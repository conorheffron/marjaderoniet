package com.marjade.roniet;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FashionAppTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void homePage() {
		String indexHtml = this.restTemplate.getForObject("/", String.class);

		assertThat(indexHtml).contains("html lang=\"en\" ng-app=\"mainFashionApp\"");
	}

}
