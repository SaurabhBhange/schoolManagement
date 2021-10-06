package com.school.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.model.LoginDateTime;
import com.school.model.UserPojo;
import com.school.repository.LoginTimeRepo;

@Service
public class LoginTimeService {

	@Autowired
	private LoginTimeRepo loginTimeRepo;

	public LoginDateTime getTimeDate(LoginDateTime logins, UserPojo userpojo) {

		String loginTime;
		String date;
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat tf = new SimpleDateFormat("HH:mm:ss");
		Date dateobj = new Date();
		date = df.format(dateobj);
		Calendar calobj = Calendar.getInstance();
		loginTime = tf.format(calobj.getTime());
		logins.setFirstName(userpojo.getFirstName());
		logins.setLastName(userpojo.getLastName());
		return loginTimeRepo.save(logins);

	}

	public void updatelogoutTime(LoginDateTime logins) {

		DateFormat tf = new SimpleDateFormat("HH:mm:ss");
		String logoutTime = null;
		Calendar calobj = Calendar.getInstance();
		logoutTime = tf.format(calobj.getTime());
		logins.setLogoutTime(logoutTime);
		loginTimeRepo.save(logins);

	}

	public List<LoginDateTime> getdetails() {

		return loginTimeRepo.findAll();
	}

	public List<LoginDateTime> getAllAvailableDates() {

		return loginTimeRepo.findAll();
	}

	public List<LoginDateTime> getUserByDate(String date) {

		return loginTimeRepo.findByDate(date);
	}

	public LoginDateTime addloginTime(LoginDateTime loginTime, Object u) {
		// TODO Auto-generated method stub
		return null;
	}

}
