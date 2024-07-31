package com.marjade.roniet.controller;

import com.marjade.roniet.dao.ContactDao;
import com.marjade.roniet.domain.ContactRequest;
import com.marjade.roniet.domain.ContactResponse;
import com.marjade.roniet.domain.RequestStatus;
import com.marjade.roniet.model.Contact;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class ContactControllerTest {

    @InjectMocks
    private ContactController contactController;

    @Mock
    private ContactDao contactDaoMock;

    @Mock
    private Environment environmentMock;

    @Mock
    private ContactRequest contactRequestMock;

    @Mock
    private Contact contactMock;

    // test constants
    private static final String TEST_EMAIL = "conor.heffron@gmail.com";
    private static final String TEST_FIRST_NAME = "CONOR";
    private static final String TEST_LAST_NAME = "heffron";
    private static final String TEST_MSG = "hi there";
    private static final String TEST_RECIPIENT = "POSTMASTER@tec.com";

    @Test
    public void test_processContactMessage_contactRequest_null() {
        // when
        ContactResponse result = contactController.processContactMessage(null);

        // then
        verify(environmentMock, never()).getProperty("com.marjade.roniet.email.recipient");

        assertThat(result.isSuccess(), is(Boolean.FALSE));
        assertThat(result.getRequestStatus(), is(RequestStatus.FORM_INVALID));
    }

    @Test
    public void test_processContactMessage_contactRequest_invalid() {
        // when
        ContactResponse result = contactController.processContactMessage(contactRequestMock);

        // then
        verify(environmentMock, never()).getProperty("com.marjade.roniet.email.recipient");

        assertThat(result.isSuccess(), is(Boolean.FALSE));
        assertThat(result.getRequestStatus(), is(RequestStatus.FORM_INVALID));
    }

    @Test
    public void test_processContactMessage_contactRequest_save_fail() {
        // given
        when(contactRequestMock.getMessage()).thenReturn(TEST_MSG);
        when(contactRequestMock.getEmail()).thenReturn(TEST_EMAIL);
        when(contactRequestMock.getFirstName()).thenReturn(TEST_FIRST_NAME);
        when(contactRequestMock.getLastName()).thenReturn(TEST_LAST_NAME);

        // when
        ContactResponse result = contactController.processContactMessage(contactRequestMock);

        // then
        verify(environmentMock).getProperty("com.marjade.roniet.email.recipient");
        verify(contactRequestMock).getMessage();
        verify(contactRequestMock).getEmail();
        verify(contactRequestMock).getFirstName();
        verify(contactRequestMock).getLastName();

        assertThat(result.isSuccess(), is(Boolean.FALSE));
        assertThat(result.getRequestStatus(), is(RequestStatus.SAVE_FAIL));
    }

    @Test
    public void test_processContactMessage_contactRequest_save_exception() {
        // given
        when(contactRequestMock.getMessage()).thenReturn(TEST_MSG);
        when(contactRequestMock.getEmail()).thenReturn(TEST_EMAIL);
        when(contactRequestMock.getFirstName()).thenReturn(TEST_FIRST_NAME);
        when(contactRequestMock.getLastName()).thenReturn(TEST_LAST_NAME);
        when(contactDaoMock.save(any(Contact.class))).thenThrow(new RuntimeException());

        // when
        ContactResponse result = contactController.processContactMessage(contactRequestMock);

        // then
        verify(environmentMock).getProperty("com.marjade.roniet.email.recipient");
        verify(contactRequestMock).getMessage();
        verify(contactRequestMock).getEmail();
        verify(contactRequestMock).getFirstName();
        verify(contactRequestMock).getLastName();
        verify(contactDaoMock).save(any(Contact.class));

        assertThat(result.isSuccess(), is(Boolean.FALSE));
        assertThat(result.getRequestStatus(), is(RequestStatus.SAVE_FAIL));
    }

    @Test
    public void test_processContactMessage_contactRequest_success() {
        // given
        when(contactRequestMock.getMessage()).thenReturn(TEST_MSG);
        when(contactRequestMock.getEmail()).thenReturn(TEST_EMAIL);
        when(contactRequestMock.getFirstName()).thenReturn(TEST_FIRST_NAME);
        when(contactRequestMock.getLastName()).thenReturn(TEST_LAST_NAME);
        when(contactDaoMock.save(any(Contact.class))).thenReturn(contactMock);

        // when
        ContactResponse result = contactController.processContactMessage(contactRequestMock);

        // then
        verify(environmentMock).getProperty("com.marjade.roniet.email.recipient");
        verify(contactRequestMock).getMessage();
        verify(contactRequestMock).getEmail();
        verify(contactRequestMock).getFirstName();
        verify(contactRequestMock).getLastName();
        verify(contactDaoMock).save(any(Contact.class));

        assertThat(result.isSuccess(), is(Boolean.TRUE));
        assertThat(result.getRequestStatus(), is(RequestStatus.OK));
    }
}
