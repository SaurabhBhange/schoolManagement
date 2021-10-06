package com.school.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.school.config.JwtTokenUtil;
import com.school.dto.UserDTO;
import com.school.model.CourseBatchMapping;
import com.school.model.LoginDateTime;
import com.school.model.LoginRequest;
import com.school.model.ReferralAdmin;
import com.school.model.ReferralCounsellor;
import com.school.model.ReferralPartner;
import com.school.model.StudentLoginResponse;
import com.school.model.StudentPojo;
import com.school.model.SuperAdmin;
import com.school.model.UserLoginResponse;
import com.school.model.UserPojo;
import com.school.model.UserRole;
import com.school.repository.LoginTimeRepo;
import com.school.repository.UserRepo;
import com.school.service.AuthService;
import com.school.service.LoginTimeService;
import com.school.service.ReferralPartnerService;
import com.school.service.StaffService;
import com.school.service.StudentService;
import com.school.service.mailService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/auth")
public class AuthController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
	private static final String Success = null;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private AuthService authService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private LoginTimeService loginTimeService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private StudentService studentService;

	@Autowired
	private StaffService staffService;

	@Autowired
	private ReferralPartnerService referralService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private LoginTimeRepo loginTimeRepo;

	@Autowired
	private mailService mailservice;

	// API For Councellor Signup
	@PostMapping("/permit/counsellor/signup")
	public ResponseEntity<?> signupStudent(@ModelAttribute UserDTO user) throws IOException {
		LOGGER.info("inside councellor signup");
		if (user.getRole().equalsIgnoreCase("admin") && !user.getPassword().substring(0, 5).equalsIgnoreCase("Ind14")) {
			return new ResponseEntity<String>("Sorry you can not signup with admin", HttpStatus.BAD_REQUEST);
		}
		if (authService.getUserByEmail(user.getEmail()) != null) {
			return new ResponseEntity<String>("Email already exist", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (authService.getUserByAdharNumber(user.getAdharNumber()) != null) {
			return new ResponseEntity<String>("adhar number already exist", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		UserPojo users = authService.createStudent(user.getImage(), user.getFirstName(), user.getMiddleName(),
				user.getLastName(), user.getGender(), user.getMothersName(), user.getMobileNumber(),
				user.getParentNumber(), user.getAddress(), user.getPincode(), user.getCity(), user.getState(),
				user.getAdharNumber(), user.getDateOfBirth(), user.getHigherQualification(), user.getEmail(),
				user.getUsername(), user.getPassword(), user.getBloodGroup(), user.getRole());

		if (users != null) {
			System.out.println("user is not null");
			return new ResponseEntity<UserPojo>(users, HttpStatus.OK);
		} else {
			System.out.println("user is null");
			return new ResponseEntity<String>("unable to store counsellor in DB", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// API For Student Signup
	@PostMapping("/student/signupnew")
	public ResponseEntity<?> signupStudentnew(@RequestParam("image") MultipartFile image,
			@RequestParam("firstName") String firstName, @RequestParam("middleName") String middleName,
			@RequestParam("lastName") String lastName, @RequestParam("gender") String gender,
			@RequestParam("mothersName") String mothersName, @RequestParam("mobileNumber") String mobileNumber,
			@RequestParam("parentNumber") String parentNumber, @RequestParam("address") String address,
			@RequestParam("pincode") String pincode, @RequestParam("city") String city,
			@RequestParam("state") String state, @RequestParam("adharNumber") String adharNumber,
			@RequestParam("dateOfBirth") String dateOfBirth,
			@RequestParam("higherQualification") String higherQualification, @RequestParam("email") String email,
			@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("bloodGroup") String bloodGroup, @RequestParam("course_id") int course_id,
			@RequestParam("batch_id") int batch_id) throws IOException {
		if (studentService.getUserByEmail(email) != null) {
			return new ResponseEntity<String>("Email already exist", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (studentService.getUserByAdharNumber(adharNumber) != null) {
			return new ResponseEntity<String>("adhar number already exist", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		StudentPojo users = studentService.signupStudentnew(image, firstName, middleName, lastName, gender, mothersName,
				mobileNumber, parentNumber, address, pincode, city, state, adharNumber, dateOfBirth,
				higherQualification, email, username, password, bloodGroup, course_id, batch_id);

		if (users != null) {
			return new ResponseEntity<StudentPojo>(users, HttpStatus.OK);

		} else {
			System.out.println("user is null");
			return new ResponseEntity<String>("unable to store student in DB", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/user/{username}")
	private UserPojo findname(String username) {

		return userRepo.findByUsername(username);
	}

	// API For Staff Signup
	@PostMapping("/staff/signup")
	public ResponseEntity<?> signupStaff(@ModelAttribute UserDTO dto) throws IOException {
		if ("".equals(dto.getEmail()) || dto.getEmail() == null) {
			return new ResponseEntity<String>("email cant be empty", HttpStatus.BAD_REQUEST);
		} else {
			UserPojo u = authService.getUserByEmail(dto.getEmail());
			System.out.println(dto.getEmail());

			if (u != null) {
				return new ResponseEntity<String>("Email already exist", HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				if ("".equals(dto.getAdharNumber()) || dto.getAdharNumber() == null) {
					return new ResponseEntity<String>("adhar number cant be empty", HttpStatus.BAD_REQUEST);
				} else {
					UserPojo us = authService.getUserByAdharNumber(dto.getAdharNumber());

					if (us != null) {
						return new ResponseEntity<String>("adhar number already exist",
								HttpStatus.INTERNAL_SERVER_ERROR);
					} else {
						if (dto.getPassword().length() <= 6) {
							return new ResponseEntity<String>("password length should be greater than 6",
									HttpStatus.INTERNAL_SERVER_ERROR);
						} else {

							dto.setRole("SF");

							UserPojo user = authService.createStaff(dto.getImage(), dto.getFirstName(),
									dto.getMiddleName(), dto.getLastName(), dto.getGender(), dto.getMothersName(),
									dto.getMobileNumber(), dto.getParentNumber(), dto.getAddress(), dto.getPincode(),
									dto.getCity(), dto.getState(), dto.getAdharNumber(), dto.getDateOfBirth(),
									dto.getHigherQualification(), dto.getEmail(), dto.getUsername(), dto.getPassword(),
									dto.getBloodGroup(), dto.getRole());

							if (user != null) {
								return new ResponseEntity<UserPojo>(user, HttpStatus.OK);
							} else {
								return new ResponseEntity<String>("unable to store staff in DB",
										HttpStatus.INTERNAL_SERVER_ERROR);
							}
						}
					}
				}
			}
		}
	}

	// API For Student Login
	@PostMapping("/student/login")
	public ResponseEntity<?> studentSignin(@RequestBody LoginRequest loginRequest, HttpSession session)
			throws Exception {
		String FAIL = "Invalid Credentials. Please Retry!";

		try {
			authenticate(loginRequest.getUsername(), loginRequest.getPassword());
		} catch (Exception e) {
			String data = "Fail";
			return new ResponseEntity<String>("{\"status\":\"" + data + "\",\"desc\":\"" + FAIL + "\"}", HttpStatus.OK);
		}

		final UserDetails userDetail = studentService.loadUserByUsername(loginRequest.getUsername());
		if (userDetail != null) {
			StudentPojo user = studentService.getUserByUsername(userDetail.getUsername());
			String token = jwtTokenUtil.generateToken(user);
			String data = "Success";

			StudentLoginResponse loginResponse = new StudentLoginResponse();
			loginResponse.setToken(token);
			loginResponse.setUser(user);
			loginResponse.setData(data);
			return new ResponseEntity<StudentLoginResponse>(loginResponse, HttpStatus.OK);
		} else {
			String data = "Fail";
			return new ResponseEntity<String>("{\"status\":\"" + data + "\",\"desc\":\"" + FAIL + "\"}", HttpStatus.OK);
		}
	}

//UsernamePasswordAuthenticationToken	
//API For Staff Login	
	@PostMapping("/user/login")
	public ResponseEntity<?> staffSignin(@RequestBody LoginRequest loginRequest, HttpSession session) throws Exception {
		String FAIL = "Invalid Credentials. Please Retry!";
		try {

			authenticate(loginRequest.getUsername(), loginRequest.getPassword());

		} catch (Exception e) {
			System.out.println(e.getMessage());
			String data = "Fail";
			return new ResponseEntity<String>("{\"status\":\"" + data + "\",\"desc\":\"" + FAIL + "\"}", HttpStatus.OK);
		}
		// AuthenticationProvider
		final UserDetails userDetail = authService.loadUserByUsername(loginRequest.getUsername());

		if (userDetail != null) {

			UserPojo user = authService.getUserByUsername(userDetail.getUsername());
			String token = jwtTokenUtil.generateToken(user);
			String data = "Success";
			LoginDateTime login = new LoginDateTime();
			login.setFirstName(user.getFirstName());
			login.setLastName(user.getLastName());

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat tf = new SimpleDateFormat("HH:mm:ss");
			df.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
			tf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
			Date dateobj = new Date();
			login.setDate(df.format(dateobj));
			Calendar calobj = Calendar.getInstance();
			login.setLoginTime(tf.format(calobj.getTime()));
			loginTimeRepo.save(login);

			UserLoginResponse loginResponse = new UserLoginResponse();
			for (UserRole userRole : user.getRoles()) {

				if (userRole.getRole().getRole().equalsIgnoreCase("SF")) {

					loginResponse.setToken(token);
					loginResponse.setUser(user);
					loginResponse.setRoles(user.getRoles());
					loginResponse.setData(data);
					return new ResponseEntity<UserLoginResponse>(loginResponse, HttpStatus.OK);

				} else if (userRole.getRole().getRole().equalsIgnoreCase("CR")) {

					loginResponse.setToken(token);
					loginResponse.setUser(user);
					loginResponse.setRoles(user.getRoles());
					loginResponse.setData(data);
					return new ResponseEntity<UserLoginResponse>(loginResponse, HttpStatus.OK);
				} else {
					String Data = "Fail";
					return new ResponseEntity<String>("{\"status\":\"" + Data + "\",\"desc\":\"" + FAIL + "\"}",
							HttpStatus.OK);
				}
			}
		} else {
			return new ResponseEntity<String>("unable to login", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("unable to login", HttpStatus.INTERNAL_SERVER_ERROR);
	}

//Authentication whis is used in signup user
	private void authenticate(String username, String password) throws Exception {
		try {
			System.out.println(username + "--" + password);
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			System.out.println(e);
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			System.out.println(e);
			throw new Exception("INVALID_CREDENTIALS " + e.getMessage());
		}
	}

//Admin login
	@PostMapping("/admin/login")
	public ResponseEntity<?> adminlogin(@RequestBody SuperAdmin userpojo, HttpSession session) {

		String FAIL = "Invalid Credentials. Please Retry!";
		if (userpojo.getUsername() != null && userpojo.getPassword() != null) {

			SuperAdmin userpojo2 = authService.findUserWithEmailAndPassword(userpojo);

			if (userpojo2 != null) {

				session.setAttribute("loginRequest", userpojo2);
				LoginDateTime loginTime1 = new LoginDateTime();
				SuperAdmin u = (SuperAdmin) session.getAttribute("loginRequest");
				LoginDateTime loginTime = loginTimeService.addloginTime(loginTime1, u);
				session.setAttribute("loginTime1", loginTime1);
				userpojo2.setData("Success");

				return new ResponseEntity<SuperAdmin>(userpojo2, HttpStatus.OK);
			} else {
				String check = "success";
				return new ResponseEntity<String>("{\"status\":\"" + check + "\",\"desc\":\"" + FAIL + "\"}",
						HttpStatus.OK);
			}
		} else {
			String check = "fail";
			return new ResponseEntity<String>("{\"status\":\"" + check + "\",\"desc\":\"" + FAIL + "\"}",
					HttpStatus.OK);
		}
	}

	/*
	 * @PostMapping("/referral/login") public ResponseEntity<?>
	 * staffSignin(@RequestBody ReferralLoginRequest loginRequest, HttpSession
	 * session) throws Exception { String FAIL =
	 * "Invalid Credentials. Please Retry!"; try {
	 * authenticate(loginRequest.getUsername(), loginRequest.getPassword());
	 * 
	 * } catch (Exception e) { System.out.println(e.getMessage()); String data =
	 * "Fail"; return new ResponseEntity<String>("{\"status\":\"" + data +
	 * "\",\"desc\":\"" + FAIL + "\"}", HttpStatus.OK); } // AuthenticationProvider
	 * final UserDetails userDetail =
	 * referralService.loadUserByUsername(loginRequest.getUsername());
	 * 
	 * if (userDetail != null) {
	 * 
	 * ReferralPartner user =
	 * referralService.getUserByEmail(userDetail.getUsername()); String token =
	 * jwtTokenUtil.generateToken(user); String data = "Success";
	 * 
	 * ReferralLoginResponse loginResponse = new ReferralLoginResponse();
	 * loginResponse.setToken(token); loginResponse.setUser(user);
	 * loginResponse.setData(data);
	 * 
	 * return new ResponseEntity<ReferralLoginResponse>(loginResponse,
	 * HttpStatus.OK); } else { String Data = "Fail"; return new
	 * ResponseEntity<String>("{\"status\":\"" + Data + "\",\"desc\":\"" + FAIL +
	 * "\"}", HttpStatus.OK); } }
	 */

	// API For get batch by course
	@GetMapping("/getbatchbycourse")
	public ResponseEntity<?> getBatchByCourse(@RequestParam int course_id) {

		List<CourseBatchMapping> cbm = studentService.getBatchByCourse(course_id);

		return new ResponseEntity<List<CourseBatchMapping>>(cbm, HttpStatus.OK);
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

	// API For Staff logout
	@GetMapping("user/logout")
	public ResponseEntity<String> logout(HttpSession session) {
		UserPojo userpojo = (UserPojo) session.getAttribute("loginRequest");
		if (userpojo != null) {
			LoginDateTime login = (LoginDateTime) session.getAttribute("login");
			loginTimeService.updatelogoutTime(login);
			session.removeAttribute("loginRequest");
			session.getAttribute("login");
			return new ResponseEntity<String>("Logout success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Please login", HttpStatus.BAD_REQUEST);
		}
	}

	// API For Changing Password
	@PostMapping("/changepassword")
	public String changePassword(@RequestParam("email") String email, @RequestParam("password") String password) {
		UserPojo user = this.userRepo.findByEmail(email);
		user.setPassword(this.passwordEncoder.encode(password));
		this.userRepo.save(user);
		return "password changes successfully..";
	}

	// Api For Referral login
	@PostMapping("/referral/login/{username}")
	public ResponseEntity<?> referrallogin(@PathVariable String username) {
		ReferralPartner partner = referralService.getUserByEmail(username);
		if (partner != null) {
			return new ResponseEntity<ReferralPartner>(partner, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Please login", HttpStatus.OK);
		}
	}

	@PostMapping("admin/referlogin")
	public ResponseEntity<?> referaladminlogin(@RequestParam String username, @RequestParam String password) {
		ReferralAdmin admin = referralService.getAdminDetails(username, password);
		if (admin != null) {
			return new ResponseEntity<ReferralAdmin>(admin, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Please login", HttpStatus.OK);
		}
	}

	@PostMapping("counsellor/referlogin")
	public ResponseEntity<?> referalcounsellorlogin(@RequestParam String username, @RequestParam String password) {
		ReferralCounsellor counsellor = referralService.getCounssellorDetails(username, password);
		if (counsellor != null) {
			return new ResponseEntity<ReferralCounsellor>(counsellor, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Please login", HttpStatus.OK);
		}
	}

}