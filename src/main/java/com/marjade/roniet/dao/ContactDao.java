package com.marjade.roniet.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.marjade.roniet.model.Contact;

@Repository
public interface ContactDao extends CrudRepository<Contact, Long> {

}