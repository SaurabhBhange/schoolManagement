package com.school.service;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.RawMessage;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;

@Service
public class TermsAndConditionMailService {

	private static String SENDER = "info@addvic.com";

	private String email;

	private static String SUBJECT = "Customer service contact info";

	// The full path to the file that will be attached to the email.
	// If you're using Windows, escape backslashes as shown in this variable.
	private static String ATTACHMENT = "/usr/addvicjar/terms.docx";

	// The email body for recipients with non-HTML email clients.
	private static String BODY_TEXT = "Hello,\r\n" + "Please see the attached file for a list "
			+ "of customers to contact.";

	// The HTML body of the email.
	private static String BODY_HTML = "<html>" + "<head></head>" + "<body>" + "<h1>Hello!</h1>"
			+ "<p>Please see the attached file for a " + "list of customers to contact.</p>" + "</body>" + "</html>";

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

	public static Boolean attachEmail(String email) throws MessagingException {
		AWSCredentials credentials = new BasicAWSCredentials("AKIASMVE4IQJBAHMT3B4",
				"IwunXXjM05yj/83TGnx2TUyakXv/uhryFr6DXQtX");
		com.amazonaws.services.simpleemail.AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder
				.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion("ap-south-1")
				.build();

		Session session = Session.getDefaultInstance(new Properties());

		// Create a new MimeMessage object.
		MimeMessage message = new MimeMessage(session);

		// Add subject, from and to lines.
		message.setSubject(SUBJECT, "UTF-8");
		message.setFrom(new InternetAddress(SENDER));
		message.setRecipients(RecipientType.TO, email);

		// Create a multipart/alternative child container.
		MimeMultipart msg_body = new MimeMultipart("alternative");

		// Create a wrapper for the HTML and text parts.
		MimeBodyPart wrap = new MimeBodyPart();

		// Define the text part.
		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setContent(BODY_TEXT, "text/plain; charset=UTF-8");

		// Define the HTML part.
		MimeBodyPart htmlPart = new MimeBodyPart();
		htmlPart.setContent(BODY_HTML, "text/html; charset=UTF-8");

		// Add the text and HTML parts to the child container.
		msg_body.addBodyPart(textPart);
		msg_body.addBodyPart(htmlPart);

		// Add the child container to the wrapper object.
		wrap.setContent(msg_body);

		// Create a multipart/mixed parent container.
		MimeMultipart msg = new MimeMultipart("mixed");

		// Add the parent container to the message.
		message.setContent(msg);

		// Add the multipart/alternative part to the message.
		msg.addBodyPart(wrap);

		// Define the attachment
		MimeBodyPart att = new MimeBodyPart();
		DataSource fds = new FileDataSource(ATTACHMENT);
		att.setDataHandler(new DataHandler(fds));
		att.setFileName(fds.getName());

		// Add the attachment to the message.
		msg.addBodyPart(att);

		// Try to send the email.
		try {

			// Send the email.
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			message.writeTo(outputStream);
			RawMessage rawMessage = new RawMessage(ByteBuffer.wrap(outputStream.toByteArray()));

			SendRawEmailRequest rawEmailRequest = new SendRawEmailRequest(rawMessage);

			client.sendRawEmail(rawEmailRequest);
			System.out.println("Email sent!");
			// Display an error if something goes wrong.
		} catch (Exception ex) {
			System.out.println("Email Failed");
			System.err.println("Error message: " + ex.getMessage());
			ex.printStackTrace();
		}
		return true;

	}
}
