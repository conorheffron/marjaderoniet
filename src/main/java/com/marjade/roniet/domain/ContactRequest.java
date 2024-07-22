package com.marjade.roniet.domain;

import org.joda.time.DateTime;

import com.google.gson.annotations.SerializedName;

public class ContactRequest {

	private String firstName;

	private String lastName;

	private String email;

	private String message;
	
	@SerializedName("@timestamp")
	private String timestamp = new DateTime().toString();
	
	private String recipient;

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ContactForm [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", message="
				+ message + "]";
	}
}
