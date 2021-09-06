package com.marketlogic.project_service.beans;

public enum Status {

	DRAFT("draft"), PUBLISHED("published");

	Status(String status) {
		this.status = status;
	}

	public String status;
}
