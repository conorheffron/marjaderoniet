package com.marjade.roniet.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SPAControllerTest {

    private SPAController spaController = new SPAController();

    private HttpServletRequest httpServletRequestMock = Mockito.mock(HttpServletRequest.class);

    @Test
    public void test_index_success() {
        // when
        String result = spaController.index(httpServletRequestMock);

        // then
        verify(httpServletRequestMock).getRequestURI();

        assertThat(result, is("index.html"));
    }

    @Test
    public void test_viewPathResolver_success() {
        // when
        String result = spaController.viewPathResolver(httpServletRequestMock);

        // then
        verify(httpServletRequestMock).getRequestURI();

        assertThat(result, is("index.html"));
    }
}
