package com.school.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

@Service
public class mailService {

	public static Boolean sendEmail(List<String> toadress, String data) {
		AWSCredentials credentials = new BasicAWSCredentials("AKIASMVE4IQJBAHMT3B4",
				"IwunXXjM05yj/83TGnx2TUyakXv/uhryFr6DXQtX");
		com.amazonaws.services.simpleemail.AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder
				.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion("ap-south-1")
				.build();

		Destination destination = new Destination();
		destination.setToAddresses(toadress);

		Content subject = new Content().withData("OTP VERIFICATION");
		Content textBody = new Content().withData(data);
		Body body = new Body().withText(textBody);

		Message message1 = new Message().withSubject(subject).withBody(body);

		SendEmailRequest emailRequest = new SendEmailRequest().withReplyToAddresses(toadress)
				.withSource("info@addvic.com").withDestination(destination).withMessage(message1);

		client.sendEmail(emailRequest);
		boolean flag = true;

		return flag;
	}
}
