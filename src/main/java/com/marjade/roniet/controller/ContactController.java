package com.marjade.roniet.controller;

import com.marjade.roniet.dao.ContactDao;
import com.marjade.roniet.domain.ContactRequest;
import com.marjade.roniet.domain.ContactResponse;
import com.marjade.roniet.domain.RequestStatus;
import com.marjade.roniet.model.Contact;
import com.marjade.roniet.model.ContactBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContactController {

	private static Logger LOGGER = LoggerFactory.getLogger(ContactController.class);

	@Autowired
	private ContactDao contactDao;

	@Autowired
	private Environment environment;

	@RequestMapping(value = "processContactMessage", method = RequestMethod.POST)
	public @ResponseBody ContactResponse processContactMessage(@RequestBody ContactRequest contactRequest) {
		LOGGER.info("Processing contact request, {}", contactRequest);

		// form validation
		if (contactRequest == null) {
			LOGGER.error("Contact request is null, {}", contactRequest);
			return new ContactResponse(false, RequestStatus.FORM_INVALID);
		}
		String email = contactRequest.getEmail();
		String firstName = contactRequest.getFirstName();
		String lastName = contactRequest.getLastName();
		String message = contactRequest.getMessage();
		if (StringUtils.isBlank(email) || StringUtils.isBlank(firstName) || StringUtils.isBlank(lastName)
				|| StringUtils.isBlank(message)) {
			LOGGER.error("Contact request is invalid, {}", contactRequest);
			return new ContactResponse(false, RequestStatus.FORM_INVALID);
		}

		String recipient = environment.getProperty("com.marjade.roniet.email.recipient");
		ContactBuilder contactBuilder = new ContactBuilder()
				.withEmail(email)
				.withFirstName(firstName)
				.withLastName(lastName)
				.withMessage(message)
				.withRecipient(recipient);
					
		Contact savedContact = null;
		try {
			// save contact to DB
			savedContact = contactDao.save(contactBuilder.build());
			LOGGER.info("Saved contact to DB, {}", savedContact);
			if (savedContact == null) {
				return new ContactResponse(false, RequestStatus.SAVE_FAIL);
			}
		} catch (Exception e) {
			LOGGER.error("Contact request failed to save: ", e);
			return new ContactResponse(false, RequestStatus.SAVE_FAIL);
		}

		// success
		return new ContactResponse(true, RequestStatus.OK);
	}

}
