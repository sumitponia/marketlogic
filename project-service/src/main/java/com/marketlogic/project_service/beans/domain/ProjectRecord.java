package com.marketlogic.project_service.beans.domain;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "project_record")
public class ProjectRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long recordId;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String snapshot;

	@JsonIgnore
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projectId")
	private Project project;
	
	@OneToOne(mappedBy = "failedProjectRecord",cascade = CascadeType.ALL)
	private FailedPushRequest failedPushRequest;

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public String getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(String snapshot) {
		this.snapshot = snapshot;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
