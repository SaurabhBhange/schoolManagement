package com.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.model.CourseBatchMapping;

public interface CourseBatchMappingRepo extends JpaRepository<CourseBatchMapping, Integer> {

	@Query(value = "select * from coursebatchmapping  where course_id=:course_id", nativeQuery = true)
	public List<CourseBatchMapping> findByCourseid(int course_id);
	
	@Query(value="select * from coursebatchmapping where batch_id=:batch_id",nativeQuery = true)
	public List<CourseBatchMapping> findByBatchId(int batch_id);
	
	@Query(value="select * from coursebatchmapping where user_id=:user_id",nativeQuery=true)
    public List<CourseBatchMapping> findByUserID(@Param("user_id") int userId);
	
}
