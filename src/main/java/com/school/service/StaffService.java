package com.school.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.school.model.Role;
import com.school.model.UserPojo;
import com.school.model.UserRole;
import com.school.repository.RoleRepo;
import com.school.repository.UserRepo;
import com.school.repository.UserRoleRepo;

@Service
public class StaffService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private UserRoleRepo userRoleRepo;

	@Autowired
	private AWSS3Service service;

	public UserPojo updateStaff(int userId, String firstName, String middleName, String lastName, String gender,
			String mothersName, String mobileNumber, String parentNumber, String address, String pincode, String city,
			String state, String adharNumber, String dateOfBirth, String higherQualification, String email,
			String username, String bloodGroup, MultipartFile image) throws IOException {
		UserPojo u = userRepo.findById(userId).orElse(new UserPojo());
		// byte[] img = ImageCompressorService.compressBytes(image.getBytes());
		int otp = 1000 + new Random().nextInt(9000);
		String fileName = StringUtils.cleanPath(image.getOriginalFilename());
		fileName = otp + "-" + fileName;
		service.uploadFile(image, fileName);

		u.setFirstName(firstName);
		u.setMiddleName(middleName);
		u.setLastName(lastName);
		u.setGender(gender);
		u.setMothersName(mothersName);
		u.setMobileNumber(mobileNumber);
		u.setParentNumber(parentNumber);
		u.setAddress(address);
		u.setPincode(pincode);
		u.setCity(city);
		u.setState(state);
		u.setAdharNumber(adharNumber);
		u.setDateOfBirth(dateOfBirth);
		u.setHigherQualification(higherQualification);
		u.setEmail(email);
		u.setUsername(username);
		u.setBloodGroup(bloodGroup);
		u.setImage(null);
		u.setFileName(fileName);
		// u.setImage(ImageCompressorService.compressBytes(image.getBytes()));

		UserPojo user = userRepo.save(u);
		user.setImage(image.getBytes());
		return user;

	}

	public UserPojo updateStaffRole(int userId, String role) {
		Role r = roleRepo.findByRole(role);

		UserPojo user = userRepo.findById(userId).orElse(new UserPojo());

		UserRole roles = new UserRole();

		roles.setRole(r);
		roles.setUser(user);
		r.getRoles().add(roles);
		user.getRoles().add(roles);
		try {
			userRoleRepo.save(roles);
			return userRepo.save(user);
		} catch (Exception e) {
			return null;
		}

	}

	public UserPojo getUserById(int userId) {
		try {
			UserPojo user = userRepo.findById(userId).orElse(new UserPojo());
			byte[] image;
			image = user.getFileName() == null ? ImageCompressorService.decompress(user.getImage())
					: service.downloadFile(user.getFileName());
			user.setImage(image);
			return user;

		} catch (Exception e) {

			System.out.println(e.getMessage());
			return null;
		}
	}

	public List<UserPojo> getAllUsers() {
		try {
			List<UserPojo> user = (List<UserPojo>) userRepo.findAll();

			List<UserPojo> updated = user.stream().map(st -> {
				byte[] image;
				try {
					// resume = ImageCompressorService.decompress(employmen.getResume());
					image = st.getFileName() == null ? ImageCompressorService.decompress(st.getImage())
							: service.downloadFile(st.getFileName());

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

	public Set<UserPojo> getBirthdayDetails() {

		String dateOfBirth = DateTimeFormatter.ofPattern("dd/MM/").format(LocalDate.now());

		Set<UserPojo> staff = userRepo.findByDateOfBirthLike(dateOfBirth + "%");

		return staff;
	}

	public void DeleteStaff(int userId) {

		String fileName = userRepo.findById(userId).get().getFileName();
		if (fileName != null) {
			service.DeleteFile(fileName);
		}
		userRepo.deleteById(userId);
	}

}