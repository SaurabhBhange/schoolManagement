package com.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.model.QuestionModel;

public interface QuestionRepo extends JpaRepository<QuestionModel, Integer> {

	@Query(value = "select * from question_pojo where exam_id=:exam_id", nativeQuery = true)
	public List<QuestionModel> findByExamId(@Param("exam_id")int examId);

}
