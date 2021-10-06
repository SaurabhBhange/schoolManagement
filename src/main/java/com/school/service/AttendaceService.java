//package com.school.service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.school.dto.StudentAttendaceDto;
//import com.school.model.StudentAttendance;
//import com.school.model.StudentPojo;
//import com.school.repository.AttendanceDetailsRepo;
//import com.school.repository.StudentAttendanceRepo;
//import com.school.repository.StudentRepo;
//
//
//public class AttendaceService {
//
//	@Autowired
//	StudentAttendanceRepo studentAttendanceRepo;
//
//	@Autowired
//	StudentRepo studentRepo;
//
//	@Autowired
//	AttendanceDetailsRepo attendanceDetailRepo;
//
//	public StudentAttendance saveAttendanceDetails(StudentAttendance studentAttendance) {
//		try {
//			return studentAttendanceRepo.save(studentAttendance);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			return null;
//		}
//	}
//}
//
///*	public List<StudentAttendaceDto> getAttendance_Details(int batch_id, int course_id) {
//
//		List<StudentPojo> students = studentRepo.findByCourseAndBatch(batch_id, course_id);
//		List<StudentAttendance> attendance = studentAttendanceRepo.findAll();
//		List<StudentAttendaceDto> studentAttendaceDtoList = new ArrayList<>();
//
//		attendance.forEach(attendenceItr -> {
//	//		List<StudentAttendanceDetails> studentAttendanceDetails = attendenceItr.getClass();
//			students.forEach(stud -> {
//				for (int i = 0; i < studentAttendanceDetails.size(); i++) {
//					if (stud.getUserId() == studentAttendanceDetails.get(i).getUser_id()) {
//						StudentAttendaceDto studentAttendaceDto = new StudentAttendaceDto(stud.getUserId(),
//								stud.getFirstName(), stud.getLastName(), attendenceItr.getDate(),
//								attendenceItr.getDetail(), studentAttendanceDetails.get(i).getCourse_id(),
//								studentAttendanceDetails.get(i).getBatch_id(),
//								studentAttendanceDetails.get(i).getStatus());
//						studentAttendaceDtoList.add(studentAttendaceDto);
//					}
//				}
//			});
//		});
//
//		return studentAttendaceDtoList;
//	}
//*/
///*	
//	public List<StudentAttendaceDto> getAttendance_DetailsById(int userId) {
//
//		List<StudentPojo> students = studentRepo.findByUserId(userId);
//		List<StudentAttendance> attendance = studentAttendanceRepo.findAll();
//		List<StudentAttendaceDto> studentAttendaceDtoList = new ArrayList<>();
//
//		attendance.forEach(attendenceItr -> {
//			List<StudentAttendanceDetails> studentAttendanceDetails = attendenceItr.getStudentAttendanceDetails();
//			students.forEach(stud -> {
//				for (int i = 0; i < studentAttendanceDetails.size(); i++) {
//					if (stud.getUserId() == studentAttendanceDetails.get(i).getUser_id()) {
//						StudentAttendaceDto studentAttendaceDto = new StudentAttendaceDto(stud.getUserId(),
//								stud.getFirstName(), stud.getLastName(), attendenceItr.getDate(),
//								attendenceItr.getDetail(), studentAttendanceDetails.get(i).getCourse_id(),
//								studentAttendanceDetails.get(i).getBatch_id(),
//								studentAttendanceDetails.get(i).getStatus());
//						studentAttendaceDtoList.add(studentAttendaceDto);
//					}
//				}
//			});
//		});
//
//		return studentAttendaceDtoList;
//	}
//*/
///*
//	public List<StudentAttendaceDto> getAttendance_DetailsByCourseAndUserId(int userId, int course_id) {
//
//		List<StudentAttendaceDto> studentAttendaceDtoList = new ArrayList<>();
//		List<StudentAttendanceDetails> studentAttendanceDetails = attendanceDetailRepo.getListOfAttendance(userId,
//				course_id);
//
//		studentAttendanceDetails.forEach(stud -> {
//			for (int i = 0; i < studentAttendanceDetails.size(); i++) {
//				System.out.println(studentAttendanceDetails.get(i).getSadetails_id()+"--->>>");
//				StudentAttendance attendance = studentAttendanceRepo.findById(studentAttendanceDetails.get(i).getSadetails_id()).orElse(new StudentAttendance());
//				System.out.println(attendance.getDetail() + "--->>>" + attendance.getDate());
//				StudentAttendaceDto studentAttendaceDto = new StudentAttendaceDto(userId, null, null,
//						attendance.getDate(), attendance.getDetail(), studentAttendanceDetails.get(i).getCourse_id(),
//						studentAttendanceDetails.get(i).getBatch_id(), studentAttendanceDetails.get(i).getStatus());
//				studentAttendaceDtoList.add(studentAttendaceDto);
//			}
//		});
//
//		return studentAttendaceDtoList;
//	}
//
//	public List<StudentPojo> getByCourseAndBatchId(int course_id, int batch_id) {
//		try {
//			System.out.println("data");
//			return studentRepo.findByCourseAndBatch(course_id, batch_id);
//
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			return null;
//		}
//	}
//
//	public List<StudentPojo> getByCourseId(int course_id) {
//		try {
//			return studentRepo.findByCourse(course_id);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			return null;
//		}
//	}
//	/*
//	 * public List<StudentAttendaceDto> getAttendanceOnStudentDashboard(int
//	 * userId,int course_id){ List<StudentAttendance> comment = new
//	 * ArrayList<StudentAttendance>(); List<StudentAttendanceDetails> details =
//	 * attendanceDetailRepo.getListOfAttendance(userId, course_id);
//	 * List<StudentAttendaceDto> output = details.stream().map(st ->{ for(int i=0; i
//	 * < details.size(); i++) { if(comment.get(i).getId() ==
//	 * details.get(i).getSadetails_id()) {
//	 * 
//	 * } } }
//	 * 
//	 * ) }
//	 * 
//	 * }
//	 */
////}
