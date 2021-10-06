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

import com.school.model.bucketCertificate;
import com.school.repository.bucketRepo;

@Service
public class bucketCertificateService {

	@Autowired
	private bucketRepo bucketrepo;

	@Autowired
	private AWSS3Bucket service;

	public bucketCertificate addCertificate(MultipartFile img, String fullName, String issueDate, String courseName)
			throws IOException {
		try {
			String imgName = StringUtils.cleanPath(img.getOriginalFilename());
			int otp = 1000 + new Random().nextInt(9000);
			imgName = otp + "_" + imgName;
			service.uploadFile(img, imgName);

			bucketCertificate cert = new bucketCertificate(fullName, issueDate, courseName, null, imgName);

			bucketCertificate b = bucketrepo.save(cert);
			b.setImg(img.getBytes());

			return b;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	// Service for getting all users
		public Page<bucketCertificate> getAllUsers(int pageNo, int pageSize) {
			try {
				Pageable paging = PageRequest.of(pageNo, pageSize);
				return bucketrepo.findAllByOrderByIdDesc(paging);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return null;
			}
		}

	public void deleteCertificate(int id) {
		String image = bucketrepo.findById(id).get().getImgName();
		if (image != null) {
			service.DeleteFile(image);
		}
		bucketrepo.deleteById(id);
	}

}
