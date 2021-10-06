package com.school.service;

import java.io.IOException;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.school.dto.StudentDto;
import com.school.model.Course;
import com.school.model.CourseBatchMapping;
import com.school.model.GraphicCourse;
import com.school.model.ItCourse;
import com.school.model.StudentPojo;
import com.school.repository.BatchRepo;
import com.school.repository.CourseBatchMappingRepo;
import com.school.repository.CourseRepo;
import com.school.repository.GraphicCourseRepo;
import com.school.repository.ItCourseRepo;
import com.school.repository.StudentRepo;

@Service
public class StudentService implements UserDetailsService {

	@Autowired
	private StudentRepo studentRepo;

	@Autowired
	private CourseRepo courseRepo;

	@Autowired
	private ItCourseRepo itcourseRepo;

	@Autowired
	private GraphicCourseRepo graphicCourseRepo;

	@Autowired
	private BatchRepo batchRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CourseBatchMappingRepo courseBatchMappingRepo;

	@Autowired
	private AWSS3Service service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		StudentPojo user = studentRepo.findByUsername(username);
		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					new ArrayList<>());
		}
		throw new UsernameNotFoundException("User name not found Exception");
	}

	public StudentPojo signupStudentnew(MultipartFile image, String firstName, String middleName, String lastName,
			String gender, String mothersName, String mobileNumber, String parentNumber, String address, String pincode,
			String city, String state, String adharNumber, String dateOfBirth, String higherQualification, String email,
			String username, String password, String bloodGroup, int course_id, int batch_id) throws IOException {

		new SimpleDateFormat("dd/MM/yy").format(new Date());
		// byte[] img = ImageCompressorService.compressBytes(image.getBytes());
		int otp = 1000 + new Random().nextInt(9000);
		String filename = StringUtils.cleanPath(image.getOriginalFilename());
		filename = otp + "_" + filename;
		service.uploadFile(image, filename);

		// service.uploadFile(image);
		// String filename = StringUtils.cleanPath(image.getOriginalFilename());
		passwordEncoder.encode(password);
		StudentPojo user = new StudentPojo(firstName, middleName, lastName, gender, mothersName, mobileNumber,
				parentNumber, address, pincode, city, state, adharNumber, dateOfBirth, higherQualification,
				new SimpleDateFormat("dd/MM/yy").format(new Date()), null, email, username,
				passwordEncoder.encode(password), bloodGroup, null, filename);// ,course_id,batch_id);

		CourseBatchMapping cbm = new CourseBatchMapping(course_id, batch_id, user);
		courseBatchMappingRepo.save(cbm);
		try {
			return studentRepo.save(user);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	// get student by aadhar
	public StudentPojo getUserByAdharNumber(String adharNumber) {

		try {
			return studentRepo.findByAdharNumber(adharNumber);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// get student by username
	public StudentPojo getUserByUsername(String username) {

		StudentPojo u = studentRepo.findByUsername(username);

		return u;
	}

	// get student by Email
	public StudentPojo getUserByEmail(String email) {
		try {
			return studentRepo.findByEmail(email);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public Page<StudentPojo> getAllUsers(int pageNo, int pageSize) {
		try {
			Pageable paging = PageRequest.of(pageNo, pageSize);
			return studentRepo.findAllByOrderByUserIdDesc(paging);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	/*
	 * List<StudentPojo> updated = student.stream().map(st ->{ byte[] image; try {
	 * // resume = ImageCompressorService.decompress(employmen.getResume()); image =
	 * st.getFileName() == null ? ImageCompressorService.decompress(st.getImage()) :
	 * service.downloadFile(st.getFileName());
	 * 
	 * st.setImage(image); } catch (Exception e) {
	 * 
	 * e.printStackTrace(); } return st; }).collect(Collectors.toList()); return
	 * updated; } catch (Exception e) { System.out.println(e.getMessage()); return
	 * null; }
	 */

	// delete Student
	public void DeleteStudent(int userId) {
		String fileName = studentRepo.findById(userId).get().getFileName();
		if (fileName != null) {
			service.DeleteFile(fileName);
		}

		studentRepo.deleteById(userId);
	}

//Check birthdays	
	public Set<StudentPojo> getBirthdayDetails() {

		String dateOfBirth = DateTimeFormatter.ofPattern("dd/MM/").format(LocalDate.now());

		Set<StudentPojo> students = studentRepo.findByDateOfBirthLike(dateOfBirth + "%");

		return students;
	}

	// update student
	public StudentPojo updateStudent(int student_id, MultipartFile image, String firstName, String middleName,
			String lastName, String gender, String mothersName, String mobileNumber, String parentNumber,
			String address, String pincode, String city, String state, String adharNumber, String dateOfBirth,
			String higherQualification, String email, String username, String bloodGroup, int course_id, int batch_id)
			throws IOException {

		StudentPojo u = studentRepo.findById(student_id).orElse(new StudentPojo());

		u.setAddress(address);
		u.setGender(gender);
		u.setCity(city);
		u.setState(state);
		u.setPincode(pincode);
		int otp = 1000 + new Random().nextInt(9000);
		String filename = StringUtils.cleanPath(image.getOriginalFilename());
		filename = otp + "_" + filename;
		service.uploadFile(image, filename);
		u.setImage(null);
		u.setFileName(filename);
		u.setAdharNumber(adharNumber);
		u.setBloodGroup(bloodGroup);
		u.setDateOfBirth(dateOfBirth);
		u.setEmail(email);
		u.setFirstName(firstName);
		u.setHigherQualification(higherQualification);
		u.setLastName(lastName);
		u.setMiddleName(middleName);
		u.setMobileNumber(mobileNumber);
		u.setMothersName(mothersName);
		u.setParentNumber(parentNumber);
		u.setUsername(username);

		CourseBatchMapping cbm = new CourseBatchMapping(course_id, batch_id, u);
		cbm.setCourse_id(course_id);
		cbm.setBatch_id(batch_id);
		// cbm.setCourseName(null);
		// cbm.setBatchName(null);
		courseBatchMappingRepo.save(cbm);

		try {

			return studentRepo.save(u);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public List<StudentDto> getBatchStudent(int batch_id) {
		List<StudentPojo> students = studentRepo.findByBatchId(batch_id);
		List<StudentDto> output = students.stream().map(student -> {
			StudentDto s = new StudentDto();
			s.setuserId(student.getUserId());
			s.setFirstName(student.getFirstName());
			s.setLastName(student.getLastName());
			return s;
		}).collect(Collectors.toList());
		return output;
	}

//batch demo
	public List<CourseBatchMapping> getBatchByCourse(int course_id) {
		return courseBatchMappingRepo.findByCourseid(course_id);
	}

	// method for get Student by id
	public StudentPojo getStudentById(int userId) {
		try {
			return studentRepo.findById(userId).orElse(new StudentPojo());
		} catch (Exception e) {

			System.out.println(e.getMessage());
			return null;
		}
	}

	public List<CourseBatchMapping> getStudentCoursaeBatchById(int userId) {
		try {
			List<CourseBatchMapping> courses = courseBatchMappingRepo.findByUserID(userId);
			
			List<CourseBatchMapping> finalResult = new ArrayList<CourseBatchMapping>();
			if (courses.size() != 0){
				for (CourseBatchMapping e : courses) {
					int id = e.getCourse_id();
					ItCourse course = itcourseRepo.findById(id).orElse(new ItCourse());
					String courseName = course.getCourseName();
					if (courseName == null) {
						courseName = courseRepo.findById(id).orElse(new Course()).getCourseName();
						if (courseName == null) {
							courseName = graphicCourseRepo.findById(id).orElse(new GraphicCourse()).getCourseName();
						}
					}
					String batchName = batchRepo.findById(e.getBatch_id()).get().getBatch();
					CourseBatchMapping em = new CourseBatchMapping(e.getId(), e.getCourse_id(), e.getBatch_id(),
							courseName, batchName);
					finalResult.add(em);
				} // for
			} // if
			return finalResult;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}
}
