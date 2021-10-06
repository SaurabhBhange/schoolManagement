package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.school.model.ExamModule;

public interface ExamModuleRepo extends JpaRepository<ExamModule, Integer> {
}
