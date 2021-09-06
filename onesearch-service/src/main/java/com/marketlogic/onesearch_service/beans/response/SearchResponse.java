package com.marketlogic.onesearch_service.beans.response;

public class SearchResponse {

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

	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public SearchResponse withError(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}

}
