package com.school.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.service.ExcelService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/leads")
public class ExcelController {

	@Autowired
	ExcelService fileService;

	@GetMapping("mechanical/download")
	public ResponseEntity<Resource> getFile() {

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String filename = "LEADS_" + currentDateTime + ".xlsx";

		InputStreamResource file = new InputStreamResource(fileService.load());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
	}
	
	@GetMapping("it/download")
	public ResponseEntity<Resource> getItFile() {

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String filename = "LEADS_" + currentDateTime + ".xlsx";

		InputStreamResource file = new InputStreamResource(fileService.loadit());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
	}
	

	@GetMapping("webinar/download")
	public ResponseEntity<Resource> getWebinarFile() {

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String filename = "LEADS_" + currentDateTime + ".xlsx";

		InputStreamResource file = new InputStreamResource(fileService.loadWebinar());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
	}
	
	@GetMapping("intern/download")
	public ResponseEntity<Resource> getInternFile() {

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String filename = "LEADS_" + currentDateTime + ".xlsx";

		InputStreamResource file = new InputStreamResource(fileService.loadintern());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
	}
	
	@GetMapping("graphic/download")
	public ResponseEntity<Resource> getGraphicFile() {

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String filename = "LEADS_" + currentDateTime + ".xlsx";

		InputStreamResource file = new InputStreamResource(fileService.loadGraphic());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
	}
	

}
