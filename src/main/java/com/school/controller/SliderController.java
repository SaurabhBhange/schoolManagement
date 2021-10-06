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

import com.school.model.SlidingImage;
import com.school.service.SliderimageService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/")
public class SliderController {
	
	@Autowired
	private SliderimageService service;
	
	@PostMapping("/imageupload")
	public ResponseEntity<?> uploadData(@RequestParam("Title") String Title,
			@RequestParam("Description") String Description, @RequestParam("image") MultipartFile image
			) throws IOException {
		String FAIL = "unable to save data in DB";
	
			SlidingImage slider = service.createImage(Title, Description, image);

			if (slider != null) {
				return new ResponseEntity<SlidingImage>(slider, HttpStatus.OK);

			} else {
				String data = "fail";
				return new ResponseEntity<String>("{\"status\":\"" + data + "\",\"desc\":\"" + FAIL + "\"}",
						HttpStatus.OK);
			}
		
	}
	
	@GetMapping("/get/sliderimages")
	public ResponseEntity<?> getData() {

		List<SlidingImage> images = service.getData();
		if (images != null) {
			return new ResponseEntity<List<SlidingImage>>(images, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("There are no Images to display", HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/delete/image/{id}")
	public ResponseEntity<?> deleteData(@PathVariable int id){
		
		service.deleteImages(id);
		return new ResponseEntity<String>("Slider Image deleted successfully",HttpStatus.OK);
	}


}
