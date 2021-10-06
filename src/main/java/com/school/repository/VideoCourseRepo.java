package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.VideoCourse;

public interface VideoCourseRepo extends JpaRepository<VideoCourse, Integer>{
	
	public VideoCourse findByCourseName(String courseName);
	

	

}
