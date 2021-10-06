package com.school.service;

import java.io.IOException;
import java.util.Collection;
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
import com.school.model.GraphicCategory;
import com.school.model.GraphicCourse;
import com.school.model.WebinarCategory;
import com.school.model.WebinarCourse;
import com.school.repository.CourseRepo;
import com.school.repository.GraphicCategoryRepo;
import com.school.repository.GraphicCourseRepo;
import com.school.repository.ItCourseRepo;

@Service
public class GraphicCourseService {

	@Autowired
	GraphicCategoryRepo categoryRepo;

	@Autowired
	GraphicCourseRepo graphicCourseRepo;

	@Autowired
	ItCourseRepo itCourseRepo;

	@Autowired
	CourseRepo courseRepo;

	@Autowired
	private AWSS3Service service;

//get course by course name
	public GraphicCourse getCourseByName(String courseName) {
		try {
			GraphicCourse c = graphicCourseRepo.findByCourseName(courseName);
			return c;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// save course
	public GraphicCourse createCourse(MultipartFile courseImage, MultipartFile coursePDF, String courseName,
			String basicPlan, String premiumPlan, String category) throws IOException {
		GraphicCategory cat = categoryRepo.findByCategory(category);

		String imagefileName = StringUtils.cleanPath(courseImage.getOriginalFilename());
		String pdffileName = StringUtils.cleanPath(coursePDF.getOriginalFilename());
		int otp = 1000 + new Random().nextInt(9000);
		imagefileName = otp + "_" + imagefileName;
		pdffileName = otp + "_" + pdffileName;
		service.uploadFile(courseImage, imagefileName);
		service.uploadFile(coursePDF, pdffileName);

		try {
			GraphicCourse course = new GraphicCourse(courseName, basicPlan, premiumPlan, imagefileName, pdffileName,
					null, null);
			course.setCategory(cat);
			GraphicCourse c = graphicCourseRepo.save(course);
			c.setCourseImage(courseImage.getBytes());
			c.setCoursePDF(coursePDF.getBytes());
			return c;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// get all graphic courses
	@SuppressWarnings("unused")
	public List<GraphicCourse> getAllCourses() {
		return graphicCourseRepo.findAll();

	}

	// delete course
	public void DeleteCourse(int course_id) {

		String imageFilename = graphicCourseRepo.findById(course_id).get().getImageFilename();
		String pdfFilename = graphicCourseRepo.findById(course_id).get().getPdfFilename();
		System.out.println(imageFilename + "------" + pdfFilename);

		if (imageFilename != null) {
			try {
				service.DeleteFile(imageFilename);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		if (pdfFilename != null) {
			try {
				service.DeleteFile(pdfFilename);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		graphicCourseRepo.deleteById(course_id);
	}

	// update course
	public GraphicCourse updateCourse(int course_id, MultipartFile courseImage, MultipartFile coursePDF,
			String courseName, String basicPlan, String premiumPlan, String category) throws IOException {
		GraphicCategory cat = categoryRepo.findByCategory(category);
		GraphicCourse c = graphicCourseRepo.findById(course_id).orElse(new GraphicCourse());
		c.setCourseName(courseName);
		c.setBasicPlan(basicPlan);
		c.setPremiumPlan(premiumPlan);
		c.setCategory(cat);
		int otp = 1000 + new Random().nextInt(9000);
		if (!courseImage.isEmpty()) {
			String imagefileName = StringUtils.cleanPath(courseImage.getOriginalFilename());
			imagefileName = otp + "_" + imagefileName;
			service.uploadFile(courseImage, imagefileName);
			service.DeleteFile(c.getImageFilename());
			c.setImageFilename(imagefileName);
		}
		if (!coursePDF.isEmpty()) {
			String pdffileName = StringUtils.cleanPath(coursePDF.getOriginalFilename());
			pdffileName = otp + "_" + pdffileName;
			service.uploadFile(coursePDF, pdffileName);
			service.DeleteFile(c.getPdfFilename());
			c.setPdfFilename(pdffileName);
		}
		return graphicCourseRepo.save(c);

	}

	// get coursesByCategory

	public Set<GraphicCourse> getCoursesByCategory(String category) {

		return categoryRepo.findByCategory(category).getCourses();

	}

	public List<ItMechCoursesDTO> getAllItMechGraphicCourses() {

		return (List<ItMechCoursesDTO>) Stream.of(
				itCourseRepo.findAll().stream().map(e -> new ItMechCoursesDTO(e.getCourse_id(), e.getCourseName()))
						.collect(Collectors.toList()),
				graphicCourseRepo.findAll().stream().map(e -> new ItMechCoursesDTO(e.getCourse_id(), e.getCourseName()))
						.collect(Collectors.toList()),
				courseRepo.findAll().stream().map(e -> new ItMechCoursesDTO(e.getCourse_id(), e.getCourseName()))
						.collect(Collectors.toList()))
				.flatMap(Collection::stream).collect(Collectors.toList());

	}

	public GraphicCourse getById(int course_id) {
		try {
			GraphicCourse c = graphicCourseRepo.findById(course_id).orElse(new GraphicCourse());
			return c;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public GraphicCourse updatePartialCourse(int course_id, String courseName, String duration, String basicPlan,
			String premiumPlan, String category) throws IOException {
		GraphicCategory cat = categoryRepo.findByCategory(category);
		GraphicCourse course = graphicCourseRepo.findById(course_id).orElse(new GraphicCourse());
		course.setCategory(cat);
		course.setCourseName(courseName);
		course.setBasicPlan(basicPlan);
		course.setPremiumPlan(premiumPlan);
		try {
			return graphicCourseRepo.save(course);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
