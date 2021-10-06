package com.school.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.ItCourse;

public interface ItCourseRepo extends JpaRepository<ItCourse, Integer> {

	public ItCourse findByCourseName(String courseName);

	Set<ItCourse> getCourseByCategory(String category);

}
