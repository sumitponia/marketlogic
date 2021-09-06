package com.marketlogic.project_service.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.marketlogic.project_service.beans.domain.Project;

public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {

}