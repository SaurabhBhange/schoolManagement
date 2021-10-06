package com.school.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.school.exception.RecordNotFoundException;
import com.school.model.VideoUpload;
import com.school.service.videoService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class VideoUploadController {

	@Autowired
	private videoService service;

	@PostMapping("/videoupload")
	public ResponseEntity<?> uploadData(@RequestParam("courseName") String courseName,
			@RequestParam("title") String title, @RequestParam("description") String description,
			@RequestParam("thumbnail") MultipartFile thumbnail, @RequestParam("video") MultipartFile video)
			throws IOException {
		String FAIL = "unable to save data in DB";
		if ("".equals(title) || title == null) {
			return new ResponseEntity<String>("title Name cant be empty", HttpStatus.BAD_REQUEST);
		} else {
			VideoUpload course = service.upload(courseName, title, description, thumbnail, video);
			if (course != null) {
				return new ResponseEntity<VideoUpload>(course, HttpStatus.OK);
			} else {
				String data = "fail";
				return new ResponseEntity<String>("{\"status\":\"" + data + "\",\"desc\":\"" + FAIL + "\"}",
						HttpStatus.OK);
			}
		}
	}

	@GetMapping("/get/videos")
	public ResponseEntity<?> getData() {
		List<VideoUpload> videos = service.getData();
		if (videos != null) {
			return new ResponseEntity<List<VideoUpload>>(videos, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("There are no videos to display", HttpStatus.OK);
		}
	}

	@GetMapping("/getvideo/{videoId}")
	public ResponseEntity<?> getByVideoId(@PathVariable int videoId) {
		VideoUpload courses = service.getVideos(videoId);
		if (courses != null) {
			return new ResponseEntity<VideoUpload>(courses, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("There are no videos to display");
		}
	}

	@DeleteMapping("/delete/video/{videoId}")
	public ResponseEntity<?> deleteData(@PathVariable int videoId) {
		service.deleteVideos(videoId);
		return new ResponseEntity<String>("Video lecture deleted successfully", HttpStatus.OK);
	}
	
	

}
