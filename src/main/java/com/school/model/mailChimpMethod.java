package com.school.model;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class mailChimpMethod {
	
	public static void sendMail()
	{		
		RestTemplate restTemplate = new RestTemplate();	
	  
	   // Query parameters
	   UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("https://addvic.com/mailchimp/AddUser.php")
	           // Add query parameter
	           .queryParam("email", "sbhange67424@gmail.com")
	           .queryParam("fname", "saurabh")
	           .queryParam("lname", "bhange");
	     
	   System.out.println(builder.buildAndExpand().toUri());
   
	   String response=restTemplate.getForObject(builder.buildAndExpand().toUri(), String.class);
	      
	System.out.println("Response : "+response);      
	}	
	
	public static void main(String[] args) {
	sendMail();	
		
	}

}
