package com.school.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.school.model.Contactus;

public interface ContactusRepo extends JpaRepository<Contactus, Integer> {

	@Modifying
	@Transactional // Make sure to import org.springframework.transaction.annotation.Transactional
	public void deleteByCreatedOnBefore(Date expiryDate);

	public Page<Contactus> findAllByOrderByContactIdDesc(Pageable paging);
	
	@Modifying
	@Transactional
	public void deleteByContactId(int contactId);
	
	@Query(value = "select * from contactus as c where c.full_name LIKE %:data% Order By contact_id DESC", nativeQuery = true)
	public Page<Contactus> findByFullName(@Param("data") String data, Pageable paging);
	
	@Query(value = "select * from contactus as c where c.email LIKE %:data% Order By contact_id DESC", nativeQuery = true)
	public Page<Contactus> findByEmail(@Param("data") String data, Pageable paging);
	
	@Query(value = "select * from contactus as c where c.mobile_number LIKE %:data% Order By contact_id DESC", nativeQuery = true)
	public Page<Contactus> findByMobileNumber(@Param("data") String data, Pageable paging);

}
