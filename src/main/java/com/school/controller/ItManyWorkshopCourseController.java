package com.school.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.exception.RecordNotFoundException;
import com.school.model.ItManyCourseWorkshop;
import com.school.service.ItManyCourseWorkService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ItManyWorkshopCourseController {

	@Autowired
	private ItManyCourseWorkService itworkservice;

	@PostMapping("itMany/workshop")
	public ResponseEntity<?> createExam(@RequestParam String fullName, @RequestParam String email,
			@RequestParam String mobNumber, @RequestParam String coursePrice, @RequestParam String RzrpOrderId,
			@RequestParam String RzrpPaymentId, @RequestParam String RzrpSign, @RequestParam String PaymentStatus,
			@RequestParam String label, @RequestParam int value) throws IOException {

		ItManyCourseWorkshop exam = itworkservice.saveData(fullName, email, mobNumber, coursePrice, RzrpOrderId,
				RzrpPaymentId, RzrpSign, PaymentStatus, label, value);
		if (exam != null) {
			return new ResponseEntity<ItManyCourseWorkshop>(exam, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Unsuccesful attempt", HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/itMany/workshops")
	public ResponseEntity<?> getAllData() {
		List<ItManyCourseWorkshop> data = itworkservice.getData();
		if (data != null) {
			return new ResponseEntity<List<ItManyCourseWorkshop>>(data, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("There are no Data to display");
		}
	}

	@PutMapping("itMany/edit/workshop/{workshopid}")
	public ResponseEntity<?> update(@PathVariable int workshopid, @RequestParam String fullName,
			@RequestParam String email, @RequestParam String mobNumber, @RequestParam String[] courseName,
			@RequestParam String coursePrice, @RequestParam String RzrpOrderId, @RequestParam String RzrpSign,
			@RequestParam String PaymentStatus, @RequestParam String RzrpPaymentId) {
		if (fullName == null) {
			return new ResponseEntity<String>("Entities can not update", HttpStatus.OK);
		}
		ItManyCourseWorkshop w = itworkservice.updateData(workshopid, fullName, email, mobNumber, coursePrice,
				RzrpOrderId, RzrpPaymentId, RzrpSign, PaymentStatus);
		return new ResponseEntity<ItManyCourseWorkshop>(w, HttpStatus.OK);
	}

	@DeleteMapping("/delete/itMany/{workshopid}")
	public ResponseEntity<?> deleteCourse(@PathVariable int workshopid) {
		itworkservice.deleteData(workshopid);
		return new ResponseEntity<String>("Data deleted Successfully", HttpStatus.OK);
	}

	@GetMapping("itMany/DataById/{workshopid}")
	public ResponseEntity<?> getCoursesById(@PathVariable int workshopid) {
		ItManyCourseWorkshop courses = itworkservice.getDataById(workshopid);
		if (courses != null) {
			return new ResponseEntity<ItManyCourseWorkshop>(courses, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("There are no courses to display");
		}
	}
}
