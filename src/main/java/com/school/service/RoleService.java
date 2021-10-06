package com.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.model.Role;
import com.school.repository.RoleRepo;

@Service
public class RoleService {

	@Autowired
	private RoleRepo roleRepo;

	public Role createRole(Role r) {
		try {
			return roleRepo.save(r);

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public List<Role> getAllRoles() {
		return roleRepo.findAll();
	}

	public Role getRoleByName(String role) {
		Role r;

		try {
			r = roleRepo.findByRole(role);
			return r;

		} catch (Exception e) {

			System.out.println(e);
			return null;
		}
	}

	
}
