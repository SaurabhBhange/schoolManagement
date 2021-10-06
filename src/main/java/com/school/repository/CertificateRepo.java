package com.school.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.model.Certificate;

public interface CertificateRepo extends JpaRepository<Certificate, String> {

	public Page<Certificate> findAllByOrderByEndDateDesc(Pageable paging);

	@Query(value = "select * from certificate as c where c.course LIKE %:data%", nativeQuery = true)
	public Page<Certificate> findByCourse(@Param("data") String data, Pageable paging);

	@Query(value = "select * from certificate as c where c.full_name LIKE %:data%", nativeQuery = true)
	public Page<Certificate> findByFullName(String data, Pageable paging);

	@Query(value = "select * from certificate as c where c.certificate_number LIKE %:data%", nativeQuery = true)
	public Page<Certificate> findByCertificate(String data, Pageable paging);

	public Certificate findByCertificateNumber(String certificateNumber);

}
