package com.marjade.roniet.model;

import java.util.Date;

public class ContactBuilder {

    private Integer id;

    private String email;

    private String firstName;

    private String lastName;

    private String message;

    private Date created;

    private String recipient;

    public ContactBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public ContactBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactBuilder withMessage(String message) {
        this.message = message;
        return this;
    }

    public ContactBuilder withCreated(Date created) {
        this.created = created;
        return this;
    }

    public ContactBuilder withRecipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    public Contact build() {
        Contact contact = new Contact();
        contact.setFirstName(this.firstName);
        contact.setLastName(this.lastName);
        contact.setEmail(this.email);
        contact.setRecipient(this.recipient);
        contact.setCreated(new Date());
        contact.setMessage(this.message);
        return contact;
    }
}
