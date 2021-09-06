package com.marketlogic.project_service.beans.response;

public class DeleteResponse {

	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public DeleteResponse withError(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}
}