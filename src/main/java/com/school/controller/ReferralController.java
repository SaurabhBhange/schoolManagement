package com.school.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.dto.BlogPaginationDTO;
import com.school.model.ReferralPartner;
import com.school.service.ReferralPartnerService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class ReferralController {

	@Autowired
	private ReferralPartnerService referralService;

//Api for Creating Referral Partner
	@PostMapping("/permit/referralpartner/signup")
	public ResponseEntity<?> signupStudent(@RequestBody ReferralPartner data) throws IOException {
		if (referralService.getUserByEmail(data.getUsername()) != null) {
			return new ResponseEntity<String>("Email already exist", HttpStatus.OK);
		}
		ReferralPartner refer = referralService.createUser(data);
		if (refer != null) {
			return new ResponseEntity<ReferralPartner>(refer, HttpStatus.OK);
		} else {
			System.out.println("user is null");
			return new ResponseEntity<String>("unable to store Data in DB", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// APi for List Refferal partners
	@GetMapping("/referral/users")
	public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		Page<ReferralPartner> page = referralService.getAllUsers(pageNo, pageSize);
		if (page != null) {
			return new ResponseEntity<BlogPaginationDTO>(
					new BlogPaginationDTO(page.getTotalElements(), page.getContent(), page.getTotalPages()),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No services available", HttpStatus.NO_CONTENT);
		}
	}

	// API For Referral Partner Dashboard
	@GetMapping("/referr/{referralid}")
	public ResponseEntity<?> getStudentById(@PathVariable int referralid) {
		ReferralPartner user = referralService.getReferrById(referralid);
		return new ResponseEntity<ReferralPartner>(user, HttpStatus.OK);
	}

//APi For Find details by code
	@GetMapping("findby/coupen/{coupen}")
	public ResponseEntity<?> getDetailsByCoupen(@PathVariable String coupen) {
		ReferralPartner user = referralService.getDataByCoupen(coupen);
		if (user != null) {
			return new ResponseEntity<ReferralPartner>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Data not found", HttpStatus.OK);
		}
	}

	// Api for Updating Bank Details to Referral Partner Dashboard
	@PostMapping("/update/bankdata/{referralid}")
	public ResponseEntity<?> updateData(@PathVariable int referralid, @RequestParam String bankName,
			@RequestParam String AccountNo, @RequestParam String ifsc, @RequestParam String accholdername) {

		ReferralPartner user = referralService.updatePaymentDetails(referralid, bankName, AccountNo, ifsc,
				accholdername);
		if (user != null) {
			return new ResponseEntity<ReferralPartner>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Sorry Unable to Update", HttpStatus.OK);
		}
	}

	@PostMapping("/update/role/{referralid}")
	public ResponseEntity<?> update(@PathVariable int referralid, @RequestParam String role) {

		ReferralPartner user = referralService.updateRole(referralid, role);
		return new ResponseEntity<ReferralPartner>(user, HttpStatus.OK);
	}

//APi for Updating Activation Status Of Referral Partner
	@PostMapping("/update/status/{referralid}")
	public ResponseEntity<?> updateStatus(@PathVariable int referralid, @RequestParam Boolean isEnabled) {
		ReferralPartner user = referralService.updateActivationStatus(referralid, isEnabled);
		if (user != null) {
			return new ResponseEntity<ReferralPartner>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Sorry Unable to Update", HttpStatus.OK);
		}
	}

	@PostMapping("/update/partnerComission/{referralid}")
	public ResponseEntity<?> updateComission(@PathVariable int referralid, @RequestParam int partnerComission) {
		ReferralPartner user = referralService.updatePartnerComission(referralid, partnerComission);
		if (user != null) {
			return new ResponseEntity<ReferralPartner>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Sorry Unable to Update", HttpStatus.OK);
		}
	}

	@PostMapping("/update/StudentOffer/{referralid}")
	public ResponseEntity<?> updateOffer(@PathVariable int referralid, @RequestParam int studentOfferPrice) {
		ReferralPartner user = referralService.updateStudentOffer(referralid, studentOfferPrice);
		if (user != null) {
			return new ResponseEntity<ReferralPartner>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Sorry Unable to Update", HttpStatus.OK);
		}
	}

	@GetMapping("/inspect/username/{username}")
	public ResponseEntity<?> updateStatus(@PathVariable String username) {

		ReferralPartner user = referralService.getUserByEmail(username);

		if (user != null && user.getIsEnabled()) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
	}

}
