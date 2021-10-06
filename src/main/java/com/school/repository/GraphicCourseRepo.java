package com.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.school.model.GraphicCourse;

public interface GraphicCourseRepo extends JpaRepository<GraphicCourse, Integer> {

	public GraphicCourse findByCourseName(String courseName);

	@Override
	@Query("select c from GraphicCourse c inner join fetch c.category ct")
	List<GraphicCourse> findAll();

}
