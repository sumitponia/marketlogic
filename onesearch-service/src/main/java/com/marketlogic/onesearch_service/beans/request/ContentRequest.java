package com.marketlogic.onesearch_service.beans.request;

public class ContentRequest {
	private Long recordId;
	private String snapshot;

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

}
