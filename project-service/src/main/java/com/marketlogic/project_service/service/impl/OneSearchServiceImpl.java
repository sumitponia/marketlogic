package com.marketlogic.project_service.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.marketlogic.project_service.beans.domain.FailedPushRequest;
import com.marketlogic.project_service.beans.domain.ProjectRecord;
import com.marketlogic.project_service.repository.FailedPushedRequestRepository;
import com.marketlogic.project_service.service.OneSearchService;

@Service
public class OneSearchServiceImpl implements OneSearchService {
	Logger LOGGER = LogManager.getLogger(OneSearchServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private FailedPushedRequestRepository failedPushedRequestRepository;

	@Override
	@Async
	@Retryable(maxAttemptsExpression = "${app.max-attempts}", value = ResourceAccessException.class, backoff = @Backoff(delayExpression = "${app.backoff-delay}", multiplierExpression = "${app.backoff-delay-multiplier}"))
	public void publishToOneSearch(ProjectRecord projectRecord) {
		LOGGER.debug("Publishing record to one search");
		LOGGER.error(projectRecord.getRecordId());
		restTemplate.postForObject("http://onesearch/onesearch-service", projectRecord, Object.class);
	}

	@Recover
	public void recover(ResourceAccessException exception, ProjectRecord projectRecord) {
		LOGGER.debug("Publishing failed record to the table after exception");
		LOGGER.error(exception);
		FailedPushRequest failedPushRequest = new FailedPushRequest();
		failedPushRequest.setFailedProjectRecord(projectRecord);
		failedPushedRequestRepository.save(failedPushRequest);
	}

}