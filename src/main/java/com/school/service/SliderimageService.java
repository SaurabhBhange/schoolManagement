package com.school.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.school.model.SlidingImage;
import com.school.repository.SliderImageRepo;

@Service
public class SliderimageService {

	@Autowired
	private SliderImageRepo sliderRepo;

	@Autowired
	private AWSSliderBucket service;

	public SlidingImage createImage(String Title, String Description, MultipartFile image) throws IOException {

		String fileName = StringUtils.cleanPath(image.getOriginalFilename());
		service.uploadFiles(image, fileName);

		try {
			SlidingImage v = new SlidingImage(Title, Description, null, fileName);
			SlidingImage vid = sliderRepo.save(v);
			vid.setImage(image.getBytes());
			return v;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public List<SlidingImage> getData() {
		try {
			return sliderRepo.findAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void deleteImages(int id) {
		String fileName = sliderRepo.findById(id).get().getFileName();

		if (fileName != null) {
			try {
				service.DeleteFiles(fileName);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		sliderRepo.deleteById(id);
	}

}
