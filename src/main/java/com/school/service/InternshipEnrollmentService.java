package com.school.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.school.exception.RecordNotFoundException;
import com.school.model.InternEnrollemnt;
import com.school.repository.InternshipEnrollmentRepo;

@Service
public class InternshipEnrollmentService {

	@Autowired
	private InternshipEnrollmentRepo enrollmentRepo;

	@Autowired
	EnrollmentService enrollService;

	// service to create training
	public InternEnrollemnt createInterest(InternEnrollemnt enroll) {

		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		Date date = new Date();
		enroll.setCreated(df.format(date));
		enrollService.sendMail(enroll.getEmail(), enroll.getFullName(), "user");

		InternEnrollemnt e = enrollmentRepo.save(enroll);

		return e;
	}

	// get data with pagination
	public Page<InternEnrollemnt> getAllUsers(int pageNo, int pageSize, String data, String filterby) {
		try {
			Pageable paging = PageRequest.of(pageNo, pageSize);
			if (filterby.equalsIgnoreCase("fullname"))
				return enrollmentRepo.findByFullName(data, paging);

			else if (filterby.equalsIgnoreCase("email"))
				return enrollmentRepo.findByEmail(data, paging);

			else if (filterby.equalsIgnoreCase("mobile"))
				return enrollmentRepo.findByMobile(data, paging);

			else if (filterby.equalsIgnoreCase("interest"))
				return enrollmentRepo.findByInterest(data, paging);

			else if (filterby.equalsIgnoreCase("comment"))
				return enrollmentRepo.findByComment(data, paging);
			else
			
			return enrollmentRepo.findAllByOrderByIdDesc(paging);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void deleteEnrollmentInterest(int id) {

		enrollmentRepo.deleteById(id);

	}

	public InternEnrollemnt updateTraining(InternEnrollemnt entity) throws RecordNotFoundException {
		return enrollmentRepo.save(entity);

	}

	public InternEnrollemnt findById(int id) {

		return enrollmentRepo.findById(id).orElse(new InternEnrollemnt());
	}

	public int updateInternEnrollment(String colour, String comment, int id) {

		return enrollmentRepo.updateInternEnrollmentInterest(colour, comment, id);
	}

}
