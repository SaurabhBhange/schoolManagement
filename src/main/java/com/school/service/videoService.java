package com.school.service;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.school.model.VideoCourse;
import com.school.model.VideoUpload;
import com.school.repository.VideoCourseRepo;
import com.school.repository.videoRepo;

@Service
public class videoService {

	@Autowired
	private videoRepo videorepo;

	@Autowired
	private VideoCourseRepo repo;

	@Autowired
	private AWSS3Service service;

	@Autowired
	private AWSVideoBucket videobucket;

	public VideoUpload upload(String courseName, String title, String description, MultipartFile thumbnail,
			MultipartFile video) throws IOException {
		VideoCourse cat = repo.findByCourseName(courseName);
		String thumbnailName = StringUtils.cleanPath(thumbnail.getOriginalFilename());
		int otp = 1000 + new Random().nextInt(9000);
		thumbnailName = otp + "_" + thumbnailName;
		service.uploadFile(thumbnail, thumbnailName);
		String videoFilename = StringUtils.cleanPath(video.getOriginalFilename());
		videobucket.uploadFile(video, videoFilename);
		try {
			VideoUpload v = new VideoUpload(title, description, null, thumbnailName, null, videoFilename);
			v.setCourseName(cat);
			VideoUpload vid = videorepo.save(v);
			vid.setThumbnail(thumbnail.getBytes());
			vid.setVideo(video.getBytes());
			return v;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public List<VideoUpload> getData() {
		try {
			return videorepo.findAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void deleteVideos(int videoId) {
		String thumbnailName = videorepo.findById(videoId).getThumbnailName();
		String videoFilename = videorepo.findById(videoId).getVideoFilename();

		if (thumbnailName != null) {
			try {
				service.DeleteFile(thumbnailName);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (videoFilename != null) {
			try {
				videobucket.DeleteFile(videoFilename);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		videorepo.deleteById(videoId);
	}

	public VideoUpload getVideos(int videoId) {
		try {
			VideoUpload vid = videorepo.findById(videoId);
			return vid;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public VideoUpload getbyCourseId(int videoCourseId) {
		try {
			VideoUpload vid = videorepo.findById(videoCourseId);
			return vid;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
