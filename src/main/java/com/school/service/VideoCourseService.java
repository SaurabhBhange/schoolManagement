package com.school.service;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.school.model.StudentPojo;
import com.school.model.VideoCourse;
import com.school.model.VideoUpload;
import com.school.model.uservideomapping;
import com.school.repository.UserVideoRepo;
import com.school.repository.VideoCourseRepo;
import com.school.repository.videoRepo;

@Service
public class VideoCourseService {

	@Autowired
	private VideoCourseRepo repo;

	@Autowired
	private UserVideoRepo vidRepo;

	@Autowired
	private AWSS3Service service;

	@Autowired
	private videoRepo uservidRepo;

	public VideoCourse createCourse(MultipartFile courseImage, MultipartFile coursePDF, String courseName, String price)
			throws IOException {

		String ImageFileName = StringUtils.cleanPath(courseImage.getOriginalFilename());
		String pdfFileName = StringUtils.cleanPath(coursePDF.getOriginalFilename());
		int otp = 1000 + new Random().nextInt(9000);
		ImageFileName = otp + "_" + ImageFileName;
		pdfFileName = otp + "_" + pdfFileName;
		service.uploadFile(courseImage, ImageFileName);
		service.uploadFile(coursePDF, pdfFileName);
		try {
			VideoCourse course = new VideoCourse(courseName, price, null, null, ImageFileName, pdfFileName);

			repo.save(course);
			course.setCourseImage(courseImage.getBytes());
			course.setCoursePDF(coursePDF.getBytes());
			return course;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// get all course
	@SuppressWarnings("unused")
	public List<VideoCourse> getAllCourses() {
		try {

			return repo.findAll();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	// update course
	public VideoCourse updateCourse(int videoCourseId, MultipartFile courseImage, MultipartFile coursePDF,
			String courseName, String price) throws IOException {

		VideoCourse c = repo.findById(videoCourseId).orElse(new VideoCourse());
		byte[] courseImg = ImageCompressorService.compressBytes(courseImage.getBytes());
		byte[] coursePdf = ImageCompressorService.compressBytes(coursePDF.getBytes());
		c.setCourseName(courseName);
		c.setPrice(price);
		c.setCourseImage(courseImg);
		c.setCoursePDF(coursePdf);
		try {
			return repo.save(c);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// delete course
	public void DeleteCourse(int videoCourseId) {
		String imageName = repo.findById(videoCourseId).get().getImageFileName();
		String pdfName = repo.findById(videoCourseId).get().getPdfFileName();

		if (imageName != null) {
			try {
				service.DeleteFile(imageName);
			}

			catch (Exception e) {
				System.out.println(e);
			}
		}

		if (pdfName != null) {
			try {
				service.DeleteFile(repo.findById(videoCourseId).get().getPdfFileName());
			}

			catch (Exception e) {
			}
		}
		repo.deleteById(videoCourseId);
	}

	/*
	 * public Set<VideoCourse> getCourseByName(String courseName) { try {
	 * 
	 * return repo.findByCourseName(courseName).getVideos(); } catch (Exception e) {
	 * System.out.println(e); return null; } }
	 */

	public uservideomapping Subscribe(VideoCourse videoCourseId, StudentPojo userId) {
		try {
//			StudentPojo st = new StudentPojo();
//			st.setUserId(userId);
//			VideoCourse vid = new VideoCourse();
//			vid.setVideoCourseId(videoCourseId);
			uservideomapping us = new uservideomapping(videoCourseId, userId);
			uservideomapping uvm = vidRepo.save(us);
			System.out.println(uvm);
			return uvm;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void DeleteSubscription(int id) {
		try {
			uservidRepo.deleteById(id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public List<VideoUpload> getData(int user_id) {

		return uservidRepo.getSubscribedCourses(user_id);
	}

}
