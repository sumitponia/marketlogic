package com.marketlogic.project_service.beans.request;

import java.util.ArrayList;
import java.util.List;

import com.marketlogic.project_service.beans.Type;

public class ProjectRequest {
	private String name;
	private String description;
	private Long projectId;

	private List<SectionRequest> sectionRequests = new ArrayList<SectionRequest>();

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

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public List<SectionRequest> getSectionRequests() {
		return sectionRequests;
	}

	public void setSectionRequests(List<SectionRequest> sectionRequests) {
		this.sectionRequests = sectionRequests;
	}

}
