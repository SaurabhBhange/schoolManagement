package com.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.LoginDateTime;

public interface LoginTimeRepo extends JpaRepository<LoginDateTime, Integer> {

	public List<LoginDateTime> findByDate(String date);

}