package com.school.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ImageAndPDFNotAvailable extends Exception{
	
	 public ImageAndPDFNotAvailable(String message) {		 
		 super(message);
	 }
}
