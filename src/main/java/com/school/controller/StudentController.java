package com.school.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.config.JwtTokenUtil;
import com.school.dto.UserPojoPageDto;
import com.school.model.CourseBatchMapping;
import com.school.model.LoginRequest;
import com.school.model.StudentLoginResponse;
import com.school.model.StudentPojo;
import com.school.repository.StudentRepo;
import com.school.service.OtpService;
import com.school.service.StudentService;
import com.school.service.TermsAndConditionMailService;
import com.school.service.TermsService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class StudentController {

	private static final String Success = null;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private StudentService studentService;

	@Autowired
	private OtpService otpService;

	@Autowired
	private TermsAndConditionMailService mailservice;

	@Autowired
	private TermsService termsService;

	@Autowired
	private StudentRepo studentRepo;

	// API For Student Login
	@PostMapping("/student/login")
	public ResponseEntity<?> studentSignin(@RequestBody LoginRequest loginRequest, HttpSession session)
			throws Exception {
		try {
			authenticate(loginRequest.getUsername(), loginRequest.getPassword());
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<String>("INVALID_CREDENTIALS", HttpStatus.OK);
		}

		final UserDetails userDetail = studentService.loadUserByUsername(loginRequest.getUsername());
		if (userDetail != null) {
			StudentPojo user = studentService.getUserByUsername(userDetail.getUsername());

			String token = jwtTokenUtil.generateToken(user);
			String data = "Success";
			System.out.println(token);

			StudentLoginResponse loginResponse = new StudentLoginResponse();
			loginResponse.setToken(token);
			loginResponse.setUser(user);
			loginResponse.setData(data);
			return new ResponseEntity<StudentLoginResponse>(loginResponse, HttpStatus.OK);

		} else {
			return new ResponseEntity<String>("You are not a student please try with staff login page", HttpStatus.OK);
		}
	}

	// UsernamePasswordAuthenticationToken
	// authentication
	private void authenticate(String username, String password) throws Exception {
		try {
			System.out.println(username + "--" + password);
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		} catch (DisabledException e) {
			System.out.println(e);
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {

			System.out.println(e);
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	// API For get student by id
	@GetMapping("/student/{userId}")
	public ResponseEntity<?> getStudentById(@PathVariable int userId) {
		StudentPojo user = studentService.getStudentById(userId);

		if (user != null) {
			return new ResponseEntity<StudentPojo>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("User does not exist in DB", HttpStatus.NO_CONTENT);
		}
	}

	// checking Student birthday
	@GetMapping("/checkBirthday")
	public Set<StudentPojo> getStudent() {
		return studentService.getBirthdayDetails();
	}

	// API For GetList of all available list of students
	@GetMapping("/students")
	public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize) {

		Page<StudentPojo> page = studentService.getAllUsers(pageNo, pageSize);
		if (page != null) {
			return new ResponseEntity<UserPojoPageDto>(
					new UserPojoPageDto(page.getContent(), page.getTotalElements(), page.getTotalPages()),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No services available", HttpStatus.NO_CONTENT);
		}
	}

	// API For Delete Student entries
	@DeleteMapping("/deletestudent/{userId}")
	public ResponseEntity<String> DeleteStaff(@PathVariable int userId) {
		studentService.DeleteStudent(userId);
		return new ResponseEntity<String>("student deleted Successfully", HttpStatus.OK);
	}

	@GetMapping("/api/subscribed/courses/{userId}")
	public List<CourseBatchMapping> gtData(@PathVariable("userId") int userId) {
		return studentService.getStudentCoursaeBatchById(userId);
	}
}
