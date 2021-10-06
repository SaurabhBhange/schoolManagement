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
import com.school.model.MechWorkshop;
import com.school.service.MechWorkshopService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class MechWorkshopController {

	@Autowired
	private MechWorkshopService mechWorkshopService;

	@PostMapping("mech/workshop")
	public ResponseEntity<?> createExam(@RequestBody MechWorkshop entity) throws IOException {
		MechWorkshop exam = mechWorkshopService.saveData(entity);
		if (exam != null) {
			return new ResponseEntity<MechWorkshop>(exam, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Unsuccesful attempt", HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("mech/allData")
	public ResponseEntity<?> getAllCoursed() {
		List<MechWorkshop> courses = mechWorkshopService.getData();
		if (courses != null) {
			return new ResponseEntity<List<MechWorkshop>>(courses, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("There are no courses to display");
		}
	}

	@GetMapping("mech/DataById/{workshopId}")
	public ResponseEntity<?> getCoursesById(@PathVariable int workshopId) {
		MechWorkshop courses = mechWorkshopService.getDataById(workshopId);
		if (courses != null) {
			return new ResponseEntity<MechWorkshop>(courses, HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("There are no courses to display");
		}
	}

	@PutMapping("mech/edit/workshop/{workshopid}")
	public ResponseEntity<?> update(@PathVariable int workshopid, @RequestParam String fullName,
			@RequestParam String email, @RequestParam String mobNumber, @RequestParam String courseName,
			@RequestParam String coursePrice, @RequestParam String RzrpOrderId, @RequestParam String RzrpSign,
			@RequestParam String PaymentStatus, @RequestParam String RzrpPaymentId) {
		if (fullName == null) {
			return new ResponseEntity<String>("Entities can not update", HttpStatus.OK);
		}
		MechWorkshop w = mechWorkshopService.updateData(workshopid, fullName, email, mobNumber, courseName, coursePrice,
				RzrpOrderId, RzrpPaymentId, RzrpSign, PaymentStatus);
		return new ResponseEntity<MechWorkshop>(w, HttpStatus.OK);
	}

	@DeleteMapping("mech/delete/workshop/{workshopid}")
	public ResponseEntity<?> deleteCourse(@PathVariable int workshopid) {
		mechWorkshopService.deleteData(workshopid);
		return new ResponseEntity<String>("Data deleted Successfully", HttpStatus.OK);
	}

}
