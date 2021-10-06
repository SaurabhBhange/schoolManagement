//package com.school.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.school.dto.CommentDto;
//import com.school.dto.StudentAttendaceDto;
//import com.school.dto.StudentDto;
//import com.school.exception.RecordNotFoundException;
//import com.school.model.StudentAttendance;
//import com.school.model.StudentPojo;
//import com.school.service.AttendaceService;
//import com.school.service.StudentService;
//
//@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
//@RequestMapping("/api")
//public class AttendaceController {
//
//	@Autowired
//	private AttendaceService attendService;
//
//	@Autowired
//	private StudentService studentService;
//
//	@GetMapping("student/comment")
//	public CommentDto getCommentDetails(@RequestParam int id) {
//		return null;
//	}
//
//	@GetMapping("student/getbatchstudents")
//	public ResponseEntity<?> getBatchStudent(@RequestParam int batch_id) {
//
//		List<StudentDto> st = studentService.getBatchStudent(batch_id);
//		if (st != null) {
//			return new ResponseEntity<List<StudentDto>>(st, HttpStatus.OK);
//		} else {
//			throw new RecordNotFoundException("No student present for This batch");
//		}
//	}
//
//	@PostMapping("student/attendance")
//	public ResponseEntity<?> saveAttendanceDetails(@RequestBody StudentAttendance studentAttendance) {
//
//		StudentAttendance st = attendService.saveAttendanceDetails(studentAttendance);
//		System.out.println(st.getId() + "-->");
//		if (st != null) {
//			return new ResponseEntity<StudentAttendance>(st, HttpStatus.OK);
//		} else {
//			throw new RecordNotFoundException("No Attendance of students present ");
//		}
//
//	}
///*
////this is for only showing details
//	@GetMapping("student/getattendancedetails/{course_id}/{batch_id}")
//	public ResponseEntity<?> getDetailsByBatchId(@PathVariable int course_id, @PathVariable int batch_id) {
//		List<StudentAttendaceDto> resultList = attendService.getAttendance_Details(course_id, batch_id);
//		if (resultList.size() != 0) {
//			return new ResponseEntity<List<StudentAttendaceDto>>(resultList, HttpStatus.OK);
//		} else {
//			throw new RecordNotFoundException("There are no attendance to display");
//		}
//	}
//*/
///*	@GetMapping("student/getattendancebyuserandcourseid/{userId}/{course_id}")
//	public ResponseEntity<?> getDetailsByCourseAndUserId(@PathVariable int userId, @PathVariable int course_id) {
//		List<StudentAttendaceDto> resultList = attendService.getAttendance_DetailsByCourseAndUserId(userId, course_id);
//		if (resultList.size() != 0) {
//			return new ResponseEntity<List<StudentAttendaceDto>>(resultList, HttpStatus.OK);
//		} else {
//			throw new RecordNotFoundException("There are no attendance to display");
//		}
//	}
//
////this is only for showing list of Student Attendance  	
///*	@GetMapping("student/getattendancedetailsbyid/{userId}")
//	public ResponseEntity<?> getDetailsByUserId(@PathVariable int userId) {
//		List<StudentAttendaceDto> resultList = attendService.getAttendance_DetailsById(userId);
//
//		if (resultList.size() != 0) {
//			return new ResponseEntity<List<StudentAttendaceDto>>(resultList, HttpStatus.OK);
//		} else {
//			throw new RecordNotFoundException("There are no attendance to display");
//		}
//	}
//*/
//	/*
//	@GetMapping("studentlist/getbycourseandbatch/{course_id}/{batch_id}")
//	public ResponseEntity<?> getByCourseandBatch(@PathVariable int course_id, @PathVariable int batch_id) {
//		List<StudentPojo> st = attendService.getByCourseAndBatchId(course_id, batch_id);
//		if (st != null) {
//			return new ResponseEntity<List<StudentPojo>>(st, HttpStatus.OK);
//		} else {
//			throw new RecordNotFoundException("No student present for This batch");
//		}
//	}
//
//	@GetMapping("studentlist/getbycourseandbatch/{course_id}")
//	public ResponseEntity<?> getByCourseandBatch(@PathVariable int course_id) {
//		List<StudentPojo> st = attendService.getByCourseId(course_id);
//		if (st != null) {
//			return new ResponseEntity<List<StudentPojo>>(st, HttpStatus.OK);
//		} else {
//			throw new RecordNotFoundException("No student present for This batch");
//		}
//	}
//
//}
//*/
