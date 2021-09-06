package com.marketlogic.onesearch_service.service;

import java.util.List;

import com.marketlogic.onesearch_service.beans.request.ContentRequest;
import com.marketlogic.onesearch_service.beans.response.SearchResponse;
import com.marketlogic.onesearch_service.exception.BusinessException;

public interface SearchService {

	void createContent(ContentRequest content) throws BusinessException;

	List<SearchResponse> getContent(String query) throws BusinessException;

}