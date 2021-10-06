package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

	public Role findByRole(String role);

}
