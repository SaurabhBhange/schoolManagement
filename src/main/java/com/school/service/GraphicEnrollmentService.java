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

import com.school.model.GraphicEnrollment;
import com.school.repository.GraphicEnrollmentRepo;

@Service
public class GraphicEnrollmentService {

	@Autowired
	GraphicEnrollmentRepo enrollmentRepo;

	@Autowired
	EnrollmentService enrollService;

	// service to create training
	public GraphicEnrollment createInterest(GraphicEnrollment enroll) {

		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		System.out.println(df.getTimeZone()+"this is time zone");
		Date date = new Date();
		enroll.setCreated(df.format(date));
		enrollService.sendMail(enroll.getEmail(), enroll.getFullName(), "user");
		GraphicEnrollment e = enrollmentRepo.save(enroll);

		return e;
	}


	public Page<GraphicEnrollment> getAllUsers(int pageNo, int pageSize,String data, String filterby) {
		try {
			Pageable paging = PageRequest.of(pageNo, pageSize);
			if (filterby.equalsIgnoreCase("fullname"))
				return enrollmentRepo.findByFullName(data, paging);

			else if (filterby.equalsIgnoreCase("email"))
				return enrollmentRepo.findByEmail(data, paging);

			else if (filterby.equalsIgnoreCase("mobile"))
				return enrollmentRepo.findByMobile(data, paging);

			else if (filterby.equalsIgnoreCase("graphicEnrollmentInterest"))
				return enrollmentRepo.findBygraphicEnrollmentInterest(data, paging);

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

	public int updateGraphicEnrollment(String colour, String comment, int id) {

		return enrollmentRepo.updateGraphicEnrollment(colour, comment, id);
	}

	public GraphicEnrollment findById(int id) {

		return enrollmentRepo.findById(id).orElse(new GraphicEnrollment());
	}

}
