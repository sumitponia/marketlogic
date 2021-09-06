package com.marketlogic.project_service.beans.response;

import java.util.ArrayList;
import java.util.List;

import com.marketlogic.project_service.beans.Status;
import com.marketlogic.project_service.beans.Type;

public class ProjectResponse {

	private Long projectId;
	private String errorMessage;
	private String name;
	private String description;
	private Type type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<SectionResponse> getSections() {
		return sections;
	}

	public void setSections(List<SectionResponse> sections) {
		this.sections = sections;
	}

	public List<String> getProjectRecords() {
		return projectRecords;
	}

	public void setProjectRecords(List<String> projectRecords) {
		this.projectRecords = projectRecords;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	private Status status;
	private List<SectionResponse> sections = new ArrayList<>();
	private List<String> projectRecords = new ArrayList<>();

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public ProjectResponse withError(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}
}
