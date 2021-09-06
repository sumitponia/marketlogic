package com.marketlogic.project_service.service;

import java.io.UnsupportedEncodingException;

import com.marketlogic.project_service.beans.domain.ProjectRecord;

public interface OneSearchService {
	public void publishToOneSearch(ProjectRecord projectRecord) throws UnsupportedEncodingException;
}
