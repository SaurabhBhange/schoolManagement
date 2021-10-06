package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.ExamStudentMapping;

public interface ExamStudentMappingRepo extends JpaRepository<ExamStudentMapping, Integer>{

}
