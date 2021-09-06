package com.marketlogic.project_service.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.marketlogic.project_service.beans.domain.ProjectRecord;

public interface ProjectRecordRepository extends PagingAndSortingRepository<ProjectRecord, Long> {

}