package com.marjade.roniet.service;

import com.sendgrid.SendGrid;
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
		String from = environment.getProperty("com.marjade.roniet.admin.email");
		String recipient = environment.getProperty("com.marjade.roniet.email.recipient");
		String subject = "Message via marjaderoniet.com from: " + savedContact.getFirstName() + " "
				+ savedContact.getLastName();
		String html = "Messge from: " + savedContact.getEmail() + " - " + savedContact.getMessage();

		SendGrid sendgrid = new SendGrid(environment.getProperty("com.marjade.roniet.email.api.key"));
		SendGrid.Email email = new SendGrid.Email();
		email.addTo(recipient);
		email.setFrom(from);
		email.setSubject(subject);
		email.setText(html);

		try {
			SendGrid.Response response = sendgrid.send(email);
			if (response.getCode() != 200) {
				LOGGER.error(String.format("An error occurred: %s", response.getMessage()));
				return false;
			}
		} catch (Exception e) {
			LOGGER.error("Unexpected exception occurred while sending contact email: ", e);
			return false;
		}
		LOGGER.debug("Email sent.");
		return true;
	}

}
