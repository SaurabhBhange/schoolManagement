package com.school.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.model.InternEnrollemnt;

public interface InternshipEnrollmentRepo extends JpaRepository<InternEnrollemnt, Integer> {
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "update intern_enrollemnt w SET w.colour=:colour,w.comment=:comment where w.id=:id", nativeQuery = true)
	public int updateInternEnrollmentInterest(@Param("colour") String colour, @Param("comment") String comment,
			@Param("id") int id);

	 public Page<InternEnrollemnt> findAllByOrderByIdDesc(Pageable paging);
	 
		@Query(value = "select * from intern_enrollemnt as c where c.full_name LIKE %:data% Order By id DESC", nativeQuery = true)
		public Page<InternEnrollemnt> findByFullName(@Param("data") String data, Pageable paging);
		
		@Query(value = "select * from intern_enrollemnt as c where c.email LIKE %:data% Order By id DESC", nativeQuery = true)
		public Page<InternEnrollemnt> findByEmail(@Param("data") String data, Pageable paging);
		
		@Query(value = "select * from intern_enrollemnt as c where c.mobile LIKE %:data% Order By id DESC", nativeQuery = true)
		public Page<InternEnrollemnt> findByMobile(@Param("data") String data, Pageable paging);
		
		@Query(value = "select * from intern_enrollemnt as c where c.interest LIKE %:data% Order By id DESC", nativeQuery = true)
		public Page<InternEnrollemnt> findByInterest(@Param("data") String data, Pageable paging);

		@Query(value = "select * from intern_enrollemnt as c where c.comment LIKE %:data% Order By id DESC", nativeQuery = true)
		public Page<InternEnrollemnt> findByComment(@Param("data") String data, Pageable paging);

}
