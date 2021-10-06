package com.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.model.BatchPojo;
import com.school.repository.BatchRepo;

@Service
public class BatchService {

	@Autowired
	private BatchRepo batchRepo;
	
	
	//create batch
	public BatchPojo createBatch(BatchPojo batch)
	{
		try {
			BatchPojo b = batchRepo.save(batch);
			return b;
		}catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	
	
	//get all batches
	public List<BatchPojo> getAllBatches()
	{
		try {
			return batchRepo.findAll();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}
	
	public BatchPojo getBatchByName(String batch)
	{
		try {
		BatchPojo b = batchRepo.findByBatch(batch);
		return b;
		}catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	
// service for deleting batch
	public void deleteBatch(int batch_id) {
		
		batchRepo.deleteById(batch_id);
		
	}
	
//batch by course	
//	public Set<BatchPojo> getBatchByCourse() {
//		
//		return batchRepo.findByCourse(course_id).getBatches();
//	}
	
}
