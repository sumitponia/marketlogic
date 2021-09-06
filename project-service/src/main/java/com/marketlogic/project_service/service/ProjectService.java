package com.marketlogic.project_service.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marketlogic.project_service.beans.request.ProjectRequest;
import com.marketlogic.project_service.beans.response.CreateResponse;
import com.marketlogic.project_service.beans.response.ProjectResponse;
import com.marketlogic.project_service.exception.BusinessException;

public interface ProjectService {

	CreateResponse createProject(ProjectRequest project) throws BusinessException;

	void updateProject(ProjectRequest project) throws BusinessException;

	void deleteProject(Long projectId) throws BusinessException;

	List<ProjectResponse> getProjects(int start, int end) throws BusinessException;

	void publish(Long projectId) throws BusinessException, JsonProcessingException, UnsupportedEncodingException;

}
