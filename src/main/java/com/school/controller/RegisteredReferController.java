package com.school.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.dto.BlogPaginationDTO;
import com.school.model.RegisteredRefer;
import com.school.service.RegisteredReferService;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/api")
public class RegisteredReferController {

	@Autowired
	private RegisteredReferService registeredService;

	@PostMapping("register/newreferral")
	public ResponseEntity<?> signupStudent(@RequestBody RegisteredRefer data) throws IOException {
		RegisteredRefer refer = registeredService.createUser(data);
		if (refer != null) {
			return new ResponseEntity<RegisteredRefer>(refer, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("unable to store Data in DB", HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("get/listofregistered")
	public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		Page<RegisteredRefer> page = registeredService.getAllUsers(pageNo, pageSize);
		if (page != null) {
			return new ResponseEntity<BlogPaginationDTO>(
					new BlogPaginationDTO(page.getContent(), page.getTotalElements(), page.getTotalPages()),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No services available", HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/update/councellordiscount/{registeredreferid}")
	public Boolean update(@PathVariable int registeredreferid, @RequestParam String extraCoupen,
			@RequestParam String extraDiscount, @RequestParam String totalDiscount) {
		Boolean r = registeredService.updateChanges(registeredreferid, extraCoupen, extraDiscount, totalDiscount);
		if (r) {
			return true;
		} else {
			return false;
		}

	}

	@PostMapping("/update/admissionstatus/{registeredreferid}")
	public ResponseEntity<?> update(@PathVariable int registeredreferid, @RequestParam String AdmissionDate,
			@RequestParam Boolean Admissionstatus) {
		RegisteredRefer r = registeredService.updateAdmissionStatus(registeredreferid, Admissionstatus, AdmissionDate);
		return new ResponseEntity<RegisteredRefer>(r, HttpStatus.OK);
	}

	@GetMapping("/listofregisteredbycoupen/{coupen}")
	public ResponseEntity<?> getdetails(@PathVariable String coupen) {
		List<RegisteredRefer> r = registeredService.getByReferralCodeController(coupen);
		if (!r.isEmpty()) {
			return new ResponseEntity<List<RegisteredRefer>>(r, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
	}

	@GetMapping("/listofregisterbycouncellor/{extraCoupen}")
	public ResponseEntity<?> getDataByReferrlcode(@PathVariable String extraCoupen) {
		List<RegisteredRefer> user = registeredService.getByCouncellorReferralCodeController(extraCoupen);
		if (!user.isEmpty()) {
			return new ResponseEntity<List<RegisteredRefer>>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("User does not exist in DB", HttpStatus.OK);
		}
	}

	@PostMapping("/update/paymentstatus/{registeredreferid}")
	public ResponseEntity<?> update(@PathVariable int registeredreferid, @RequestParam Boolean paymentStatus) {
		RegisteredRefer r = registeredService.updatePaymentStatus(registeredreferid, paymentStatus);
		return new ResponseEntity<RegisteredRefer>(r, HttpStatus.OK);
	}

	@PutMapping("/update/comment/{registeredreferid}")
	public ResponseEntity<?> updateMessage(@PathVariable int registeredreferid, @RequestParam String message) {

		RegisteredRefer msg = registeredService.updateComment(registeredreferid, message);
		return new ResponseEntity<RegisteredRefer>(msg, HttpStatus.OK);
	}

}
