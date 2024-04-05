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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((requestStatus == null) ? 0 : requestStatus.hashCode());
		result = prime * result + (success ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactResponse other = (ContactResponse) obj;
		if (requestStatus != other.requestStatus)
			return false;
		if (success != other.success)
			return false;
		return true;
	}

}
