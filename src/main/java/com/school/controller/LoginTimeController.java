package com.school.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.model.LoginDateTime;
import com.school.model.UserPojo;
import com.school.service.LoginTimeService;

@RestController
@RequestMapping("/api")
public class LoginTimeController {

	@Autowired
	private LoginTimeService loginTimeService;

	// API For taking log in time
	@GetMapping("/logintime")
	public ResponseEntity<?> getdetail(@RequestBody LoginDateTime loginTime, HttpSession session) throws Exception

	{
		List<LoginDateTime> login = new ArrayList<LoginDateTime>();

		UserPojo userpojo = (UserPojo) session.getAttribute("loginRequest");

		if (userpojo.getRoles().equals("Admin") && "rajat@gmail.com".equals(userpojo.getEmail()))

		{
			login = loginTimeService.getdetails();
			if (login != null) {

				return new ResponseEntity<List<LoginDateTime>>(login, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("no entry exist", HttpStatus.BAD_REQUEST);
			}
		} else {

			return new ResponseEntity<String>("You don't have access to this page", HttpStatus.BAD_REQUEST);
		}

	}

	// API For getting avaialble login credentials
	@GetMapping("/logintime/{date}")
	public ResponseEntity<?> getDetails(@PathVariable String date, HttpSession session) {
		List<LoginDateTime> login = new ArrayList<LoginDateTime>();

		UserPojo userpojo = (UserPojo) session.getAttribute("loginRequest");

		if (userpojo.getRoles().equals("Admin") && "rajat@gmail.com".equals(userpojo.getEmail()))
			;
		{
			login = loginTimeService.getUserByDate(date);

			if (login != null) {
				return new ResponseEntity<List<LoginDateTime>>(login, HttpStatus.OK);

			} else {
				return new ResponseEntity<String>("no entry exist", HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}

	}

}
