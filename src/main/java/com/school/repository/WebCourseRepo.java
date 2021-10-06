package com.school.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.school.model.WebinarCourse;

public interface WebCourseRepo extends JpaRepository<WebinarCourse, Integer> {

	WebinarCourse findByCourseName(String courseName);

	Set<WebinarCourse> getCourseByCategory(String category);

	@Override
	@Query("select c from WebinarCourse c inner join fetch c.category ct")
	List<WebinarCourse> findAll();
	
	

}
