package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.ResultPojo;

public interface ResultRepo extends JpaRepository<ResultPojo, Integer>{

}
