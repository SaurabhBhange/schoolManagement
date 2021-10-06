package com.school.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.school.dto.ServicePaginationDTO;
import com.school.model.Certificate;
import com.school.service.CertificateService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class CertificateController {

	@Autowired
	private CertificateService certificateService;

	// API For creating certificate
	@PostMapping("/create/certificate")
	public Certificate createNumber(@RequestParam("fullName") String fullName,
			@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
			@RequestParam("course") String course) {

		Certificate f = certificateService.createNumber(fullName, startDate, endDate, course);
		return f;
	}

	// API For to get all certificates
	@GetMapping("/certificates")
	public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "all") String data,
			@RequestParam(defaultValue = "all") String filterby) {
		Page<Certificate> page = certificateService.getAllUsers(pageNo, pageSize, data, filterby);
		if (page != null) {
			return new ResponseEntity<ServicePaginationDTO>(
					new ServicePaginationDTO(page.getContent(), page.getTotalElements(), page.getTotalPages()),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No certificates are available in DB to display", HttpStatus.NO_CONTENT);
		}
	}

	// API For for searching details by certificateNumber
	@GetMapping("/certificate/verification/{certificateNumber}")
	public ResponseEntity<?> getByCertificateNumber(@PathVariable String certificateNumber) {
		Optional<Certificate> certified = certificateService.getByCertificateNumber(certificateNumber);

		if (certified != null) {
			return new ResponseEntity<Optional<Certificate>>(certified, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("User does not exist in DB", HttpStatus.NO_CONTENT);
		}
	}

	// API For deleting certificate
	@DeleteMapping("/delete/certification/{certificateNumber}")
	public ResponseEntity<?> delteByCertificateNumber(@PathVariable String certificateNumber) {
		certificateService.deleteByCertificateNumber(certificateNumber);
		return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);

	}

//	@GetMapping("/filter/data/{fullName}")
//	public Certificate searchData(@PathVariable String fullName) {
//
//		return certificateService.FilterData(fullName);
//	}

}
