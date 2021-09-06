package com.marketlogic.onesearch_service.service.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.marketlogic.onesearch_service.beans.domain.Content;
import com.marketlogic.onesearch_service.beans.request.ContentRequest;
import com.marketlogic.onesearch_service.beans.response.SearchResponse;
import com.marketlogic.onesearch_service.exception.BusinessException;
import com.marketlogic.onesearch_service.repository.SearchRepository;
import com.marketlogic.onesearch_service.service.SearchService;
import com.marketlogic.onesearch_service.service.Valdiator;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchRepository searchRepository;

	@Override
	public void createContent(ContentRequest contentRequest) throws BusinessException {
		Valdiator.validate(x -> x != null, contentRequest.getRecordId(), "Please provide project record id.");
		Valdiator.validate(StringUtils::hasLength, contentRequest.getSnapshot(), "Please provide content.");
		Content content = new Content();
		BeanUtils.copyProperties(contentRequest, content);
		searchRepository.save(content);
	}

	@Override
	public List<SearchResponse> getContent(String query) throws BusinessException {
		Valdiator.validate(StringUtils::hasLength, query, "Please provide search content.");
		List<Content> content = searchRepository.findByContent(query);
		return content.stream().map(searchResponseMapper).collect(Collectors.toList());
	}

	private Function<Content, SearchResponse> searchResponseMapper = (content) -> {
		SearchResponse searchResponse = new SearchResponse();
		BeanUtils.copyProperties(content, searchResponse);
		return searchResponse;
	};
}
