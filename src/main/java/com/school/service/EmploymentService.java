package com.school.service;

import java.io.IOException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.school.model.Employment;
import com.school.repository.EmploymentRepo;

@Service
public class EmploymentService {

	@Autowired
	private EmploymentRepo hiringRepo;

	@Autowired
	private AWSS3Service service;

	public Employment create(String fullName, String email, String phoneNo, String message, MultipartFile resume,
			String designation) throws IOException {

		int otp = 1000 + new Random().nextInt(9000);
		String fileName = StringUtils.cleanPath(resume.getOriginalFilename());
		fileName = otp + "-" + fileName;
		service.uploadFile(resume, fileName);
		try {
			// String filename= storageService.uploadFile(resume, email);
			Employment h = new Employment(fullName, email, phoneNo, message, null, designation, fileName);

			Employment e = hiringRepo.save(h);
			e.setResume(resume.getBytes());
			return e;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	
	  public Page<Employment> getAllUsers(int pageNo, int pageSize) { try {
	  Pageable paging = PageRequest.of(pageNo, pageSize); return
	  hiringRepo.findAllByOrderByHiringIdDesc(paging); } catch (Exception e) {
	  System.out.println(e.getMessage()); return null; } }
	 

	// delete
	public void deleteEmploymentForm(int hiringId) {

		hiringRepo.deleteById(hiringId);
	}

	/*
	 * public void HiringCleanup(){ //calculate date Calendar cal =
	 * Calendar.getInstance(); Date today = cal.getTime(); cal.add(Calendar.YEAR,1);
	 * Date expiryDate = cal.getTime();
	 * 
	 * //call the method hiringRepo.deleteByCreatedOnBefore(expiryDate); }
	 */
}