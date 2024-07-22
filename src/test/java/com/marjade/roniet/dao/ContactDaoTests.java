package com.marjade.roniet.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.marjade.roniet.model.Contact;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ContactDaoTests {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ContactDao contactDao;

    @Test
    public void findAll() throws Exception {
        // given
        Contact contact = new Contact("test2@test.com", "test2",
                "mctester2", "hi there", "conorheffron@gmail.com");
        this.testEntityManager.merge(contact);

        // when
        Iterable<Contact> contacts = this.contactDao.findAll();

        // then
        assertThat(contacts.iterator().next().getMessage()).isEqualTo("hi there");
    }

}