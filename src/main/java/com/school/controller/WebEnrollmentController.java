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

import com.school.model.WebinarEnrollment;
import com.school.service.WebinarEnrollmentService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class WebEnrollmentController {

	@Autowired
	private WebinarEnrollmentService webService;

	// API For Creating Web Training Lead
	@PostMapping("/create/webtraining")
	public ResponseEntity<?> createIneterest(@RequestBody WebinarEnrollment enroll) {

		if (enroll.getEmail() == "") {
			String Fail = "uanble to save Data";
			return new ResponseEntity<String>("{\"status\":\"" + Fail + "\"}", HttpStatus.OK);

		}
		WebinarEnrollment web = webService.createInterest(enroll);
		return new ResponseEntity<WebinarEnrollment>(web, HttpStatus.OK);

	}

	// API For Get list of all avaialble webinars
	@GetMapping("/get/webtrainers")
	public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam (defaultValue = "all")String data,
			@RequestParam (defaultValue = "all")String filterby) {

		Page<WebinarEnrollment> page = webService.getAllUsers(pageNo, pageSize,data,filterby);
		if (page != null) {
			return new ResponseEntity<UserPaginationDTO>(
					new UserPaginationDTO(page.getContent(), page.getTotalElements(), page.getTotalPages()),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No services available", HttpStatus.NO_CONTENT);
		}
	}

	// API For update Web training leads with comment and colour
	@PostMapping("/update/webtraining")
	public ResponseEntity<?> updateMethod(@RequestParam("id") int webId, @RequestParam("comment") String comment,
			@RequestParam("colour") String colour) {

		int result = webService.updateWebinar(colour, comment, webId);

		if (result > 0) {
			return new ResponseEntity<String>("Updated Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Sorry Unable to Update", HttpStatus.OK);
		}
	}

	// API For Deleting Webinar
	@DeleteMapping("/delete/webenrollmentinterest/{id}")
	public ResponseEntity<?> deleteEnrollmentInterest(@PathVariable int id) {

		webService.deleteEnrollmentInterest(id);

		return new ResponseEntity<String>("EnrollmentInterest Successfully deleted", HttpStatus.OK);
	}

}
