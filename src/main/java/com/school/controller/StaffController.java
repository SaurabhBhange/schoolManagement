package com.school.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.school.dto.UserPojoPageDto;
import com.school.model.UserPojo;
import com.school.service.AuthService;
import com.school.service.EodService;
import com.school.service.StaffService;
import com.school.service.mailService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class StaffController {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(StaffController.class);

	@Autowired
	private StaffService staffService;

	@Autowired
	private AuthService authService;

	@Autowired
	private EodService eodService;

	@Autowired
	private mailService mail;

	// API For Update Staff Details
	@SuppressWarnings("unused")
	@PutMapping("/staff/{userId}")
	public ResponseEntity<?> updateStaff(@PathVariable int userId, String firstName, String middleName, String lastName,
			String gender, String mothersName, String mobileNumber, String parentNumber, String address, String pincode,
			String city, String state, String adharNumber, String dateOfBirth, String higherQualification, String email,
			String username, String bloodGroup, MultipartFile image) throws IOException {
		UserPojo user = new UserPojo();

		if (user != null) {
			UserPojo u = staffService.updateStaff(userId, firstName, middleName, lastName, gender, mothersName,
					mobileNumber, parentNumber, address, pincode, city, state, adharNumber, dateOfBirth,
					higherQualification, email, username, bloodGroup, image);
			if (u != null) {
				return new ResponseEntity<UserPojo>(u, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("unable to update staff", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<String>("user cant be null", HttpStatus.BAD_REQUEST);
			}
	}

	// API For updating Roles
	@PutMapping("/staff/role/{userId}")
	public ResponseEntity<?> updateStaffRole(@PathVariable int userId, @RequestParam("role") String role) {
		if ("".equals(role) || role == null) {
			return new ResponseEntity<String>("role can not be empty", HttpStatus.BAD_REQUEST);
		} else {
			UserPojo u = staffService.updateStaffRole(userId, role);

			if (u != null) {
				return new ResponseEntity<UserPojo>(u, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Unable to update staff role", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	// API For getting List Of Staff
	@GetMapping("/staff/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable int userId) {
		UserPojo user = staffService.getUserById(userId);

		if (user != null) {
			return new ResponseEntity<UserPojo>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("User does not exist in DB", HttpStatus.NO_CONTENT);
		}

	}

	// API For getting staff andd counsellor list
	@GetMapping("staff/allusers")
	public ResponseEntity<?> getAllUsers() {

		List<UserPojo> userpojo = staffService.getAllUsers();

		if (userpojo != null) {
			return new ResponseEntity<List<UserPojo>>(userpojo, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No services available", HttpStatus.NO_CONTENT);
		}
	}

	// API For checking staff birthday
	@GetMapping("/checkbirthdaystaff")
	public Set<UserPojo> getStaff() {
		return staffService.getBirthdayDetails();

	}

	// API For Delete staff entries
	@DeleteMapping("/deletestaff/{userId}")
	public ResponseEntity<String> DeleteStaff(@PathVariable int userId) {

		staffService.DeleteStaff(userId);

		return new ResponseEntity<String>("Staff deleted Successfully", HttpStatus.OK);
	}

	// API For Get all available staff with pagination
	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize) {

		Page<UserPojo> page = authService.getAllUsers(pageNo, pageSize);
		if (page != null) {
			return new ResponseEntity<UserPojoPageDto>(
					new UserPojoPageDto(page.getTotalElements(), page.getTotalPages(), page.toList()), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No services available", HttpStatus.NO_CONTENT);
		}
	}

}
