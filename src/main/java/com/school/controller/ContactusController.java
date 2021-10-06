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

import com.school.model.Contactus;
import com.school.model.PaginationDto;
import com.school.service.ContactusService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class ContactusController {

	@Autowired
	private ContactusService contactusService;

	// API For creating Contact form
	@PostMapping("/contact/us")
	public Contactus feed(@RequestBody Contactus contact) {
		return contactusService.feed(contact);
	}

	
	// API For getting all contactid together
	@GetMapping("/feedbacks")
	public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam (defaultValue = "all")String data,
			@RequestParam (defaultValue = "all")String filterby) {
		Page<Contactus> page = contactusService.getAllUsers(pageNo, pageSize,data,filterby);
		if (page != null) {
			return new ResponseEntity<PaginationDto>(
					new PaginationDto(page.getTotalElements(), page.getTotalPages(), page.getContent()), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No batches are available in DB to display", HttpStatus.NO_CONTENT);
		}
	}

	// API For deleting contactid
	@DeleteMapping("/delete/contactus/{contactId}")
	public ResponseEntity<?> deleteContactus(@PathVariable int contactId) {

		contactusService.deleteContactus(contactId);

		return new ResponseEntity<String>("Contactid successfully deleted", HttpStatus.OK);
	}

}
