package com.school.service;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.school.model.InternshipCategory;
import com.school.model.InternshipCourse;
import com.school.model.WebinarCategory;
import com.school.model.WebinarCourse;
import com.school.repository.InternCategoryRepo;
import com.school.repository.InternCourseRepo;

@Service
public class InternshipService {

	@Autowired
	private InternCategoryRepo categoryRepo;

	@Autowired
	private AWSS3Service service;

	@Autowired
	private InternCourseRepo courseRepo;

	public InternshipCategory createCategory(InternshipCategory c) {
		try {
			return categoryRepo.save(c);

		} catch (Exception e) {
			return null;
		}
	}

	public List<InternshipCategory> getAllCategory() {
		return categoryRepo.findAll();
	}

	public InternshipCategory getCategoryByName(String category) {
		try {
			return categoryRepo.findByCategory(category);

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	public void deleteCategory(int cat_id) {

		categoryRepo.deleteById(cat_id);
	}

//COURSE SERVICE
	// get course by name
	public InternshipCourse getCourseByName(String courseName) {
		try {
			InternshipCourse c = courseRepo.findByCourseName(courseName);
			return c;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

//save course
	public InternshipCourse createCourse(MultipartFile courseImage, String courseName, String duration, String category)
			throws IOException {
		InternshipCategory cat = categoryRepo.findByCategory(category);
		String imagefileName = StringUtils.cleanPath(courseImage.getOriginalFilename());
		int otp = 1000 + new Random().nextInt(9000);
		imagefileName = otp + "_" + imagefileName;
		service.uploadFile(courseImage, imagefileName);
		try {
			InternshipCourse course = new InternshipCourse(courseName, duration, imagefileName, null);
			course.setCategory(cat);
			InternshipCourse c = courseRepo.save(course);
			c.setCourseImage(courseImage.getBytes());
			return c;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// get all course
	@SuppressWarnings("unused")
	public List<InternshipCourse> getAllCourses() {

		return courseRepo.findAll();

	}

//update course
	public InternshipCourse updateCourse(int course_id, MultipartFile courseImage, String courseName, String duration,
			String category) throws IOException {
		InternshipCategory cat = categoryRepo.findByCategory(category);
		InternshipCourse c = courseRepo.findById(course_id).orElse(new InternshipCourse());
		c.setCourseName(courseName);
		c.setDuration(duration);
		c.setCategory(cat);
		int otp = 1000 + new Random().nextInt(9000);
		if (!courseImage.isEmpty()) {
			String imagefileName = StringUtils.cleanPath(courseImage.getOriginalFilename());
			imagefileName = otp + "_" + imagefileName;
			service.uploadFile(courseImage, imagefileName);
			service.DeleteFile(c.getImageFilename());
			c.setImageFilename(imagefileName);
		}
		return courseRepo.save(c);

	}

	// delete course
	public void DeleteCourse(int course_id) {
		String imageFilename = courseRepo.findById(course_id).get().getImageFilename();

		if (imageFilename != null) {
			try {
				service.DeleteFile(imageFilename);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		courseRepo.deleteById(course_id);
	}

	// get coursesByCategory

	public Set<InternshipCourse> getCoursesByCategory(String category) {

		return categoryRepo.findByCategory(category).getCourses();

	}

	public Set<InternshipCourse> getCoursesByCategoryID(int cat_id) {

		return categoryRepo.findById(cat_id).get().getCourses();

	}

	public InternshipCourse getById(int course_id) {
		try {
			InternshipCourse c = courseRepo.findById(course_id).orElse(new InternshipCourse());
			return c;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public InternshipCourse updatePartialCourse(int course_id, String courseName, String duration, String category)
			throws IOException {
		InternshipCategory cat = categoryRepo.findByCategory(category);
		InternshipCourse course = courseRepo.findById(course_id).orElse(new InternshipCourse());
		course.setCategory(cat);
		course.setCourseName(courseName);
		course.setDuration(duration);
		try {
			return courseRepo.save(course);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
