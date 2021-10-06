package com.school.service;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.school.model.WebinarCategory;
import com.school.model.WebinarCourse;
import com.school.repository.WebCourseRepo;
import com.school.repository.WebcatRepo;

@Service
public class WebinarService {

	@Autowired
	private WebcatRepo webcatRepo;

	@Autowired
	private WebCourseRepo webCourseRepo;

	@Autowired
	private AWSS3Service service;

	public WebinarCategory createCategory(WebinarCategory c) {
		try {
			return webcatRepo.save(c);
		} catch (Exception e) {
			return null;
		}
	}

	public List<WebinarCategory> getAllCategory() {
		return webcatRepo.findAll();
	}

	public void deleteCategory(int category_id) {

		webcatRepo.deleteById(category_id);
	}

	// get course by course name
	public WebinarCourse getCourseByName(String courseName) {
		try {
			WebinarCourse c = webCourseRepo.findByCourseName(courseName);
			return c;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// save course
	public WebinarCourse createCourse(MultipartFile courseImage, String courseName, String duration, String date,
			String time, String category) throws IOException {
		WebinarCategory cat = webcatRepo.findByCategory(category);

		String imageFileName = StringUtils.cleanPath(courseImage.getOriginalFilename());
		int otp = 1000 + new Random().nextInt(9000);
		imageFileName = otp + "_" + imageFileName;
		service.uploadFile(courseImage, imageFileName);

		try {
			WebinarCourse course = new WebinarCourse(courseName, duration, imageFileName, null, date, time);
			course.setCategory(cat);
			WebinarCourse c = webCourseRepo.save(course);
			c.setCourseImage(courseImage.getBytes());
			return c;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// get all graphic courses
	@SuppressWarnings("unused")
	public List<WebinarCourse> getAllCourses() {

		return webCourseRepo.findAll();

	}

	// delete course
	public void DeleteCourse(int course_id) {
		String imageFilename = webCourseRepo.findById(course_id).get().getImageFilename();

		if (imageFilename != null) {
			try {
				service.DeleteFile(imageFilename);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		webCourseRepo.deleteById(course_id);
	}

	// update course
	public WebinarCourse updateCourse(int course_id, MultipartFile courseImage, String courseName, String duration,
			String date, String time, String category) throws IOException {
		WebinarCategory cat = webcatRepo.findByCategory(category);
		WebinarCourse c = webCourseRepo.findById(course_id).orElse(new WebinarCourse());
		c.setCourseName(courseName);
		c.setDate(date);
		c.setDuration(duration);
		c.setTime(time);
		c.setCategory(cat);
		int otp = 1000 + new Random().nextInt(9000);
		if (!courseImage.isEmpty()) {
			String imagefileName = StringUtils.cleanPath(courseImage.getOriginalFilename());
			imagefileName = otp + "_" + imagefileName;
			service.uploadFile(courseImage, imagefileName);
			service.DeleteFile(c.getImageFilename());
			c.setImageFilename(imagefileName);
		}
		return webCourseRepo.save(c);
	}

	// get coursesByCategory
	public Set<WebinarCourse> getCoursesByCategory(String category) {

		return webcatRepo.findByCategory(category).getCourses();

	}

	public WebinarCourse getById(int course_id) {
		try {
			WebinarCourse c = webCourseRepo.findById(course_id).orElse(new WebinarCourse());
			return c;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public WebinarCourse updatePartialCourse(int course_id, String courseName, String duration, String date,
			String time, String category) throws IOException {
		WebinarCategory cat = webcatRepo.findByCategory(category);
		WebinarCourse course = webCourseRepo.findById(course_id).orElse(new WebinarCourse());
		course.setCategory(cat);
		course.setCourseName(courseName);
		course.setDuration(duration);
		course.setDate(date);
		course.setTime(time);
		try {
			return webCourseRepo.save(course);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
