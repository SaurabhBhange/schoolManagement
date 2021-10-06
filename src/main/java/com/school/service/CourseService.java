package com.school.service;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.ecr.model.ImageNotFoundException;
import com.school.model.Category;
import com.school.model.Course;
import com.school.model.CourseBatchMapping;
import com.school.repository.CategoryRepo;
import com.school.repository.CourseBatchMappingRepo;
import com.school.repository.CourseRepo;

@Service
public class CourseService {

	@Autowired
	private CourseRepo courseRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private AWSS3Service service;

	@Autowired
	private CourseBatchMappingRepo repo;

	// get course by course name
	public Course getCourseByName(String courseName) {
		try {
			Course c = courseRepo.findByCourseName(courseName);
			return c;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

//save course
	public Course createCourse(MultipartFile courseImage, MultipartFile coursePDF, String courseName, String basicPlan,
			String premiumPlan, String category) throws IOException {
		Category cat = categoryRepo.findByCategory(category);

		String imagefileName = StringUtils.cleanPath(courseImage.getOriginalFilename());
		String pdffileName = StringUtils.cleanPath(coursePDF.getOriginalFilename());
		int otp = 1000 + new Random().nextInt(9000);
		imagefileName = otp + "_" + imagefileName;
		pdffileName = otp + "_" + pdffileName;
		service.uploadFile(courseImage, imagefileName);
		service.uploadFile(coursePDF, pdffileName);
		try {
			Course course = new Course(courseName, basicPlan, premiumPlan, imagefileName, pdffileName, null, null);
			course.setCategory(cat);
			Course c = courseRepo.save(course);
			c.setCourseImage(courseImage.getBytes());
			c.setCoursePDF(coursePDF.getBytes());
			return c;
		} catch (Exception e) {

			throw new ImageNotFoundException("Currently Image and PDF not found in this course");
		}
	}

	// get all course
	@SuppressWarnings("unused")
	public List<Course> getAllCourses() {
		try {
			return courseRepo.findAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public Course getById(int course_id) {
		try {

			return courseRepo.findById(course_id).orElse(new Course());

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

//update course
	public Course updateCourse(int course_id, MultipartFile courseImage, MultipartFile coursePDF, String courseName,
			String basicPlan, String premiumPlan, String category) throws IOException {
		
		Category cat = categoryRepo.findByCategory(category);
		Course course = courseRepo.findById(course_id).orElse(new Course());
		/*
		 * 1.set all the variables except image and pdf 2.check if multipart are null 3.
		 * if above condition is false then will upload file to server with new name and
		 * before uploadig delete old file with the name inside the course object
		 */
		course.setCourseName(courseName);
		course.setBasicPlan(basicPlan);
		course.setPremiumPlan(premiumPlan);
		course.setCategory(cat);
		int otp = 1000 + new Random().nextInt(9000);
		if (!courseImage.isEmpty()) {
			String imagefileName = StringUtils.cleanPath(courseImage.getOriginalFilename());
			imagefileName = otp + "_" + imagefileName;
			service.uploadFile(courseImage, imagefileName);
			service.DeleteFile(course.getImageFilename());
			course.setImageFilename(imagefileName);
		} else {
			course.setCourseImage(null);
			course.setImageFilename(course.getImageFilename());
		}
		if (!coursePDF.isEmpty()) {
			String pdffileName = StringUtils.cleanPath(coursePDF.getOriginalFilename());
			pdffileName = otp + "_" + pdffileName;
			service.uploadFile(coursePDF, pdffileName);
			service.DeleteFile(course.getPdfFilename());
			course.setPdfFilename(pdffileName);
		} else {
			course.setCoursePDF(null);
		}
		course.setPdfFilename(course.getPdfFilename());
		return courseRepo.save(course);
	}

	public Course updatePartialCourse(int course_id, String courseName, String basicPlan, String premiumPlan,
			String category) throws IOException {
		
		Category cat = categoryRepo.findByCategory(category);
		Course course = courseRepo.findById(course_id).orElse(new Course());
		course.setCategory(cat);
		course.setCourseName(courseName);
		course.setBasicPlan(basicPlan);
		course.setPremiumPlan(premiumPlan);
		try {
			return courseRepo.save(course);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// delete course
	public void DeleteCourse(int course_id) {
		String imageName = courseRepo.findById(course_id).get().getImageFilename();
		String pdfName = courseRepo.findById(course_id).get().getPdfFilename();
		if (imageName != null) {
			try {
				service.DeleteFile(imageName);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		if (pdfName != null) {
			try {
				service.DeleteFile(courseRepo.findById(course_id).get().getPdfFilename());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		courseRepo.deleteById(course_id);
	}

	// get coursesByCategory
	public Set<Course> getCoursesByCategory(String category) {
		return categoryRepo.findByCategory(category).getCourses();

	}

	public List<CourseBatchMapping> getData(int batch_id) {
		return repo.findByBatchId(batch_id);
	}

}
