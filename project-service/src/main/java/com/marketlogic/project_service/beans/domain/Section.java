package com.marketlogic.project_service.beans.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "section")
public class Section {
	public Section() {

	}

	public Section(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long sectionId;
	private String title;
	private String description;

	@JsonIgnore
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projectId")
	private Project project;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
