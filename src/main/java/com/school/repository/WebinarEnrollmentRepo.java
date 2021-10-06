package com.school.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.model.WebinarEnrollment;

public interface WebinarEnrollmentRepo extends JpaRepository<WebinarEnrollment, Integer> {

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "update webinar_enrollment w SET w.colour=:colour,w.comment=:comment where w.id=:id", nativeQuery = true)
	public int updateWebinarEnrollment(@Param("colour") String colour, @Param("comment") String comment,
			@Param("id") int id);

	public Page<WebinarEnrollment> findAllByOrderByIdDesc(Pageable paging);
	
	@Query(value = "select * from webinar_enrollment as c where c.full_name LIKE %:data% Order By id DESC", nativeQuery = true)
	public Page<WebinarEnrollment> findByFullName(@Param("data") String data, Pageable paging);
	
	@Query(value = "select * from webinar_enrollment as c where c.email LIKE %:data% Order By id DESC", nativeQuery = true)
	public Page<WebinarEnrollment> findByEmail(@Param("data") String data, Pageable paging);
	
	@Query(value = "select * from webinar_enrollment as c where c.mobile LIKE %:data% Order By id DESC", nativeQuery = true)
	public Page<WebinarEnrollment> findByMobile(@Param("data") String data, Pageable paging);
	
	@Query(value = "select * from webinar_enrollment as c where c.interest LIKE %:data% Order By id DESC", nativeQuery = true)
	public Page<WebinarEnrollment> findByInterest(@Param("data") String data, Pageable paging);

	@Query(value = "select * from webinar_enrollment as c where c.comment LIKE %:data% Order By id DESC", nativeQuery = true)
	public Page<WebinarEnrollment> findByComment(@Param("data") String data, Pageable paging);
	

}