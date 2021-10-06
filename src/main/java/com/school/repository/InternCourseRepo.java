package com.school.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.school.model.InternshipCourse;

public interface InternCourseRepo extends JpaRepository<InternshipCourse, Integer> {

	InternshipCourse findByCourseName(String courseName);

	Set<InternshipCourse> getByCategory(String category);

	@Override
	@Query("select c from InternshipCourse c inner join fetch c.category ct")
	List<InternshipCourse> findAll();

}
