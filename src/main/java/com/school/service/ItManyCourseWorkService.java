package com.school.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.exception.RecordNotFoundException;
import com.school.model.ItCourseWorkshopMapping;
import com.school.model.ItManyCourseWorkshop;
import com.school.repository.ItCourseWorkshopMappingRepo;
import com.school.repository.ItManyCourseWorkshopRepo;

@Service
public class ItManyCourseWorkService {

	@Autowired
	private ItManyCourseWorkshopRepo workshopRepo;

	@Autowired
	private ItCourseWorkshopMappingRepo courseWorkshopMappingRepo;

	public ItManyCourseWorkshop saveData(String fullName, String email, String mobNumber, String coursePrice,
			String RzrpOrderId, String RzrpPaymentId, String RzrpSign, String PaymentStatus, String label, int value)
			throws IOException {
		try {
			ItManyCourseWorkshop work = new ItManyCourseWorkshop(fullName, email, mobNumber, coursePrice, RzrpOrderId,
					RzrpPaymentId, RzrpSign, PaymentStatus);

			ItCourseWorkshopMapping icw = new ItCourseWorkshopMapping(label,value,work);
			courseWorkshopMappingRepo.save(icw);

			return workshopRepo.save(work);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}

	}

	public List<ItManyCourseWorkshop> getData() {
		try {
			return workshopRepo.findAll();
		} catch (Exception e) {
			return null;
		}

	}

	public ItManyCourseWorkshop updateData(int workshopid, String fullName, String email, String mobNumber,
			String coursePrice, String RzrpOrderId, String RzrpPaymentId, String RzrpSign, String PaymentStatus) {
		ItManyCourseWorkshop w = workshopRepo.findById(workshopid).orElse(new ItManyCourseWorkshop());

		w.setFullName(fullName);
		w.setEmail(email);
		w.setMobNumber(mobNumber);
		w.setCoursePrice(coursePrice);
		w.setPaymentStatus(PaymentStatus);
		w.setRzrpOrderId(RzrpOrderId);
		w.setRzrpSign(RzrpSign);
		w.setRzrpPaymentId(RzrpPaymentId);

		return workshopRepo.save(w);
	}

	public void deleteData(int workshopid) {
		try {
			workshopRepo.deleteById(workshopid);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());

		}

	}

	public ItManyCourseWorkshop getDataById(int workshopid) {
		try {
			return workshopRepo.findById(workshopid).orElse(new ItManyCourseWorkshop());
		} catch (RecordNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
	}
}
