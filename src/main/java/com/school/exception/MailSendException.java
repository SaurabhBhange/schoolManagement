package com.school.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MailSendException  extends RuntimeException 
	{
	    public MailSendException(String exception) {
	        super(exception);
	    }
	}

