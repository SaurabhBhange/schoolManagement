package com.school.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.school.service.AuthService;
import com.school.service.ContactusService;
import com.school.service.EmploymentService;
import com.school.service.EnrollmentService;
import com.school.service.ITEnrollmentService;
import com.school.service.StudentService;

@Component
public class ScheduleBirthday {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private ContactusService contactusService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	@Autowired
	private ITEnrollmentService itEnrollmentService;
	
	@Autowired
	private EmploymentService hiringService;
	
	@Scheduled(cron = "0 0 10 * * *")
	public void sendStudentWishes() {
		authService.getBirthdayStudent();
	}
	
	@Scheduled(cron = "0 0 10 * * *")
//	@Scheduled(cron = "0 47 18 ? * *")
	public void sendStaffWishes() {		
		authService.getBirthdayStaff();
	}
	
	//contactus form 
	@Scheduled(cron="0 15 10 1 * ?")
	public void deleteByCreatedOnBefore() 
	{	
		contactusService.performTweetCleanup();
	}
	/*
	
	//attendace form
	@Scheduled(cron="0 15 10 1 * ?")
	public void deleteByAttendance() 
	{	
		studentService.AttendanceCleanup();
	}
	
	//mechanical training form
	@Scheduled(cron="0 15 10 1 * ?")
	public void deleteByMechTraining() 
	{	
		enrollmentService.trainingCleanup();
	}
	
	//it training form
	@Scheduled(cron="0 15 10 1 * ?")
	public void deleteByItTraining() 
	{	
		itEnrollmentService.itTrainingCleanup();
	}*/
	
/*	@Scheduled(cron="0 15 10 1 * ?")
	public void deleteByHiring() 
	{	
		hiringService.HiringCleanup();
	}*/
	
	
}


