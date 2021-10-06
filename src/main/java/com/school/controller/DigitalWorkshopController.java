
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.exception.RecordNotFoundException;
import com.school.model.DigitalWorkshop;
import com.school.service.DigitalWorkshopService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class DigitalWorkshopController {

	@Autowired
	private DigitalWorkshopService digitalWorkservice;

	@PostMapping("digital/workshop")
	public ResponseEntity<?> createExam(@RequestBody DigitalWorkshop entity) throws IOException {
		DigitalWorkshop exam = digitalWorkservice.saveData(entity);
		if (exam != null) {
			return new ResponseEntity<DigitalWorkshop>(exam, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Unsuccesful attempt", HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("digital/allData")
	public ResponseEntity<?> getAllCoursed() {
		List<DigitalWorkshop> courses = digitalWorkservice.getData();
		if (courses != null) {
			return new ResponseEntity<List<DigitalWorkshop>>(courses, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("There are no courses to display");
		}
	}

	@GetMapping("digital/DataById/{workshopid}")
	public ResponseEntity<?> getCoursesById(@PathVariable int workshopid) {
		DigitalWorkshop courses = digitalWorkservice.getDataById(workshopid);
		if (courses != null) {
			return new ResponseEntity<DigitalWorkshop>(courses, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("There are no courses to display");
		}
	}

	@PostMapping("/edit/digital/{workshopid}")
	public ResponseEntity<?> update(@PathVariable int workshopid, @RequestParam String fullName,
			@RequestParam String email, @RequestParam String mobNumber, @RequestParam String courseName,
			@RequestParam String coursePrice, @RequestParam String RzrpOrderId, @RequestParam String RzrpSign,
			@RequestParam String PaymentStatus, @RequestParam String RzrpPaymentId) {
		if (fullName == null) {
			return new ResponseEntity<String>("Entities can not update", HttpStatus.OK);
		}
		DigitalWorkshop w = digitalWorkservice.updateData(workshopid, fullName, email, mobNumber, courseName,
				coursePrice, RzrpOrderId, RzrpPaymentId, RzrpSign, PaymentStatus);
		return new ResponseEntity<DigitalWorkshop>(w, HttpStatus.OK);
	}

	@DeleteMapping("/delete/digital/{workshopid}")
	public ResponseEntity<?> deleteCourse(@PathVariable int workshopid) {
		digitalWorkservice.deleteData(workshopid);
		return new ResponseEntity<String>("Data deleted Successfully", HttpStatus.OK);
	}

}
