package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.dto.TermsConditionDTO;

public interface TermsRepo extends JpaRepository<TermsConditionDTO, Integer> {

}
