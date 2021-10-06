package com.school.service;

import org.springframework.web.multipart.MultipartFile;

public interface AWSSliderBucket {
	
	public void uploadFiles(MultipartFile multipartFile,String fileName);
	public byte[] downloadFiles(String keyName);
	public  void DeleteFiles(String keyName);
}
