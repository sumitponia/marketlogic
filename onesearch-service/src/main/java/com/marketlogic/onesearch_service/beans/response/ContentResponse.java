package com.marketlogic.onesearch_service.beans.response;

public class ContentResponse {

	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public ContentResponse withError(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}
}
