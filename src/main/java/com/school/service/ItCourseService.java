package com.school.service;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.school.dto.ItMechCoursesDTO;

import com.school.model.ItCategory;
import com.school.model.ItCourse;
import com.school.repository.CourseRepo;
import com.school.repository.GraphicCourseRepo;
import com.school.repository.InternCourseRepo;
import com.school.repository.ItCategoryRepo;
import com.school.repository.ItCourseRepo;

@Service
public class ItCourseService {

	@Autowired
	private ItCourseRepo itCourseRepo;

	@Autowired
	private ItCategoryRepo itCategoryRepo;

	@Autowired
	private CourseRepo courseRepo;

	@Autowired
	GraphicCourseRepo graphicCourseRepo;

	@Autowired
	private InternCourseRepo internCourseRepo;

	@Autowired
	private AWSS3Service service;

	// get course by course name
	public ItCourse getCourseByName(String courseName) {
		try {
			ItCourse c = itCourseRepo.findByCourseName(courseName);
			return c;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	// save course
	public ItCourse createCourse(MultipartFile courseImage, MultipartFile coursePDF, String courseName,
			String basicPlan, String premiumPlan, String category) throws IOException {
		ItCategory cat = itCategoryRepo.findByCategory(category);
		String imagefileName = StringUtils.cleanPath(courseImage.getOriginalFilename());
		String pdffileName = StringUtils.cleanPath(coursePDF.getOriginalFilename());
		int otp = 1000 + new Random().nextInt(9000);
		imagefileName = otp + "_" + imagefileName;
		pdffileName = otp + "_" + pdffileName;

		service.uploadFile(courseImage, imagefileName);
		service.uploadFile(coursePDF, pdffileName);

		// byte[] courseImg =
		// ImageCompressorService.compressBytes(courseImage.getBytes());
		// byte[] coursePdf= ImageCompressorService.compressBytes(coursePDF.getBytes());

		ItCourse course = new ItCourse(courseName, basicPlan, premiumPlan, null, null, imagefileName, pdffileName);
		try {
			course.setCategory(cat);

			ItCourse c = itCourseRepo.save(course);
			c.setCourseImage(courseImage.getBytes());
			c.setCoursePDF(coursePDF.getBytes());
			return c;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// get all course
	public List<ItCourse> getAllCoursed() {

		return itCourseRepo.findAll();

	}

	// update course
	public ItCourse updateCourse(int course_id, MultipartFile courseImage, MultipartFile coursePDF, String courseName,
			String basicPlan, String premiumPlan, String category) throws IOException {
		ItCategory cat = itCategoryRepo.findByCategory(category);
		ItCourse c = itCourseRepo.findById(course_id).orElse(new ItCourse());
		c.setCourseName(courseName);
		c.setBasicPlan(basicPlan);
		c.setPremiumPlan(premiumPlan);
		c.setCategory(cat);
		int otp = 1000 + new Random().nextInt(9000);
		if (!courseImage.isEmpty()) {
			String imagefileName = StringUtils.cleanPath(courseImage.getOriginalFilename());
			imagefileName = otp + "_" + imagefileName;
			service.uploadFile(courseImage, imagefileName);
			service.DeleteFile(c.getItFileImage());
			c.setItFileImage(imagefileName);
		}
		if (!coursePDF.isEmpty()) {
			String pdffileName = StringUtils.cleanPath(coursePDF.getOriginalFilename());
			pdffileName = otp + "_" + pdffileName;
			service.uploadFile(coursePDF, pdffileName);
			service.DeleteFile(c.getItFilePdf());
			c.setItFilePdf(pdffileName);
		}
		return itCourseRepo.save(c);
	}

	// delete course
	public void DeleteCourse(int course_id) {
		String itFileImage = itCourseRepo.findById(course_id).get().getItFileImage();
		String itFilePdf = itCourseRepo.findById(course_id).get().getItFilePdf();
		if (itFileImage != null) {
			try {
				service.DeleteFile(itFileImage);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		if (itFilePdf != null) {
			try {
				service.DeleteFile(itFilePdf);
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		itCourseRepo.deleteById(course_id);
	}

	// get coursesByCategory
	public Set<ItCourse> getCoursesByCategory(String category) {

		Set<ItCourse> courses = new HashSet<ItCourse>();

		courses = itCategoryRepo.findByCategory(category).getCourses();

		return courses;

	}

	
	public Set<ItCourse> getCoursesByCategory(int cat_id) {

		return itCategoryRepo.findById(cat_id).get().getCourses();

	}

	public List<ItMechCoursesDTO> getAllItMechCourses() {

		return (List<ItMechCoursesDTO>) Stream.of(
				itCourseRepo.findAll().stream().map(e -> new ItMechCoursesDTO(e.getCourse_id(), e.getCourseName()))
						.collect(Collectors.toList()),
				courseRepo.findAll().stream().map(e -> new ItMechCoursesDTO(e.getCourse_id(), e.getCourseName()))
						.collect(Collectors.toList()),
				graphicCourseRepo.findAll().stream().map(e -> new ItMechCoursesDTO(e.getCourse_id(), e.getCourseName()))
						.collect(Collectors.toList()))
				.flatMap(Collection::stream).collect(Collectors.toList());
	}

	public ItCourse getById(int course_id) {
		try {
			ItCourse c = itCourseRepo.findById(course_id).orElse(new ItCourse());
			return c;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public ItCourse updatePartialCourse(int course_id, String courseName, String basicPlan, String premiumPlan,
			String category) throws IOException {
		ItCategory cat = itCategoryRepo.findByCategory(category);
		ItCourse course = itCourseRepo.findById(course_id).orElse(new ItCourse());
		course.setCategory(cat);
		course.setCourseName(courseName);
		course.setBasicPlan(basicPlan);
		course.setPremiumPlan(premiumPlan);
		try {
			return itCourseRepo.save(course);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
