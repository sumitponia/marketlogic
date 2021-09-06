package com.marketlogic.onesearch_service.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.marketlogic.onesearch_service.beans.request.ContentRequest;
import com.marketlogic.onesearch_service.beans.response.ContentResponse;
import com.marketlogic.onesearch_service.beans.response.SearchResponse;
import com.marketlogic.onesearch_service.exception.BusinessException;
import com.marketlogic.onesearch_service.service.SearchService;

@RestController
@RequestMapping("/onesearch-service")
public class SearchController {

	Logger LOGGER = LogManager.getLogger(SearchController.class);

	@Autowired
	private SearchService searchService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ContentResponse> createContent(@RequestBody ContentRequest content) {
		LOGGER.debug("Method Start : createContent");
		try {
			searchService.createContent(content);
			return new ResponseEntity<>(new ContentResponse(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(new ContentResponse().withError(e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOGGER.error("Exception in calling createContent service. ", e);
			return new ResponseEntity<>(
					new ContentResponse().withError("Something went wrong in creating project. Please try again."),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@RequestMapping(path = "_search",method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<SearchResponse>> getContent(@RequestParam(name = "query") String query) {
		LOGGER.debug("Method Start : getContent");
		try {
			List<SearchResponse> seacrResponses = searchService.getContent(query);
			return new ResponseEntity<>(seacrResponses, HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(Arrays.asList(new SearchResponse().withError(e.getMessage())),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOGGER.error("Exception in calling getProjects service. ", e);
			return new ResponseEntity<>(
					Arrays.asList(new SearchResponse()
							.withError("Something went wrong in getting search content. Please try again.")),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

}
