package com.school.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.school.exception.MailSendException;
import com.school.model.Role;
import com.school.model.StudentPojo;
import com.school.model.SuperAdmin;
import com.school.model.UserPojo;
import com.school.model.UserRole;
import com.school.repository.RoleRepo;
import com.school.repository.StudentRepo;
import com.school.repository.SuperRepo;
import com.school.repository.UserRepo;
import com.school.repository.UserRoleRepo;

@Service
public class AuthService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private UserRoleRepo userRoleRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SuperRepo superRepo;

	@Autowired
	private StudentRepo studentRepo;

	@Autowired
	StaffMailService staffMailService;

	@Autowired
	private AWSS3Service awsservice;

	/*
	 * @Override public UserDetails loadUserByUsername(String username) throws
	 * UsernameNotFoundException {
	 * 
	 * UserPojo user = userRepo.findByUsername(username);
	 * 
	 * if (user != null) { return new
	 * org.springframework.security.core.userdetails.User(user.getUsername(),
	 * user.getPassword(), new ArrayList<>()); } throw new
	 * UsernameNotFoundException("User name not found Exception"); }
	 */

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		List<GrantedAuthority> authorities = new ArrayList<>();
		UserPojo user = findByUsername(name);
		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					authorities);
		}

		StudentPojo student = findByStudentName(name);

		if (student != null) {
			return new org.springframework.security.core.userdetails.User(student.getUsername(), student.getPassword(),
					authorities);
		} else {
			throw new UsernameNotFoundException("No user Found");
		}
	}

	private UserPojo findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	private StudentPojo findByStudentName(String studentname) {
		return studentRepo.findByUsername(studentname); // given you have such method declared in the Spring Data
														// repository
	}

	public UserPojo createStudent(MultipartFile image, String firstName, String middleName, String lastName,
			String gender, String mothersName, String mobileNumber, String parentNumber, String address, String pincode,
			String city, String state, String adharNumber, String dateOfBirth, String higherQualification, String email,
			String username, String password, String bloodGroup, String role) throws IOException {
		Role r = roleRepo.findByRole(role);
		String doj;
		DateFormat df = new SimpleDateFormat("dd/MM/yy");
		Date date = new Date();
		doj = df.format(date);

		int otp = 1000 + new Random().nextInt(9000);
		String filename = StringUtils.cleanPath(image.getOriginalFilename());
		filename = otp + "_" + filename;
		awsservice.uploadFile(image, filename);

		String encry_password = passwordEncoder.encode(password);

		UserPojo user = new UserPojo(firstName, middleName, lastName, gender, mothersName, mobileNumber, parentNumber,
				address, pincode, city, state, adharNumber, dateOfBirth, higherQualification, doj, null, email,
				username, encry_password, bloodGroup, null, filename);

		UserRole userRole = new UserRole();
		try {
			userRole.setUser(user);
			userRole.setRole(r);
			r.getRoles().add(userRole);
			userRoleRepo.save(userRole);
			return userRepo.save(user);
		} catch (Exception e) {
			return null;
		}
	}

	public UserPojo createStaff(MultipartFile image, String firstName, String middleName, String lastName,
			String gender, String mothersName, String mobileNumber, String parentNumber, String address, String pincode,
			String city, String state, String adharNumber, String dateOfBirth, String higherQualification, String email,
			String username, String password, String bloodGroup, String role) throws IOException {
		Role r = roleRepo.findByRole(role);

		String doj;
		DateFormat df = new SimpleDateFormat("dd/MM/yy");
		Date date = new Date();
		doj = df.format(date);
		int otp = 1000 + new Random().nextInt(9000);
		String filename = StringUtils.cleanPath(image.getOriginalFilename());
		filename = otp + "_" + filename;
		awsservice.uploadFile(image, filename);
		String encry_password = passwordEncoder.encode(password);
		UserPojo user = new UserPojo(firstName, middleName, lastName, gender, mothersName, mobileNumber, parentNumber,
				address, pincode, city, state, adharNumber, dateOfBirth, higherQualification, doj, null, email,
				username, encry_password, bloodGroup, null, filename);
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(r);
		try {
			user.getRoles().add(userRole);
			r.getRoles().add(userRole);
			userRoleRepo.save(userRole);
			return userRepo.save(user);
		} catch (Exception e) {;
			return null;
		}
	}

	public UserPojo getUserByUsername(String username) {
		try {
			UserPojo u = userRepo.findByUsername(username);
	

			// TODO: this method to be removed after testing (for each)
			for (UserRole userRole : u.getRoles()) {
				// System.out.println(userRole.getRole().getRole());
			}
			return u;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public UserPojo getUserByAdharNumber(String adharNumber) {

		try {
			return userRepo.findByAdharNumber(adharNumber);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public UserPojo getUserByEmail(String email) {
		try {
			return userRepo.findByEmail(email);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public Page<UserPojo> getAllUsers(int pageNo, int pageSize) {
		try {
			Pageable paging = PageRequest.of(pageNo, pageSize);
			return userRepo.findAllByOrderByUserIdDesc(paging);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public List<UserPojo> getAllUsers() {
		try {
			List<UserPojo> student = (List<UserPojo>) userRepo.findAll();

			List<UserPojo> updated = student.stream().map(st -> {
				byte[] image;
				try {
					image = st.getFileName() == null ? ImageCompressorService.decompress(st.getImage())
							: awsservice.downloadFile(st.getFileName());

					st.setImage(image);
				} catch (Exception e) {

					e.printStackTrace();
				}
				return st;
			}).collect(Collectors.toList());
			return updated;
		} catch (Exception e) {
			// TODO: handle exception

			System.out.println(e.getMessage());
			return null;
		}
	}

	public SuperAdmin findUserWithEmailAndPassword(SuperAdmin superAdmin) {
		return superRepo.getUserByPassword(superAdmin.getPassword());
	}

// Student birthday wish	
	public String getBirthdayStudent() {
		String message = "";
		try {
			String dateOfBirth = DateTimeFormatter.ofPattern("dd/MM/").format(LocalDate.now());

			Set<StudentPojo> students = studentRepo.findByDateOfBirthLike(dateOfBirth + "%");
			if (students.size() != 0) {
				List<String> l1 = new ArrayList<>();
				for (StudentPojo student : students) {
					l1.add(student.getEmail());
				}

				message = service.sendEmail(l1);
			}
			return message;
		} catch (Exception e) {
			throw new MailSendException("mail not send" + e.toString());
		}
	}

	public Set<StudentPojo> getBirthdayDetails() {

		String dateOfBirth = DateTimeFormatter.ofPattern("dd/MM/").format(LocalDate.now());

		Set<StudentPojo> students = studentRepo.findByDateOfBirthLike(dateOfBirth + "%");

		return students;

	}

	// Staff birthday wish
	public String getBirthdayStaff() {
		String message = "";
		try {
			String dateOfBirth = DateTimeFormatter.ofPattern("dd/MM/").format(LocalDate.now());

			Set<UserPojo> users = userRepo.findByDateOfBirthLike(dateOfBirth + "%");
			if (users.size() != 0) {
				List<String> l1 = new ArrayList<>();
				for (UserPojo student : users) {
					l1.add(student.getEmail());
				}

				message = staffMailService.sendEmail(l1);
			}
			return message;
		} catch (Exception e) {
			throw new MailSendException("mail not send" + e.toString());
		}
	}

	/*
	 * public List<AllEmailDTO> getAllEmail(String email) {
	 * 
	 * return (List<AllEmailDTO>) Stream.of(studentRepo.findByEmail(email).st,
	 * userRepo.findByEmail(email).stream()).flatMap(Collection::stream);
	 * 
	 * }
	 */
}
