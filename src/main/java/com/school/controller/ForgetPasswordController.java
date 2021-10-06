package com.school.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.model.StudentPojo;
import com.school.model.UserPojo;
import com.school.repository.StudentRepo;
import com.school.repository.UserRepo;
import com.school.service.OtpService;
import com.school.service.mailService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class ForgetPasswordController {

	@Autowired
	private mailService mailservice;

	@Autowired
	private StudentRepo studentRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private OtpService otpService;

	// API For generating otp for forget password email verification
	@PostMapping("/generateOtp")
	public ResponseEntity<?> generateOtp(@RequestParam("email") String email) {

		int otp = otpService.generateOTP(email);
		List<String> al = new ArrayList<String>();
		al = Arrays.asList(email);
		mailservice.sendEmail(al, String.valueOf(otp));
		String result = "pass";
		return new ResponseEntity<String>("{\"status\":\"" + result + "\",\"mail\":\"" + email + "\"}", HttpStatus.OK);
	}
	
	@PostMapping("generate/referralOtp")
	public ResponseEntity<?> generatereferralOtp(@RequestParam("username") String username) {
		int otp = otpService.generateOTP(username);
		List<String> al = new ArrayList<String>();
		al = Arrays.asList(username);
		mailservice.sendEmail(al, String.valueOf(otp));
		String result = "pass";
		return new ResponseEntity<String>("{\"status\":\"" + result + "\",\"mail\":\"" + username + "\"}", HttpStatus.OK);
	}
	

	// API For generate otp for staff forget password module
	@PostMapping("/generateStaffEmailOtp")
	public ResponseEntity<?> generateStaffEmailOtp(@RequestParam("email") String email) {
		String pass = "Otp sent to mail id";
		String fail = "This Email-id Already Exist";
		UserPojo u = userRepo.findByEmail(email);
		if (u != null) {
			String result = "failed";
			String mail = email;
			return new ResponseEntity<String>(
					"{\"status\":\"" + result + "\",\"desc\":\"" + fail + "\",\"mail\":\"" + email + "\" }",
					HttpStatus.OK);
		} else {
			int otp = otpService.generateOTP(email);
			List<String> al = new ArrayList<String>();
			al = Arrays.asList(email);

			mailservice.sendEmail(al, String.valueOf(otp));
			String result = "Success";
			return new ResponseEntity<String>(
					"{\"status\":\"" + result + "\",\"desc\":\"" + pass + "\",\"mail\":\"" + email + "\"}",
					HttpStatus.OK);

		}
	}

	// API For student email otp generation
	@PostMapping("/generateStudentEmailOtp")
	public ResponseEntity<?> generateStudentEmailOtp(@RequestParam("email") String email) {

		String pass = "Otp sent to mail id";
		String fail = "This Email-id Already Exist";

		StudentPojo u = studentRepo.findByEmail(email);
		if (u != null) {
			String result = "failed";
			return new ResponseEntity<String>("{\"status\":\"" + result + "\",\"desc\":\"" + fail + "\"}",
					HttpStatus.OK);
		} else {

			int otp = otpService.generateOTP(email);
			List<String> al = new ArrayList<String>();
			al = Arrays.asList(email);

			mailservice.sendEmail(al, String.valueOf(otp));
			String result = "pass";
			return new ResponseEntity<String>(
					"{\"status\":\"" + result + "\",\"desc\":\"" + pass + "\",\"mail\":\"" + email + "\"}",
					HttpStatus.OK);
		}
	}

	// API For validating otp
	@PostMapping("/validateOtp")
	public ResponseEntity<?> validateOtp(@RequestParam("otpnum") int otpnum, @RequestParam("email") String email) {
		String SUCCESS = "Entered Otp is valid";
		String FAIL = "Entered Otp is NOT valid. Please Retry!";
		// Validate the Otp
		if (otpnum >= 0) {
			int serverOtp = otpService.getOtp(email);
			if (serverOtp > 0) {
				if (otpnum == serverOtp) {
					otpService.clearOTP(email);
					String data = "success";
					return new ResponseEntity<String>(
							"{\"status\":\"" + data + "\",\"desc\":\"" + SUCCESS + "\",\"mail\":\"" + email + "\"}",
							HttpStatus.OK);

				} else {
					String data = "unsuccessful";
					return new ResponseEntity<String>("{\"status\":\"" + data + "\",\"desc\":\"" + FAIL + "\"}",
							HttpStatus.OK);
				}
			} else {
				String data = "unsuccessful";
				return new ResponseEntity<String>("{\"status\":\"" + data + "\",\"desc\":\"" + FAIL + "\"}",
						HttpStatus.OK);
			}
		} else {
			String data = "unsuccessful";
			return new ResponseEntity<String>("{\"status\":\"" + data + "\",\"desc\":\"" + SUCCESS + "\"}",
					HttpStatus.OK);
		}
	}

}
