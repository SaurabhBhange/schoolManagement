package com.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.model.BatchPojo;
import com.school.service.BatchService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class BatchController {

	@Autowired
	private BatchService batchService;

	// API For create new Batch
	@PostMapping("/batch/create")
	public ResponseEntity<?> createBatch(@RequestBody BatchPojo batch) {
		if ("".equals(batch.getBatch()) || batch.getBatch() == null) {
			return new ResponseEntity<String>("Batch name cant be empty", HttpStatus.BAD_REQUEST);
		} else {
			BatchPojo b = batchService.getBatchByName(batch.getBatch());
			if (b != null) {
				return new ResponseEntity<String>("batch already exist", HttpStatus.OK);
			} else {
				BatchPojo bac = batchService.createBatch(batch);
				if (bac != null) {
					return new ResponseEntity<String>("batch " + bac.getBatch() + " created successfully",
							HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("unable to store Batch in DB", HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		}
	}

	//API For  get all the Available batches
	@GetMapping("/batches")
	public ResponseEntity<?> getAllBatches() {
		List<BatchPojo> batches = batchService.getAllBatches();

		if (batches != null) {
			return new ResponseEntity<List<BatchPojo>>(batches, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No batches are available in DB to display", HttpStatus.NO_CONTENT);
		}
	}

	//API For Deleting Batch according to id
	@DeleteMapping("/delete/batch/{batch_id}")
	public ResponseEntity<?> deleteBatch(@PathVariable int batch_id) {
		batchService.deleteBatch(batch_id);

		return new ResponseEntity<String>("batch deleted successfully", HttpStatus.OK);
	}

}
