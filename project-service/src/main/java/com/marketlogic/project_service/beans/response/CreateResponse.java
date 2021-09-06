package com.marketlogic.project_service.beans.response;

public class CreateResponse {

	private Long projectId;
	private String errorMessage;
	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public CreateResponse withError(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}
}
