package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.UserRole;

public interface UserRoleRepo extends JpaRepository<UserRole, Integer> {

}
