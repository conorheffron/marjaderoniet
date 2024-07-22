package com.marjade.roniet.controller.json;

import com.marjade.roniet.dao.ContactDao;
import com.marjade.roniet.domain.ContactRequest;
import com.marjade.roniet.domain.ContactResponse;
import com.marjade.roniet.domain.RequestStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class ContactControllerJsonTests {

	@Autowired
	private JacksonTester<ContactRequest> jacksonTesterContactRequest;

	@Autowired
	private JacksonTester<ContactResponse> jacksonTesterContactResponse;

	@MockBean
    private ContactDao contactDao;

	private ContactResponse contactResponse = new ContactResponse(false, RequestStatus.EMAIL_FAILED);

	@Test
	public void serializeContactRequest() throws Exception {
		ContactRequest contactRequest = new ContactRequest();
		contactRequest.setEmail("test@test.com");
		contactRequest.setFirstName("test");
		contactRequest.setLastName("mctestester");
		contactRequest.setMessage("hi");

		// Assert against JSON file
		assertThat(this.jacksonTesterContactRequest.write(contactRequest)).isEqualToJson("contactRequest.json");
		// JSON path based assertions
		assertThat(this.jacksonTesterContactRequest.write(contactRequest)).hasJsonPathStringValue("@.email");
		assertThat(this.jacksonTesterContactRequest.write(contactRequest)).extractingJsonPathStringValue("@.email")
				.isEqualTo(contactRequest.getEmail());
	}

	@Test
	public void deserializeContactRequest() throws Exception {
		String content = "{\"firstName\":\"test\",\"lastName\":\"mctestester\",\"email\":\"test@test.com\",\"message\":\"hi\"}";
		ContactRequest result = this.jacksonTesterContactRequest.parseObject(content);
		assertThat(result.getFirstName()).isEqualTo("test");
		assertThat(result.getLastName()).isEqualTo("mctestester");
		assertThat(result.getEmail()).isEqualTo("test@test.com");
		assertThat(result.getMessage()).isEqualTo("hi");
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
		ContactResponse result = this.jacksonTesterContactResponse.parseObject(content);
		assertThat(result.getRequestStatus()).isEqualTo(RequestStatus.EMAIL_FAILED);
		assertThat(result.isSuccess()).isEqualTo(Boolean.FALSE);
	}

}
