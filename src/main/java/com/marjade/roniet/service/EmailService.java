package com.marjade.roniet.service;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.*;
import com.sendgrid.helpers.mail.objects.*;
import java.io.IOException;
import com.marjade.roniet.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	private Environment environment;

	public boolean sendEmail(Contact savedContact) {
		Email from = new Email(environment.getProperty("com.marjade.roniet.admin.email"));
		String subject = "Message via marjaderoniet.com from: " + savedContact.getFirstName() + " "
				+ savedContact.getLastName();
		Email to = new Email(environment.getProperty("com.marjade.roniet.email.recipient"));
		Content content = new Content("text/plain", "Messge from: " + savedContact.getEmail() + " - " + savedContact.getMessage());
		Mail mail = new Mail(from, subject, to, content);

		SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			if (response.getCode() != 200) {
				LOGGER.error(String.format("An error occurred: %s", response.getMessage()));
				return false;
			}
		} catch (IOException ex) {
			LOGGER.error("Unexpected exception occurred while sending contact email: ", e);
			return false;
		}
		LOGGER.debug("Email sent.");
		return true;
	}

}
