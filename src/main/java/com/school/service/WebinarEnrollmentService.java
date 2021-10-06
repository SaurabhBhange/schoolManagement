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
import com.school.model.WebinarEnrollment;
import com.school.repository.WebinarEnrollmentRepo;

@Service
public class WebinarEnrollmentService {

	@Autowired
	private WebinarEnrollmentRepo webRepo;

	@Autowired
	EnrollmentService enrollService;

	// service to create training
	public WebinarEnrollment createInterest(WebinarEnrollment enroll) {
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		Date date = new Date();
		enroll.setCreated(df.format(date));
		WebinarEnrollment e = webRepo.save(enroll);
		enrollService.sendMail(enroll.getEmail(), enroll.getFullName(), "user");
		return e;
	}

	// getting all interests
	public Page<WebinarEnrollment> getAllUsers(int pageNo, int pageSize, String data, String filterby) {
		try {
			Pageable paging = PageRequest.of(pageNo, pageSize);
			if (filterby.equalsIgnoreCase("fullname"))
				return webRepo.findByFullName(data, paging);

			else if (filterby.equalsIgnoreCase("email"))
				return webRepo.findByEmail(data, paging);

			else if (filterby.equalsIgnoreCase("mobile"))
				return webRepo.findByMobile(data, paging);

			else if (filterby.equalsIgnoreCase("interest"))
				return webRepo.findByInterest(data, paging);

			else if (filterby.equalsIgnoreCase("comment"))
				return webRepo.findByComment(data, paging);
			else

				return webRepo.findAllByOrderByIdDesc(paging);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void deleteEnrollmentInterest(int id) {
		webRepo.deleteById(id);
	}

	public WebinarEnrollment updateTraining(WebinarEnrollment entity) throws RecordNotFoundException {
		return webRepo.save(entity);
	}

	public WebinarEnrollment findById(int id) {
		return webRepo.findById(id).orElse(new WebinarEnrollment());
	}

	public int updateWebinar(String colour, String comment, int id) {
		{
			return webRepo.updateWebinarEnrollment(colour, comment, id);
		}
	}

}
