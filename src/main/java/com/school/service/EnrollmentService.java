package com.school.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.school.exception.RecordNotFoundException;
import com.school.model.EnrollmentInterest;
import com.school.repository.EnrollmentRepo;

@Service
public class EnrollmentService {

	@Autowired
	private EnrollmentRepo trainingRepo;

	@Value("${mailChimpurl}")
	String url;

	@Autowired
	RestTemplate restTemplate;

	// service to create training
	public EnrollmentInterest createInterest(EnrollmentInterest enroll) {

		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		Date date = new Date();

		enroll.setCreated(df.format(date));

		sendMail(enroll.getEmail(), enroll.getFullName(), "user");
		EnrollmentInterest e = trainingRepo.save(enroll);

		return e;
	}

	public void sendMail(String email, String fname, String lname) {

		// Query parameters
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
				// Add query parameter
				.queryParam("email", email).queryParam("fname", fname).queryParam("lname", lname);
		System.out.println(builder.buildAndExpand().toUri());

		String response = restTemplate.getForObject(builder.buildAndExpand().toUri(), String.class);
		System.out.println("Response : " + response);
	}

//getting all interests 

	public Page<EnrollmentInterest> getAllUsers(int pageNo, int pageSize, String data, String filterby) {
		try {
			Pageable paging = PageRequest.of(pageNo, pageSize);

			if (filterby.equalsIgnoreCase("fullname"))
				return trainingRepo.findByFullName(data, paging);

			else if (filterby.equalsIgnoreCase("email"))
				return trainingRepo.findByEmail(data, paging);

			else if (filterby.equalsIgnoreCase("mobile"))
				return trainingRepo.findByMobile(data, paging);

			else if (filterby.equalsIgnoreCase("interest"))
				return trainingRepo.findByInterest(data, paging);

			else if (filterby.equalsIgnoreCase("comment"))
				return trainingRepo.findByComment(data, paging);
			else

				return trainingRepo.findAllByOrderByIdDesc(paging);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void deleteEnrollmentInterest(int id) {
		trainingRepo.deleteById(id);
	}

	public EnrollmentInterest updateTraining(EnrollmentInterest entity) throws RecordNotFoundException {
		return trainingRepo.save(entity);
	}

	public EnrollmentInterest findById(int id) {
		return trainingRepo.findById(id).orElse(new EnrollmentInterest());
	}

	public int updateItEnrollment(String colour, String comment, int id) {

		return trainingRepo.updateEnrollmentInterest(colour, comment, id);
	}

	/*
	 * public void trainingCleanup(){ //calculate date Calendar cal =
	 * Calendar.getInstance(); Date today = cal.getTime(); cal.add(Calendar.YEAR,1);
	 * Date expiryDate = cal.getTime();
	 * 
	 * //call the method trainingRepo.deleteByCreatedOnBefore(expiryDate); }
	 */
}
