package com.school.service;


import org.springframework.web.multipart.MultipartFile;
public interface AWSS3Service {

	public void uploadFile(MultipartFile multipartFile,String fileName);
	public byte[] downloadFile(String keyName);
	public  void DeleteFile(String keyName);
	
}
