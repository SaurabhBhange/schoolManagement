package com.school.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.school.model.Contactus;
import com.school.repository.ContactusRepo;

@Service
public class ContactusService {

	@Autowired
	private ContactusRepo contactusRepo;

	public Contactus feed(Contactus contact) {
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date date = new Date();
		df.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		contact.setCreatedOn(df.format(date));

		return contactusRepo.save(contact);
	}

	public Page<Contactus> getAllUsers(int pageNo, int pageSize, String data, String filterby) {
		try {
			Pageable paging = PageRequest.of(pageNo, pageSize);
			if (filterby.equalsIgnoreCase("fullname"))
				return contactusRepo.findByFullName(data, paging);

			else if (filterby.equalsIgnoreCase("email"))
				return contactusRepo.findByEmail(data, paging);

			else if (filterby.equalsIgnoreCase("mobileNumber"))
				return contactusRepo.findByMobileNumber(data, paging);
			else
				return contactusRepo.findAllByOrderByContactIdDesc(paging);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void deleteContactus(int contactId) {
		try {
			contactusRepo.deleteByContactId(contactId);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	public void performTweetCleanup() {
		// calculate date
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		cal.add(Calendar.YEAR, 0);
		Date expiryDate = cal.getTime();

		// call the method
		contactusRepo.deleteByCreatedOnBefore(expiryDate);
	}
}
