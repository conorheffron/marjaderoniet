package com.marjade.roniet.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.marjade.roniet.model.ContactBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.marjade.roniet.model.Contact;

import java.time.Instant;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ContactDaoTests {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ContactDao contactDao;

    // test constants
    private static final String TEST_EMAIL = "conor.heffron@gmail.com";
    private static final String TEST_FIRST_NAME = "CONOR";
    private static final String TEST_LAST_NAME = "heffron";
    private static final String TEST_MSG = "hi there";
    private static final String TEST_RECIPIENT = "POSTMASTER@tec.com";

    @Test
    public void findAll() throws Exception {
        // given
        Contact contact = new ContactBuilder()
                .withEmail(TEST_EMAIL)
                .withFirstName(TEST_FIRST_NAME)
                .withLastName(TEST_LAST_NAME)
                .withMessage(TEST_MSG)
                .withRecipient(TEST_RECIPIENT)
                .build();
        this.testEntityManager.merge(contact);

        // when
        Iterable<Contact> contacts = this.contactDao.findAll();

        // then
        Contact contactResult = contacts.iterator().next();
        assertThat(contactResult.getMessage()).isEqualTo(TEST_MSG);
        assertThat(contactResult.getEmail()).isEqualTo(TEST_EMAIL);
        assertThat(contactResult.getRecipient()).isEqualTo(TEST_RECIPIENT);
        assertThat(contactResult.getFirstName()).isEqualTo(TEST_FIRST_NAME);
        assertThat(contactResult.getLastName()).isEqualTo(TEST_LAST_NAME);
        assertThat(contactResult.getCreated()).isBefore(Instant.now());
    }

}