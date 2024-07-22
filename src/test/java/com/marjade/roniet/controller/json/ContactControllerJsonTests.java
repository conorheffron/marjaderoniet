package com.marjade.roniet.controller.json;

import com.marjade.roniet.dao.ContactDao;
import com.marjade.roniet.domain.ContactRequest;
import com.marjade.roniet.domain.ContactResponse;
import com.marjade.roniet.domain.RequestStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class ContactControllerJsonTests {

	@Autowired
	private JacksonTester<ContactRequest> jacksonTesterContactRequest;

	@Autowired
	private JacksonTester<ContactResponse> jacksonTesterContactResponse;

	@MockBean
    private ContactDao contactDao;

	private ContactRequest contactRequest = new ContactRequest();

	private ContactResponse contactResponse = new ContactResponse(false, RequestStatus.EMAIL_FAILED);

	@BeforeEach
	public void init() {
		contactRequest.setEmail("test@test.com");
		contactRequest.setFirstName("test");
		contactRequest.setLastName("mctestester");
		contactRequest.setMessage("hi");
	}

	@Test
	public void serializeContactRequest() throws Exception {
		String email = contactRequest.getEmail();

		// Assert against JSON file
		assertThat(this.jacksonTesterContactRequest.write(contactRequest)).isEqualToJson("contactRequest.json");
		// JSON path based assertions
		assertThat(this.jacksonTesterContactRequest.write(contactRequest)).hasJsonPathStringValue("@.email");
		assertThat(this.jacksonTesterContactRequest.write(contactRequest)).extractingJsonPathStringValue("@.email")
				.isEqualTo(email);
	}

	@Test
	public void deserializeContactRequest() throws Exception {
		String content = "{\"firstName\":\"test\",\"lastName\":\"mctestester\",\"email\":\"test@test.com\",\"message\":\"hi\"}";
		assertThat(this.jacksonTesterContactRequest.parse(content)).isEqualTo(contactRequest);
		assertThat(this.jacksonTesterContactRequest.parseObject(content).getFirstName()).isEqualTo("test");
	}

	@Test
	public void serializeContactResponse() throws Exception {
		String requestStatus = contactResponse.getRequestStatus().name();

		// Assert against JSON file
		assertThat(this.jacksonTesterContactResponse.write(contactResponse)).isEqualToJson("contactResponse.json");
		// JSON path based assertions
		assertThat(this.jacksonTesterContactResponse.write(contactResponse)).hasJsonPathStringValue("@.requestStatus");
		assertThat(this.jacksonTesterContactResponse.write(contactResponse)).extractingJsonPathStringValue("@.requestStatus")
				.isEqualTo(requestStatus);
	}

	@Test
	public void deserializeContactResponse() throws Exception {
		String content = "{\"success\":\"false\",\"requestStatus\":\"EMAIL_FAILED\"}";
		assertThat(this.jacksonTesterContactResponse.parse(content)).isEqualTo(contactResponse);
		assertThat(this.jacksonTesterContactResponse.parseObject(content).getRequestStatus()).isEqualTo(RequestStatus.EMAIL_FAILED);
	}

}
