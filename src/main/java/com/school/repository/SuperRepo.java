package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.SuperAdmin;

public interface SuperRepo extends JpaRepository<SuperAdmin, Integer> {

	public SuperAdmin getUserByPassword(String password);

}
