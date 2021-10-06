package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.EndofDay;

public interface EodRepo extends JpaRepository<EndofDay, Integer> {

}
