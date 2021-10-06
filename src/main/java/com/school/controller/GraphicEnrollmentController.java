package com.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.school.dto.UserPojoPageDto;
import com.school.model.GraphicEnrollment;
import com.school.service.GraphicEnrollmentService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class GraphicEnrollmentController {

	@Autowired
	private GraphicEnrollmentService graphicService;

	// API For creating training leads
	@PostMapping("/graphic/service")
	public ResponseEntity<?> createIneterest(@RequestBody GraphicEnrollment enroll) {
		if (enroll.getEmail() == "") {
			String data = "fail";
			return new ResponseEntity<String>("{\"status\":\"" + data + "\"}", HttpStatus.OK);
		}
		GraphicEnrollment gr = graphicService.createInterest(enroll);
		return new ResponseEntity<GraphicEnrollment>(gr, HttpStatus.OK);
	}

	// API For getting available training Entries or leads
	@GetMapping("/graphic/services")
	public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam (defaultValue = "all")String data,
			@RequestParam (defaultValue = "all")String filterby) {
		Page<GraphicEnrollment> page = graphicService.getAllUsers(pageNo, pageSize,data,filterby);
		if (page != null) {
			return new ResponseEntity<UserPojoPageDto>(
					new UserPojoPageDto(page.getTotalElements(), page.getContent(), page.getTotalPages()),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No lists available in DB to display", HttpStatus.NO_CONTENT);
		}
	}

	// API For update available graphic entries for comment and colour
	@PostMapping("/update/graphic")
	public ResponseEntity<?> updateMethod(@RequestParam("id") int id, @RequestParam("comment") String comment,
			@RequestParam("colour") String colour) {

		int result = graphicService.updateGraphicEnrollment(colour, comment, id);

		if (result > 0) {
			return new ResponseEntity<String>("Updated Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Sorry Unable to Update", HttpStatus.OK);
		}
	}

	// API For deleting available graphic training entries
	@DeleteMapping("/delete/graphicinterest/{id}")
	public ResponseEntity<?> deleteEnrollmentInterest(@PathVariable int id) {

		graphicService.deleteEnrollmentInterest(id);

		return new ResponseEntity<String>("EnrollmentInterest Successfully deleted", HttpStatus.OK);
	}

}
