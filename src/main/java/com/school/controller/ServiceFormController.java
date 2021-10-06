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

import com.school.dto.ServicePaginationDTO;
import com.school.model.ServiceForm;
import com.school.service.ServiceFormService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class ServiceFormController {

	@Autowired
	private ServiceFormService serviceformService;

	// API For creating Service Form
	@PostMapping("create/service")
	public ServiceForm createService(@RequestBody ServiceForm service) {

		return serviceformService.createService(service);

	}

	// API For Getting Services
	@GetMapping("/services")
	public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		Page<ServiceForm> page = serviceformService.getAllUsers(pageNo, pageSize);
		if (page != null) {
			return new ResponseEntity<ServicePaginationDTO>(
					new ServicePaginationDTO(page.getTotalElements(), page.getTotalPages(), page.getContent()),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No services available", HttpStatus.NO_CONTENT);
		}
	}

	// API For Deleting Services
	@DeleteMapping("/delete/serviceform/{serviceId}")
	public ResponseEntity<?> deleteServiceForm(@PathVariable int serviceId) {

		serviceformService.deleteServiceForm(serviceId);

		return new ResponseEntity<String>("Service form deleted successfully", HttpStatus.OK);
	}

}
