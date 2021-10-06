package com.school.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.school.model.Course;

public interface CourseRepo extends JpaRepository<Course, Integer> {

	Course findByCourseName(String courseName);

	Set<Course> getCourseByCategory(String category);

	@Override
	@Query("select c from Course c inner join fetch c.category ct")
	List<Course> findAll();


}