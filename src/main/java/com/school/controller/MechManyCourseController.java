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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.exception.RecordNotFoundException;
import com.school.model.MechManyCourseWorkshop;
import com.school.service.MechManyCourseService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MechManyCourseController {

	@Autowired
	private MechManyCourseService mechworkservice;

	@PostMapping("mechMany/workshop")
	public ResponseEntity<?> createExam(@RequestParam String fullName, @RequestParam String email,
			@RequestParam String mobNumber, @RequestParam String coursePrice, @RequestParam String RzrpOrderId,
			@RequestParam String RzrpPaymentId, @RequestParam String RzrpSign, @RequestParam String PaymentStatus,
			@RequestParam String label, @RequestParam int value) throws IOException {
		MechManyCourseWorkshop exam = mechworkservice.saveData(fullName, email, mobNumber, coursePrice, RzrpOrderId,
				RzrpPaymentId, RzrpSign, PaymentStatus, label, value);
		if (exam != null) {
			return new ResponseEntity<MechManyCourseWorkshop>(exam, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Unsuccesful attempt", HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("mechMany/workshops")
	public ResponseEntity<?> getAllData() {
		List<MechManyCourseWorkshop> data = mechworkservice.getData();
		if (data != null) {
			return new ResponseEntity<List<MechManyCourseWorkshop>>(data, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("There are no Data to display");
		}
	}

	@PostMapping("mechMany/edit/workshop/{workshopid}")
	public ResponseEntity<?> update(@PathVariable int workshopid, @RequestParam String fullName,
			@RequestParam String email, @RequestParam String mobNumber,
			@RequestParam String coursePrice, @RequestParam String RzrpOrderId, @RequestParam String RzrpSign,
			@RequestParam String PaymentStatus, @RequestParam String RzrpPaymentId) {
		if (fullName == null) {
			return new ResponseEntity<String>("Entities can not update", HttpStatus.OK);
		}
		MechManyCourseWorkshop w = mechworkservice.updateData(workshopid, fullName, email, mobNumber,
				coursePrice, RzrpOrderId, RzrpPaymentId, RzrpSign, PaymentStatus);
		return new ResponseEntity<MechManyCourseWorkshop>(w, HttpStatus.OK);
	}

	@DeleteMapping("/delete/mechMany/{workshopid}")
	public ResponseEntity<?> deleteCourse(@PathVariable int workshopid) {
		mechworkservice.deleteData(workshopid);
		return new ResponseEntity<String>("Data deleted Successfully", HttpStatus.OK);
	}

	@GetMapping("mechMany/DataById/{workshopid}")
	public ResponseEntity<?> getCoursesById(@PathVariable int workshopid) {
		MechManyCourseWorkshop courses = mechworkservice.getDataById(workshopid);
		if (courses != null) {
			return new ResponseEntity<MechManyCourseWorkshop>(courses, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("There are no courses to display");
		}
	}
}
