package com.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.dto.TermsConditionDTO;
import com.school.repository.TermsRepo;

@Service
public class TermsService {

	@Autowired
	private TermsRepo termsRepo;

	public TermsConditionDTO saveDetails(String full_name, String email) {

		TermsConditionDTO entity = new TermsConditionDTO(full_name, email);

		return termsRepo.save(entity);
	}
}
