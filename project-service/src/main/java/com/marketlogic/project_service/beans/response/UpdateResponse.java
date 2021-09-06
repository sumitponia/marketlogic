package com.marketlogic.project_service.beans.response;

public class UpdateResponse {

	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public UpdateResponse withError(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}
}
