
package com.school.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.exception.RecordNotFoundException;
import com.school.model.ItCourseWorkshopMapping;
import com.school.model.ItManyCourseWorkshop;
import com.school.model.MechCourseWorkshopMapping;
import com.school.model.MechManyCourseWorkshop;
import com.school.repository.MechCourseWorkshopMappingRepo;
import com.school.repository.MechManyCourseWorkshopRepo;

@Service
public class MechManyCourseService {

	@Autowired
	private MechManyCourseWorkshopRepo workshopRepo;

	@Autowired
	private MechCourseWorkshopMappingRepo mechWorkshopMappingRepo;

	public MechManyCourseWorkshop saveData(String fullName, String email, String mobNumber, String coursePrice,
			String RzrpOrderId, String RzrpPaymentId, String RzrpSign, String PaymentStatus, String label, int value)
			throws IOException {
		try {
			MechManyCourseWorkshop work = new MechManyCourseWorkshop(fullName, email, mobNumber, coursePrice,
					RzrpOrderId, RzrpPaymentId, RzrpSign, PaymentStatus);

			MechCourseWorkshopMapping icw = new MechCourseWorkshopMapping(label, value, work);
			mechWorkshopMappingRepo.save(icw);

			return workshopRepo.save(work);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}

	}

	public List<MechManyCourseWorkshop> getData() {
		try {
			return workshopRepo.findAll();
		} catch (Exception e) {
			return null;
		}

	}

	public MechManyCourseWorkshop updateData(int workshopid, String fullName, String email, String mobNumber,
			String coursePrice, String RzrpOrderId, String RzrpPaymentId, String RzrpSign, String PaymentStatus) {
		MechManyCourseWorkshop w = workshopRepo.findById(workshopid).orElse(new MechManyCourseWorkshop());

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

	public MechManyCourseWorkshop getDataById(int workshopid) {
		try {
			return workshopRepo.findById(workshopid).orElse(new MechManyCourseWorkshop());
		} catch (RecordNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
	}
}
