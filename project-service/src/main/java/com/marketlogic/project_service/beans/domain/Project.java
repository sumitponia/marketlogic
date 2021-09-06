package com.marketlogic.project_service.beans.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.marketlogic.project_service.beans.Status;
import com.marketlogic.project_service.beans.Type;

@Entity(name = "project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long projectId;

	@NotNull
	private String name;
	private String description;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Type type;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Section> sections = new ArrayList<>();

	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ProjectRecord> projectRecords = new ArrayList<>();

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

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

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public List<ProjectRecord> getProjectRecords() {
		return projectRecords;
	}

	public void setProjectRecords(List<ProjectRecord> projectRecords) {
		this.projectRecords = projectRecords;
	}

}
