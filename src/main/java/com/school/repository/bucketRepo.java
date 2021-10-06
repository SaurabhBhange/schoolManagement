package com.school.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.bucketCertificate;

public interface bucketRepo extends JpaRepository<bucketCertificate, Integer>{

	public Page<bucketCertificate> findAllByOrderByIdDesc(Pageable paging);

}
