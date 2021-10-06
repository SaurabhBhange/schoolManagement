package com.school.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.exception.RecordNotFoundException;
import com.school.model.MechWorkshop;
import com.school.model.Workshop;
import com.school.repository.MechWorkshopRepo;

@Service
public class MechWorkshopService {

	@Autowired
	MechWorkshopRepo mechWorkshopRepo;

	public MechWorkshop saveData(MechWorkshop data) throws IOException {
		try {
			return mechWorkshopRepo.save(data);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
	}

	public List<MechWorkshop> getData() {
		try {
			return mechWorkshopRepo.findAll();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
	}

	public MechWorkshop getDataById(int workshopId) {
		try {
			return mechWorkshopRepo.findById(workshopId).orElse(new MechWorkshop());
		} catch (RecordNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
	}

	public MechWorkshop updateData(int workshopid, String fullName, String email, String mobNumber, String courseName,
			String coursePrice, String RzrpOrderId, String RzrpPaymentId, String RzrpSign, String PaymentStatus) {
		MechWorkshop w = mechWorkshopRepo.findById(workshopid).orElse(new MechWorkshop());

		w.setFullName(fullName);
		w.setEmail(email);
		w.setMobNumber(mobNumber);
		w.setCourseName(courseName);
		w.setCoursePrice(coursePrice);
		w.setPaymentStatus(PaymentStatus);
		w.setRzrpOrderId(RzrpOrderId);
		w.setRzrpSign(RzrpSign);
		w.setRzrpPaymentId(RzrpPaymentId);

		return mechWorkshopRepo.save(w);
	}

	public void deleteData(int workshopid) {
		try {
			mechWorkshopRepo.deleteById(workshopid);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());

		}

	}
}
