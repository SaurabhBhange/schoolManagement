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
import com.school.model.ITEnrollmentInterest;
import com.school.repository.ItEnrollmentRepo;

@Service
public class ITEnrollmentService {

	@Autowired
	private ItEnrollmentRepo ittrainingRepo;

	@Autowired
	EnrollmentService enrollService;

	// service to create training
	public ITEnrollmentInterest createInterest(ITEnrollmentInterest enroll) {
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		Date date = new Date();
		enroll.setCreated(df.format(date));

		enrollService.sendMail(enroll.getEmail(), enroll.getFullName(), "user");
		ITEnrollmentInterest e = ittrainingRepo.save(enroll);
		return e;
	}

	// Service for getting all users
	public Page<ITEnrollmentInterest> getAllUsers(int pageNo, int pageSize,String data, String filterby) {
		try {
			Pageable paging = PageRequest.of(pageNo, pageSize);
			if (filterby.equalsIgnoreCase("fullname"))
				return ittrainingRepo.findByFullName(data, paging);

			else if (filterby.equalsIgnoreCase("email"))
				return ittrainingRepo.findByEmail(data, paging);

			else if (filterby.equalsIgnoreCase("mobile"))
				return ittrainingRepo.findByMobile(data, paging);

			else if (filterby.equalsIgnoreCase("interest"))
				return ittrainingRepo.findByitEnrollmentInterest(data, paging);

			else if (filterby.equalsIgnoreCase("comment"))
				return ittrainingRepo.findByComment(data, paging);
			else
			return ittrainingRepo.findAllByOrderByIdDesc(paging);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void deleteEnrollmentInterest(int id) {

		ittrainingRepo.deleteById(id);

	}

	public ITEnrollmentInterest updateTraining(ITEnrollmentInterest entity) throws RecordNotFoundException {
		return ittrainingRepo.save(entity);

	}

	public ITEnrollmentInterest findById(int id) {

		return ittrainingRepo.findById(id).orElse(new ITEnrollmentInterest());
	}

	public int updateItEnrollment(String colour, String comment, int id) {

		return ittrainingRepo.updateItEnrollmentInterest(colour, comment, id);
	}

}
