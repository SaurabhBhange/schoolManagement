package com.school.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.model.GraphicEnrollment;

public interface GraphicEnrollmentRepo extends JpaRepository<GraphicEnrollment, Integer> {

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "update graphic_enrollment w SET w.colour=:colour,w.comment=:comment where w.id=:id", nativeQuery = true)
	public int updateGraphicEnrollment(@Param("colour") String colour, @Param("comment") String comment,
			@Param("id") int id);
	
	public Page<GraphicEnrollment> findAllByOrderByIdDesc(Pageable paging);
	
	@Query(value = "select * from graphic_enrollment as c where c.full_name LIKE %:data% Order By id DESC", nativeQuery = true)
	public Page<GraphicEnrollment> findByFullName(@Param("data") String data, Pageable paging);
	
	@Query(value = "select * from graphic_enrollment as c where c.email LIKE %:data% Order By id DESC", nativeQuery = true)
	public Page<GraphicEnrollment> findByEmail(@Param("data") String data, Pageable paging);
	
	@Query(value = "select * from graphic_enrollment as c where c.mobile LIKE %:data% Order By id DESC", nativeQuery = true)
	public Page<GraphicEnrollment> findByMobile(@Param("data") String data, Pageable paging);
	
	@Query(value = "select * from graphic_enrollment as c where c.graphic_enrollment_interest LIKE %:data% Order By id DESC", nativeQuery = true)
	public Page<GraphicEnrollment> findBygraphicEnrollmentInterest(@Param("data") String data, Pageable paging);

	@Query(value = "select * from graphic_enrollment as c where c.comment LIKE %:data% Order By id DESC", nativeQuery = true)
	public Page<GraphicEnrollment> findByComment(@Param("data") String data, Pageable paging);

}
