package com.school.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.school.model.Certificate;
import com.school.repository.CertificateRepo;

@Service
public class CertificateService {

	@Autowired
	private CertificateRepo certificateRepo;

	public String createNumber() {

		int certificateNumber = (int) (Math.random() * 900000) + 100000;

		String otp = ("AV" + certificateNumber);

		return otp;
	}

	public Certificate createNumber(String fullName, String startDate, String endDate, String course) {

		Certificate c = new Certificate(createNumber(), fullName, startDate, endDate, course);

		return certificateRepo.save(c);
	}

//to get all available certificates

	public Page<Certificate> getAllUsers(int pageNo, int pageSize, String data, String filterby) {
		try {
			Pageable paging = PageRequest.of(pageNo, pageSize);

			if (filterby.equalsIgnoreCase("course"))

				return certificateRepo.findByCourse(data, paging);

			else if (filterby.equalsIgnoreCase("fullname"))

				return certificateRepo.findByFullName(data, paging);

			else if (filterby.equalsIgnoreCase("certificateNumber"))

				return certificateRepo.findByCertificate(data, paging);

			else

				return certificateRepo.findAllByOrderByEndDateDesc(paging);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

//Service for searching certificate

	public Optional<Certificate> getByCertificateNumber(String certificateNumber) {
		try {
			return certificateRepo.findById(certificateNumber);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}
	}


//Delete certificate
	public void deleteByCertificateNumber(String certificateNumber) {

		certificateRepo.deleteById(certificateNumber);

	}

}
