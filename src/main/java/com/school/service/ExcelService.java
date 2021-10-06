package com.school.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.model.EnrollmentInterest;
import com.school.model.ExcelHelper;
import com.school.model.GraphicEnrollment;
import com.school.model.ITEnrollmentInterest;
import com.school.model.InternEnrollemnt;
import com.school.model.WebinarEnrollment;
import com.school.repository.EnrollmentRepo;
import com.school.repository.GraphicEnrollmentRepo;
import com.school.repository.InternshipEnrollmentRepo;
import com.school.repository.ItEnrollmentRepo;
import com.school.repository.WebinarEnrollmentRepo;

@Service
public class ExcelService {
	@Autowired
	private EnrollmentRepo trainingRepo;

	@Autowired
	private ItEnrollmentRepo itRepo;

	@Autowired
	private WebinarEnrollmentRepo webinarRepo;

	@Autowired
	private InternshipEnrollmentRepo internRepo;

	@Autowired
	private GraphicEnrollmentRepo graphicRepo;

	public ByteArrayInputStream load() {
		List<EnrollmentInterest> tutorials = trainingRepo.findAll();

		ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(tutorials);
		return in;
	}

	public ByteArrayInputStream loadit() {
		List<ITEnrollmentInterest> user = itRepo.findAll();

		ByteArrayInputStream in = ExcelHelper.itToExcel(user);
		return in;
	}
	
	public ByteArrayInputStream loadWebinar() {
		List<WebinarEnrollment> user = webinarRepo.findAll();

		ByteArrayInputStream in = ExcelHelper.webinarToExcel(user);
		return in;
	}
	
	public ByteArrayInputStream loadintern() {
		List<InternEnrollemnt> user = internRepo.findAll();

		ByteArrayInputStream in = ExcelHelper.internToExcel(user);
		return in;
	}
	
	public ByteArrayInputStream loadGraphic() {
		List<GraphicEnrollment> user = graphicRepo.findAll();

		ByteArrayInputStream in = ExcelHelper.graphicToExcel(user);
		return in;
	}
	
	
	
	
}
