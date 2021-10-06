
package com.school.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.exception.RecordNotFoundException;
import com.school.model.Workshop;
import com.school.model.DigitalWorkshop;
import com.school.repository.WorkshopRepo;
import com.school.repository.DigitalWorkshopRepo;

@Service
public class DigitalWorkshopService {

	@Autowired
	private DigitalWorkshopRepo digitalWorkrepo;

	public DigitalWorkshop saveData(DigitalWorkshop data) throws IOException {
		try {
			return digitalWorkrepo.save(data);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
	}

	public List<DigitalWorkshop> getData() {
		try {
			return digitalWorkrepo.findAll();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
	}

	public DigitalWorkshop getDataById(int workshopid) {
		try {
			return digitalWorkrepo.findById(workshopid).orElse(new DigitalWorkshop());
		} catch (RecordNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
	}

	public DigitalWorkshop updateData(int workshopId, String fullName, String email, String mobNumber,
			String courseName, String coursePrice, String RzrpOrderId, String RzrpPaymentId, String RzrpSign,
			String PaymentStatus) {
		DigitalWorkshop w = digitalWorkrepo.findById(workshopId).orElse(new DigitalWorkshop());

		w.setFullName(fullName);
		w.setEmail(email);
		w.setMobNumber(mobNumber);
		w.setCourseName(courseName);
		w.setCoursePrice(coursePrice);
		w.setPaymentStatus(PaymentStatus);
		w.setRzrpOrderId(RzrpOrderId);
		w.setRzrpSign(RzrpSign);
		w.setRzrpPaymentId(RzrpPaymentId);

		return digitalWorkrepo.save(w);
	}

	public void deleteData(int workshopId) {
		try {
			digitalWorkrepo.deleteById(workshopId);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());

		}

	}
}
