package com.marketlogic.project_service.repository;

import org.springframework.data.repository.CrudRepository;

import com.marketlogic.project_service.beans.domain.FailedPushRequest;

public interface FailedPushedRequestRepository extends CrudRepository<FailedPushRequest, Long> {

}