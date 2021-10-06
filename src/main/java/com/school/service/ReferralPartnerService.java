package com.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.school.model.ReferralAdmin;
import com.school.model.ReferralCounsellor;
import com.school.model.ReferralPartner;
import com.school.model.RegisteredRefer;
import com.school.repository.ReferCounsellorRepo;
import com.school.repository.ReferralAdminRepo;
import com.school.repository.ReferralPartnerRepo;

@Service
public class ReferralPartnerService {

	@Autowired
	private ReferralPartnerRepo referralpartnerRepo;

	@Autowired
	RegisteredReferService registeredReferService;

	@Autowired
	ReferralAdminRepo referadminRepo;
	
	@Autowired
	private ReferCounsellorRepo refercounselloRepo;

	public String createNumber() {
		int certificateNumber = (int) (Math.random() * 900000) + 100000;
		String otp = ("AV" + certificateNumber);
		return otp;
	}

	/*
	 * public UserDetails loadUserByUsername(String username) throws
	 * UsernameNotFoundException { List<GrantedAuthority> authorities = new
	 * ArrayList<>(); ReferralPartner user = findByUsername(username); if (user !=
	 * null) { return new
	 * org.springframework.security.core.userdetails.User(user.getUsername(),
	 * authorities); } return null; }
	 */
	private ReferralPartner getUserData(String username) {
		return referralpartnerRepo.findByUsername(username);
	}

	public ReferralPartner createUser(ReferralPartner referr) {
		referr.setReferralcode(createNumber());
		referr.setIsEnabled(false);
		referr.setRole("INFLUENCER");
		return referralpartnerRepo.save(referr);
	}

	public ReferralPartner getUserByEmail(String username) {
		return referralpartnerRepo.findByUsername(username);
	}
	
	public ReferralPartner getDataByCoupen(String referralcode) {
		return referralpartnerRepo.findByReferralcode(referralcode);
	}

	public ReferralAdmin getAdminDetails(String username, String password) {
		return referadminRepo.getDetailsAdmin(username, password);
	}
	
	public ReferralCounsellor getCounssellorDetails(String username, String password) {
		return refercounselloRepo.getCounsellorDetails(username, password);
	}

	public Page<ReferralPartner> getAllUsers(int pageNo, int pageSize) {
		try {
			Pageable paging = PageRequest.of(pageNo, pageSize);
			return referralpartnerRepo.findAllByOrderByReferralidDesc(paging);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public ReferralPartner getReferrById(int referralid) {
		ReferralPartner data = referralpartnerRepo.findById(referralid).orElse(new ReferralPartner());

		if (data.getRole().equalsIgnoreCase("INFLUENCER")) {
			List<RegisteredRefer> result = registeredReferService.getByReferralCode(data.getReferralcode());
			if (result != null) {
				int values = result.size() * data.getPartnerComission();
				data.setTotalRevenue(values);

				List<RegisteredRefer> proof = registeredReferService
						.getByReferralCodePendingAmount(data.getReferralcode());
				if (proof != null) {
					int bulk = proof.size() * data.getPartnerComission();
					data.setPendingamount(bulk);
					return referralpartnerRepo.save(data);
				}

			}
		}
		if (data.getRole().equalsIgnoreCase("COUNSELLOR")) {
			List<RegisteredRefer> result1 = registeredReferService.getByCouncellorReferralCode(data.getReferralcode());
			if (result1 != null) {
				int values = result1.size() * data.getPartnerComission();
				data.setTotalRevenue(values);

				List<RegisteredRefer> proof1 = registeredReferService
						.getByCouncellorCodePendingamt(data.getReferralcode());
				if (proof1 != null) {
					int bulk = proof1.size() * data.getPartnerComission();
					data.setPendingamount(bulk);
					return referralpartnerRepo.save(data);
				}

			}

		}
		return data;
	}

	/*
	 * 
	 * else if (role.equalsIgnoreCase("COUNSELLOR")) { List<RegisteredRefer> result1
	 * = registeredReferService.getByCouncellorReferralCode(data.getReferralcode());
	 * if (!result1.isEmpty()) { int values = result1.size() *
	 * data.getPartnerComission(); org_data.setTotalRevenue(values);
	 * 
	 * List<RegisteredRefer> proof1 = registeredReferService
	 * .getByCouncellorCodePendingamt(data.getReferralcode());
	 * 
	 * if (!proof1.isEmpty()) { int bulk1 = proof1.size() *
	 * data.getPartnerComission(); org_data.setPendingamount(bulk1); return data; }
	 * else { return org_data; } } return org_data; } return org_data;
	 */

	public ReferralPartner updatePaymentDetails(int referralid, String bankName, String AccountNo, String ifsc,
			String accholdername) {
		ReferralPartner refer = referralpartnerRepo.findById(referralid).orElse(new ReferralPartner());
		refer.setBankName(bankName);
		refer.setAccountNo(AccountNo);
		refer.setIfsc(ifsc);
		refer.setAccholdername(accholdername);
		return referralpartnerRepo.save(refer);
	}

	public ReferralPartner updateRole(int referralid, String role) {
		ReferralPartner refer = referralpartnerRepo.findById(referralid).orElse(new ReferralPartner());
		refer.setRole(role);
		return referralpartnerRepo.save(refer);
	}

	public ReferralPartner updateActivationStatus(int referralid, Boolean isEnabled) {
		ReferralPartner refer = referralpartnerRepo.findById(referralid).orElse(new ReferralPartner());
		refer.setIsEnabled(isEnabled);
		return referralpartnerRepo.save(refer);
	}

	public ReferralPartner updatePartnerComission(int referralid, int partnerComission) {
		ReferralPartner refer = referralpartnerRepo.findById(referralid).orElse(new ReferralPartner());
		refer.setPartnerComission(partnerComission);
		return referralpartnerRepo.save(refer);
	}

	public ReferralPartner updateStudentOffer(int referralid, int studentOfferPrice) {
		ReferralPartner refer = referralpartnerRepo.findById(referralid).orElse(new ReferralPartner());
		refer.setStudentOfferPrice(studentOfferPrice);
		return referralpartnerRepo.save(refer);
	}

	public ReferralPartner getdetails(String referralcode) {
		ReferralPartner data = referralpartnerRepo.findByReferralcode(referralcode);
		if (data != null) {
			return data;
		} else {
			return null;
		}
	}

}
