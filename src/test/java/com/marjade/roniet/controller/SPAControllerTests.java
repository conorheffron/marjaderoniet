package com.marjade.roniet.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SPAController.class)
public class SPAControllerTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ContactController contactController;

	@Test
	public void home() {
		this.performRequest("/");
	}

	@Test
	public void contact() {
		this.performRequest("/contact");
	}

	@Test
	public void brand() {
		this.performRequest("/brand");
	}

	@Test
	public void designer() {
		this.performRequest("/designer");
	}

	@Test
	public void editorials() {
		this.performRequest("/editorials");
	}

	@Test
	public void graduate() {
		this.performRequest("/graduate");
	}

	private void performRequest(String uriPath) {
		try {
			this.mvc.perform(get(uriPath).accept(MediaType.TEXT_PLAIN)).andExpect(status().isOk())
					.andExpect(view().name("index.html")).andExpect(forwardedUrl("index.html"));
		} catch (Exception e) {
			fail(uriPath, e);
		}
	}
}
