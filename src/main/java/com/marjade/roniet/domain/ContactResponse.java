package com.marjade.roniet.domain;

public class ContactResponse {

	private RequestStatus requestStatus;
	
	private boolean success;

	public ContactResponse() {
	}

	public ContactResponse(boolean success, RequestStatus requestStatus) {
		this.requestStatus = requestStatus;
		this.success = success;
	}

	public RequestStatus getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(RequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
