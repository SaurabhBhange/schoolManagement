package com.school.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.exception.RecordNotFoundException;
import com.school.model.Workshop;
import com.school.repository.WorkshopRepo;

@Service
public class WorkshopService {

	@Autowired
	private WorkshopRepo repo;

	public Workshop saveData(Workshop data) throws IOException {
		try {
			return repo.save(data);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
	}

	public List<Workshop> getData() {
		try {
			return repo.findAll();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
	}

	public Workshop getDataById(int workshopId) {
		try {
			return repo.findById(workshopId).orElse(new Workshop());
		} catch (RecordNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
	}

	public Workshop updateData(int workshopId, String fullName, String email, String mobNumber, String courseName,String coursePrice,String RzrpOrderId,
			String RzrpPaymentId, String RzrpSign, String PaymentStatus) {
		Workshop w = repo.findById(workshopId).orElse(new Workshop());

		w.setFullName(fullName);
		w.setEmail(email);
		w.setMobNumber(mobNumber);
		w.setCourseName(courseName);
		w.setCoursePrice(coursePrice);
		w.setPaymentStatus(PaymentStatus);
		w.setRzrpOrderId(RzrpOrderId);
		w.setRzrpSign(RzrpSign);
		w.setRzrpPaymentId(RzrpPaymentId);

		return repo.save(w);
	}

	public void deleteData(int workshopId) {
		try {
			repo.deleteById(workshopId);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());

		}

	}
}
