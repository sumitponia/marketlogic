package com.marketlogic.project_service.beans.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "failed_push_requests")
public class FailedPushRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne
	@JoinColumn(name = "recordId")
	private ProjectRecord failedProjectRecord;

	public ProjectRecord getFailedProjectRecord() {
		return failedProjectRecord;
	}

	public void setFailedProjectRecord(ProjectRecord failedProjectRecord) {
		this.failedProjectRecord = failedProjectRecord;
	}
}
