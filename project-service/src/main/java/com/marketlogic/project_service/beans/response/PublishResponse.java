package com.marketlogic.project_service.beans.response;

public class PublishResponse {

	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public PublishResponse withError(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}
}
