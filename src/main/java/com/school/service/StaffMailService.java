package com.school.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailRequest;

@Service
public class StaffMailService {

	public static String sendEmail(List<String> toAddresses) {
		AWSCredentials credentials = new BasicAWSCredentials("AKIASMVE4IQJBAHMT3B4",
				"IwunXXjM05yj/83TGnx2TUyakXv/uhryFr6DXQtX");
		com.amazonaws.services.simpleemail.AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder
				.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion("ap-south-1")
				.build();

		Destination destination = new Destination();
		destination.setToAddresses(toAddresses);
		SendTemplatedEmailRequest templatedEmailRequest = new SendTemplatedEmailRequest();
		templatedEmailRequest.withDestination(destination);
		templatedEmailRequest.withTemplate("WishTemplate");
		templatedEmailRequest.withTemplateData("{ \"name\":\"Jack\"}");
		templatedEmailRequest.withSource("info@addvic.com");
		client.sendTemplatedEmail(templatedEmailRequest);
		return "Email Sent Successfully";

	}

}
