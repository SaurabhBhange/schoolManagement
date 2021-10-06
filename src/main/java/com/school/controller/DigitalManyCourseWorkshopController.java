
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.exception.RecordNotFoundException;
import com.school.model.DigitalManyCourseWorkshop;
import com.school.service.DigitalManyCourseWorkshopService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DigitalManyCourseWorkshopController {

	@Autowired
	private DigitalManyCourseWorkshopService digitalworkservice;

	@PostMapping("digiMany/workshop")
	public ResponseEntity<?> createExam(@RequestBody DigitalManyCourseWorkshop entity) throws IOException {
		DigitalManyCourseWorkshop exam = digitalworkservice.saveData(entity);
		if (exam != null) {
			return new ResponseEntity<DigitalManyCourseWorkshop>(exam, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Unsuccesful attempt", HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/digiMany/workshops")
	public ResponseEntity<?> getAllData() {
		List<DigitalManyCourseWorkshop> data = digitalworkservice.getData();
		if (data != null) {
			return new ResponseEntity<List<DigitalManyCourseWorkshop>>(data, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("There are no Data to display");
		}
	}

	@PutMapping("digiMany/edit/workshop/{workshopid}")
	public ResponseEntity<?> update(@PathVariable int workshopid, @RequestParam String fullName,
			@RequestParam String email, @RequestParam String mobNumber, @RequestParam String[] courseName,
			@RequestParam String coursePrice, @RequestParam String RzrpOrderId, @RequestParam String RzrpSign,
			@RequestParam String PaymentStatus, @RequestParam String RzrpPaymentId) {
		if (fullName == null) {
			return new ResponseEntity<String>("Entities can not update", HttpStatus.OK);
		}
		DigitalManyCourseWorkshop w = digitalworkservice.updateData(workshopid, fullName, email, mobNumber, courseName,
				coursePrice, RzrpOrderId, RzrpPaymentId, RzrpSign, PaymentStatus);
		return new ResponseEntity<DigitalManyCourseWorkshop>(w, HttpStatus.OK);
	}

	@DeleteMapping("/delete/digiMany/{workshopid}")
	public ResponseEntity<?> deleteCourse(@PathVariable int workshopid) {
		digitalworkservice.deleteData(workshopid);
		return new ResponseEntity<String>("Data deleted Successfully", HttpStatus.OK);
	}

	@GetMapping("digiMany/DataById/{workshopid}")
	public ResponseEntity<?> getCoursesById(@PathVariable int workshopid) {
		DigitalManyCourseWorkshop courses = digitalworkservice.getDataById(workshopid);
		if (courses != null) {
			return new ResponseEntity<DigitalManyCourseWorkshop>(courses, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("There are no courses to display");
		}
	}
}
