package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.WebinarCategory;

public interface WebcatRepo extends JpaRepository<WebinarCategory, Integer> {

	WebinarCategory findByCategory(String category);

}
