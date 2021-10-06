package com.school.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.model.ITEnrollmentInterest;

public interface ItEnrollmentRepo extends JpaRepository<ITEnrollmentInterest, Integer> {

	/*
	 * @Modifying
	 * 
	 * @Transactional // Make sure to import
	 * org.springframework.transaction.annotation.Transactional public void
	 * deleteByCreatedOnBefore(Date expiryDate);
	 */

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "update itenrollment_interest w SET w.colour=:colour,w.comment=:comment where w.id=:id ", nativeQuery = true)
	public int updateItEnrollmentInterest(@Param("colour") String colour, @Param("comment") String comment,
			@Param("id") int id);

	public Page<ITEnrollmentInterest> findAllByOrderByIdDesc(Pageable paging);
	
	@Query(value = "select * from itenrollment_interest as c where c.full_name LIKE %:data% Order By id DESC", nativeQuery = true)
	public Page<ITEnrollmentInterest> findByFullName(@Param("data") String data, Pageable paging);
	
	@Query(value = "select * from itenrollment_interest as c where c.email LIKE %:data% Order By id DESC", nativeQuery = true)
	public Page<ITEnrollmentInterest> findByEmail(@Param("data") String data, Pageable paging);
	
	@Query(value = "select * from itenrollment_interest as c where c.mobile LIKE %:data% Order By id DESC", nativeQuery = true)
	public Page<ITEnrollmentInterest> findByMobile(@Param("data") String data, Pageable paging);
	
	@Query(value = "select * from itenrollment_interest as c where c.itenrollment_interest LIKE %:data% Order By id DESC", nativeQuery = true)
	public Page<ITEnrollmentInterest> findByitEnrollmentInterest(@Param("data") String data, Pageable paging);

	@Query(value = "select * from itenrollment_interest as c where c.comment LIKE %:data% Order By id DESC", nativeQuery = true)
	public Page<ITEnrollmentInterest> findByComment(@Param("data") String data, Pageable paging);

}
