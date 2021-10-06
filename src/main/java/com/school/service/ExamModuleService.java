package com.school.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.school.model.Course;
import com.school.model.ExamModule;
import com.school.model.ExamStudentMapping;
import com.school.model.GraphicCourse;
import com.school.model.ItCourse;
import com.school.model.StudentPojo;
import com.school.repository.BatchRepo;
import com.school.repository.CourseRepo;
import com.school.repository.ExamModuleRepo;
import com.school.repository.ExamStudentMappingRepo;
import com.school.repository.GraphicCourseRepo;
import com.school.repository.ItCourseRepo;

@Service
public class ExamModuleService {

	@Autowired
	private ExamModuleRepo examModuleRepo;

	@Autowired
	private ExamStudentMappingRepo repo;

	@Autowired
	private ItCourseRepo itCourseRepo;

	@Autowired
	private CourseRepo courseRepo;

	@Autowired
	GraphicCourseRepo graphicCourseRepo;

	@Autowired
	private BatchRepo batchRepo;

	public ExamModule createExam(ExamModule data) {
		return examModuleRepo.save(data);
	}

	public ExamModule findwithId(int examId) {
		return examModuleRepo.findById(examId).get();
	}

	public List<ExamModule> getAllCourses() {
		try {
			List<ExamModule> exams = examModuleRepo.findAll();
			List<ExamModule> finalResult = new ArrayList<ExamModule>();
			if (exams.size() != 0) {
				for (ExamModule e : exams) {
					int id = e.getCourse_id();
					ItCourse course = itCourseRepo.findById(id).orElse(new ItCourse());
					String courseName = course.getCourseName();
					if (courseName == null) {
						courseName = courseRepo.findById(id).orElse(new Course()).getCourseName();
						if (courseName == null) {
							courseName = graphicCourseRepo.findById(id).orElse(new GraphicCourse()).getCourseName();
						}
					}
					String batchName = batchRepo.findById(e.getBatch_id()).get().getBatch();
					ExamModule em = new ExamModule(e.getExamId(), e.getExamName(), e.getDuration(),
							e.getPassingPercentage(), e.getCourse_id(), e.getBatch_id(), courseName, batchName);
					System.out.println(em.getCourseName() + batchName);

					finalResult.add(em);
				} // for
			} // if
			return finalResult;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/*
	 * public List<ExamModule> getAllCourses() { try { List<ExamModule> exams =
	 * examModuleRepo.findAll(); return exams; }catch (Exception e) {
	 * System.out.println(e.getMessage()); } return null; }
	 */

	public void deleteexam(int examId) {
		examModuleRepo.deleteById(examId);
	}

	public List<ExamModule> availableExam() {
		return null;
	}

	public ExamModule UpdateExam(int examId, String examName, String duration, String passingPercentage,
			Integer course_id, Integer batch_id) {
		ExamModule ex = examModuleRepo.findById(examId).get();
		ex.setExamName(examName);
		ex.setDuration(duration);
		ex.setPassingPercentage(passingPercentage);
		ex.setCourse_id(course_id);
		ex.setBatch_id(batch_id);
		return examModuleRepo.save(ex);
	}

	public List<ExamStudentMapping> Subscribe(List<ExamModule> examId, List<StudentPojo> userId) {
		List<ExamStudentMapping> list = new ArrayList<>();

		Iterator<ExamModule> it1 = examId.iterator();
		Iterator<StudentPojo> it2 = userId.iterator();
		while (it1.hasNext() && it2.hasNext()) {

			ExamModule exam = it1.next();
			StudentPojo std = it2.next();

			ExamStudentMapping ex = new ExamStudentMapping(std, exam);
			list.add(ex);
		}

		return repo.saveAll(list);
	}

	public void DeleteData(int id) {
		try {
			repo.deleteById(id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
