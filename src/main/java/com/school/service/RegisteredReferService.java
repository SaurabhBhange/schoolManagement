package com.school.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.school.model.ReferralPartner;
import com.school.model.RegisteredRefer;
import com.school.repository.RegisteredReferRepo;

@Service
public class RegisteredReferService {

	@Autowired
	private RegisteredReferRepo registeredRepo;

	@Autowired
	private ReferralPartnerService referPartnerService;

	public RegisteredRefer createUser(RegisteredRefer data) {

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		data.setRegistrationDate(df.format(date));
		data.setAdmissionstatus(false);
		data.setPaymentStatus(false);
		String coupan = data.getCoupen();

		ReferralPartner refferal = referPartnerService.getdetails(coupan);
		if (refferal != null) {
			int discount = refferal.getStudentOfferPrice();
			data.setDiscount(discount);
			data.setTotalDiscount(data.getDiscount() + data.getExtraDiscount());
		}
		return registeredRepo.save(data);
	}

	public Page<RegisteredRefer> getAllUsers(Integer pageNo, Integer pageSize) {
		try {
			Pageable paging = PageRequest.of(pageNo, pageSize);
			return registeredRepo.findAllByOrderByRegisteredreferidDesc(paging);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public Boolean updateChanges(int registeredreferid, String extraCoupen, String extraDiscount,
			String totalDiscount) {

		RegisteredRefer r = registeredRepo.findById(registeredreferid).orElse(new RegisteredRefer());
//		r.setExtraCoupen(extraCoupen);
		ReferralPartner refferal = referPartnerService.getdetails(extraCoupen);

		if (refferal == null) {
			return false;
		} else {
			int discount = refferal.getStudentOfferPrice();
			r.setExtraCoupen(refferal.getReferralcode());
			r.setExtraDiscount(discount);
			int data = r.getDiscount() + r.getExtraDiscount();
			r.setTotalDiscount(data);
			registeredRepo.save(r);
			return true;
		}
	}

	public RegisteredRefer updateAdmissionStatus(int registeredreferid, Boolean Admissionstatus, String AdmissionDate) {

		RegisteredRefer r = registeredRepo.findById(registeredreferid).orElse(new RegisteredRefer());
		r.setAdmissionstatus(Admissionstatus);
		String dateOfPost = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		r.setAdmissionDate(dateOfPost);

		return registeredRepo.save(r);
	}

	public RegisteredRefer updateComment(int registeredreferid, String message) {

		RegisteredRefer r = registeredRepo.findById(registeredreferid).orElse(new RegisteredRefer());
		r.setMessgae(message);
		return registeredRepo.save(r);
	}

	public List<RegisteredRefer> getByReferralCodeController(String coupen) {

		List<RegisteredRefer> refer = registeredRepo.findByCoupen(coupen);

		return refer;
	}

	public List<RegisteredRefer> getByCouncellorReferralCodeController(String extraCoupen) {
		List<RegisteredRefer> r = registeredRepo.findByExtraCoupen(extraCoupen);
		return r;
	}

	public List<RegisteredRefer> getByReferralCode(String coupen) {
		List<RegisteredRefer> refer = registeredRepo.getData(coupen);
		if (!refer.isEmpty()) {
			return refer;
		} else {
			return null;
		}
	}

	public List<RegisteredRefer> getByReferralCodePendingAmount(String coupen) {
		List<RegisteredRefer> r = registeredRepo.getPendingAmountData(coupen);

		if (!r.isEmpty()) {
			return r;
		} else {
			return null;
		}
	}

	public List<RegisteredRefer> getByCouncellorReferralCode(String extraCoupen) {
		List<RegisteredRefer> r = registeredRepo.getExtraData(extraCoupen);
		if (!r.isEmpty()) {
			return r;
		} else {
			return null;
		}
	}

	public List<RegisteredRefer> getByCouncellorCodePendingamt(String extraCoupen) {
		List<RegisteredRefer> r = registeredRepo.getCouncellorPendingAmountData(extraCoupen);
		if (!r.isEmpty()) {
			return r;
		} else {
			return null;
		}
	}

	public RegisteredRefer updatePaymentStatus(int registeredreferid, Boolean paymentStatus) {
		RegisteredRefer refer = registeredRepo.findById(registeredreferid).orElse(new RegisteredRefer());
		if (refer.getAdmissionstatus() && !refer.getPaymentStatus()) {
			refer.setPaymentStatus(paymentStatus);
			String dateOfPost = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
			refer.setPaymentdate(dateOfPost);
			return registeredRepo.save(refer);
		} else {
			refer.setPaymentStatus(false);
			return registeredRepo.save(refer);
		}
	}

}
