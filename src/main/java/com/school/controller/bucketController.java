package com.school.controller;

import java.io.IOException;

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
import org.springframework.web.multipart.MultipartFile;

import com.school.dto.ServicePaginationDTO;
import com.school.model.bucketCertificate;
import com.school.service.bucketCertificateService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/")
public class bucketController {

	@Autowired
	private bucketCertificateService service;

	// API For adding images to a bucket
	@PostMapping("bucket")
	public ResponseEntity<?> addService(@RequestParam("img") MultipartFile img,
			@RequestParam("courseName") String courseName, @RequestParam("fullName") String fullName,
			@RequestParam("issueDate") String issueDate) throws IOException {
		bucketCertificate buck = service.addCertificate(img, fullName, issueDate, courseName);
		return new ResponseEntity<bucketCertificate>(buck, HttpStatus.OK);
	}

	// API For deleting images from bucket
	@DeleteMapping("bucket/delete/{id}")
	public ResponseEntity<?> deleteCourse(@PathVariable int id) {
		service.deleteCertificate(id);

		return new ResponseEntity<String>("bucket image and certified user deleted Successfully", HttpStatus.OK);
	}

	/// API For getting Files from bucket
	@GetMapping("/get/bucketdata")
			public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "0") Integer pageNo,@RequestParam(defaultValue = "10") Integer pageSize) {
			Page<bucketCertificate> page = service.getAllUsers(pageNo, pageSize);
				if (page != null) {
					return new ResponseEntity<ServicePaginationDTO>(
							new ServicePaginationDTO(page.getTotalPages(),page.getContent(),page.getSize()), HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("No services available", HttpStatus.NO_CONTENT);
				}
		}
}
