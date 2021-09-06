package com.marketlogic.project_service.beans.response;

public class SectionResponse {

	private String title;
	private Long sectionId;

	private String errorMessage;
	private String description;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public SectionResponse withError(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

}
