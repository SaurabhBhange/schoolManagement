package com.school.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.model.EndofDay;
import com.school.model.UserPojo;
import com.school.repository.EodRepo;
import com.school.repository.UserRepo;

@Service
public class EodService{
	
	@Autowired
	private EodRepo eodRepo;
	
	@Autowired 
	private UserRepo userRepo;
	
public EndofDay uploadEod(EndofDay ed)
{	
		UserPojo user = userRepo.findByUsername(ed.getUser());
		try {						
			ed.setDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
			ed.setUsername(user);	
			return eodRepo.save(ed);
		}
		catch (Exception e) {
			System.out.println(e);
			return null;
			}			
		} 
}
