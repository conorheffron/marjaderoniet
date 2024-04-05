package com.marjade.roniet.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
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
        this.testEntityManager.persist(new Contact("test@test.com", "test", "mctester", "hi", "conorheffron@gmail.com"));
        
        Iterable<Contact> contacts = this.contactDao.findAll();
        
        assertThat(contacts.iterator().next().getMessage()).isEqualTo("hi");
    }

}