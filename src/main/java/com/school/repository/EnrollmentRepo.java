package com.school.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.model.EnrollmentInterest;

public interface EnrollmentRepo extends JpaRepository<EnrollmentInterest, Integer> {

    // @Modifying
	// @Transactional // Make sure to import
	// org.springframework.transaction.annotation.Transactional
	// public void deleteByCreatedOnBefore(Date expiryDate);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "update enrollment_interest w SET w.colour=:colour,w.comment=:comment where w.id=:id", nativeQuery = true)
	public int updateEnrollmentInterest(@Param("colour") String colour, @Param("comment") String comment,
			@Param("id") int id);

	public Page<EnrollmentInterest> findAllByOrderByIdDesc(Pageable paging);
	
	@Query(value = "select * from enrollment_interest as c where c.full_name LIKE %:data% Order By id DESC", nativeQuery = true)
	public Page<EnrollmentInterest> findByFullName(@Param("data") String data, Pageable paging);
	
	@Query(value = "select * from enrollment_interest as c where c.email LIKE %:data% Order By id DESC", nativeQuery = true)
	public Page<EnrollmentInterest> findByEmail(@Param("data") String data, Pageable paging);
	
	@Query(value = "select * from enrollment_interest as c where c.mobile LIKE %:data% Order By id DESC", nativeQuery = true)
	public Page<EnrollmentInterest> findByMobile(@Param("data") String data, Pageable paging);
	
	@Query(value = "select * from enrollment_interest as c where c.interest LIKE %:data% Order By id DESC", nativeQuery = true)
	public Page<EnrollmentInterest> findByInterest(@Param("data") String data, Pageable paging);

	@Query(value = "select * from enrollment_interest as c where c.comment LIKE %:data% Order By id DESC", nativeQuery = true)
	public Page<EnrollmentInterest> findByComment(@Param("data") String data, Pageable paging);
	
	
}
