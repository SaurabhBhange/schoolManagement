
package com.school.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.exception.RecordNotFoundException;
import com.school.model.DigitalManyCourseWorkshop;
import com.school.repository.DigitalManyCourseRepo;

@Service
public class DigitalManyCourseWorkshopService {

	@Autowired
	private DigitalManyCourseRepo digitalworkshopRepo;

	public DigitalManyCourseWorkshop saveData(DigitalManyCourseWorkshop data) throws IOException {

		try {
			return digitalworkshopRepo.save(data);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}

	}

	public List<DigitalManyCourseWorkshop> getData() {
		try {
			return digitalworkshopRepo.findAll();
		} catch (Exception e) {
			return null;
		}

	}

	public DigitalManyCourseWorkshop updateData(int workshopid, String fullName, String email, String mobNumber,
			String[] courseName, String coursePrice, String RzrpOrderId, String RzrpPaymentId, String RzrpSign,
			String PaymentStatus) {
		DigitalManyCourseWorkshop w = digitalworkshopRepo.findById(workshopid).orElse(new DigitalManyCourseWorkshop());

		w.setFullName(fullName);
		w.setEmail(email);
		w.setMobNumber(mobNumber);
		w.setCourseName(courseName);
		w.setCoursePrice(coursePrice);
		w.setPaymentStatus(PaymentStatus);
		w.setRzrpOrderId(RzrpOrderId);
		w.setRzrpSign(RzrpSign);
		w.setRzrpPaymentId(RzrpPaymentId);

		return digitalworkshopRepo.save(w);
	}

	public void deleteData(int workshopid) {
		try {
			digitalworkshopRepo.deleteById(workshopid);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());

		}

	}

	public DigitalManyCourseWorkshop getDataById(int workshopid) {
		try {
			return digitalworkshopRepo.findById(workshopid).orElse(new DigitalManyCourseWorkshop());
		} catch (RecordNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
	}
}
