package com.marjade.roniet.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;

public class CustomErrorControllerTest {

    private CustomErrorController spaController = new CustomErrorController();

    private HttpServletRequest httpServletRequestMock = Mockito.mock(HttpServletRequest.class);

    private HttpServletResponse httpServletResponseMock = Mockito.mock(HttpServletResponse.class);

    @Test
    public void test_getErrorPath_success() {
        // when
        String result = spaController.getErrorPath();

        // then
        assertThat(result, is("/error"));
    }

    @Test
    public void test_error_pathway() {
        // when
        ModelAndView result = spaController.error(httpServletRequestMock, httpServletResponseMock);

        // then
        assertThat(result, is(any(ModelAndView.class)));
        assertThat(result.getViewName(), is("index.html"));
    }
}
