package com.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.dto.ExamStudentMappingRequest;
import com.school.exception.RecordNotFoundException;
import com.school.model.ExamModule;
import com.school.model.ExamStudentMapping;
import com.school.service.ExamModuleService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExamModuleController {

	@Autowired
	private ExamModuleService examModuleService;

	// API For creating Exam of different courses with Batches
	@PostMapping("/create/exam")
	public ResponseEntity<?> createExam(@RequestBody ExamModule ex) {
		ExamModule exam = examModuleService.createExam(ex);
		if (exam != null) {
			return new ResponseEntity<ExamModule>(exam, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("exam can not be crated", HttpStatus.NO_CONTENT);
		}
	}

	// Api For Updating Exam
	@PutMapping("update/exam/{examId}")
	public ResponseEntity<?> updatePost(@PathVariable("examId") int examId, String examName, String duration,
			String passingPercentage, Integer course_id, Integer batch_id) throws RecordNotFoundException {
		ExamModule module = examModuleService.UpdateExam(examId, examName, duration, passingPercentage, course_id,
				batch_id);
		return new ResponseEntity<String>("Exam " + examName + " updated successfully", HttpStatus.OK);
	}

	// API For deleting exam
	@DeleteMapping("/delete/exam/{examId}")
	public ResponseEntity<?> deleteExam(@PathVariable int examId) {
		examModuleService.deleteexam(examId);
		return new ResponseEntity<String>("exam deleted successfully", HttpStatus.OK);
	}

	// API For getting available Exam
	@GetMapping("/getexam")
	public ResponseEntity<?> availableExam() {
		List<ExamModule> exam = examModuleService.availableExam();
		return new ResponseEntity<List<ExamModule>>(exam, HttpStatus.OK);
	}

	// API for list of all exams
	@GetMapping("/allExams")
	public ResponseEntity<?> allExam() {
		List<ExamModule> exam = examModuleService.getAllCourses();
		if (exam != null)
			return new ResponseEntity<List<ExamModule>>(exam, HttpStatus.OK);
		else {
			return new ResponseEntity<String>("No Exams Present", HttpStatus.OK);
		}
	}

	// Subscription Of Student for Exam
	@PostMapping("/subscribe/Exam")
	public ResponseEntity<?> saveData(@RequestBody ExamStudentMappingRequest requestData) {
		List<ExamStudentMapping> asm = examModuleService.Subscribe(requestData.getExam(),requestData.getStudent());
		if (asm != null) {
			return new ResponseEntity<List<ExamStudentMapping>>(asm, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Subscribed Successfully", HttpStatus.OK);
		}
	}

	// Delete Api for Exam
	@DeleteMapping("delete/ExamSubsciption/{id}")
	public ResponseEntity<?> deleteSubscription(@PathVariable int id) {
		examModuleService.DeleteData(id);
		return new ResponseEntity<String>("exam Subscription deleted successfully", HttpStatus.OK);
	}
}
