package com.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.model.Role;
import com.school.service.RoleService;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping("/api")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	//API For creating Role 
	@PostMapping("/role/create")
	public ResponseEntity<?> createRole(@RequestBody Role r)
	{
		if("".equals(r.getRole()) || r.getRole() == null)
		{
			return new ResponseEntity<String>("role cant be empty", HttpStatus.BAD_REQUEST);
		}
		else
		{
			Role role = roleService.getRoleByName(r.getRole());
			if(role !=null)
			{
				return new ResponseEntity<String>("This Role Already Exist", HttpStatus.OK);
			}
			else
			{
				System.out.println("-->>>>");
				Role role1 = roleService.createRole(r);
					if(role1 != null)
					{
						return new ResponseEntity<String>("Role " +role1.getRole()+ " created successfully", HttpStatus.OK);
					}
					else{
						return new ResponseEntity<String>("Unable to save Role in DB", HttpStatus.INTERNAL_SERVER_ERROR);
					}
			}
		}
	}
	
	//API For getting Roles
	@GetMapping("/roles")
	public ResponseEntity<?> getAllRoles()
	{
		List<Role> roles = roleService.getAllRoles();
		
		if(roles !=null)
		{
			return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Roles are not present in the DB to display", HttpStatus.NO_CONTENT);
		}
		
		
	}
	



}
