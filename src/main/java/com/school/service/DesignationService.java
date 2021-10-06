package com.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.model.Designation;
import com.school.repository.DesignationRepo;

@Service
public class DesignationService {

	@Autowired
	private DesignationRepo interestRepo;
	
	public Designation createInterest(Designation interest) {
		
		return interestRepo.save(interest);
}

	//deleting designation
	public void deleteInterest(int designation_id) {

		interestRepo.deleteById(designation_id);
	}
	
	//getting designations
	public List<Designation> getAllNames() {
		
		try {
			return interestRepo.findAll();
		}
		catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
	

}
