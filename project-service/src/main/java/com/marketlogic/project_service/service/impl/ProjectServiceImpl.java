package com.marketlogic.project_service.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketlogic.project_service.beans.Status;
import com.marketlogic.project_service.beans.domain.Project;
import com.marketlogic.project_service.beans.domain.ProjectRecord;
import com.marketlogic.project_service.beans.domain.Section;
import com.marketlogic.project_service.beans.request.ProjectRequest;
import com.marketlogic.project_service.beans.request.SectionRequest;
import com.marketlogic.project_service.beans.response.CreateResponse;
import com.marketlogic.project_service.beans.response.ProjectResponse;
import com.marketlogic.project_service.beans.response.SectionResponse;
import com.marketlogic.project_service.exception.BusinessException;
import com.marketlogic.project_service.repository.ProjectRepository;
import com.marketlogic.project_service.service.OneSearchService;
import com.marketlogic.project_service.service.ProjectService;
import com.marketlogic.project_service.service.Valdiator;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private OneSearchService oneSearchService;

	@Override
	public CreateResponse createProject(ProjectRequest projectRequest) throws BusinessException {
		Valdiator.validate(StringUtils::hasLength, projectRequest.getName(), "Please provide project name.");
		Project project = new Project();
		project.setStatus(Status.DRAFT);
		BeanUtils.copyProperties(projectRequest, project, "projectId");
		project.getSections().addAll(
				projectRequest.getSectionRequests().stream().map(sectionRequestMapper).collect(Collectors.toList()));
		project.getSections().forEach(section -> section.setProject(project));
		projectRepository.save(project);
		return createResponseMapper.apply(project);
	}

	@Override
	public void updateProject(ProjectRequest projectRequest) throws BusinessException {
		Valdiator.validate(x -> x != null, projectRequest.getProjectId(), "Please provide project id.");
		Optional<Project> projectOptional = projectRepository.findById(projectRequest.getProjectId());
		Valdiator.validate(Optional::isPresent, projectOptional, "No project found with provided id.");
		Project project = projectOptional.get();
		BeanUtils.copyProperties(projectRequest, project, "projectId");

		//
		Map<Long, SectionRequest> sectionRequestMap = projectRequest.getSectionRequests().stream()
				.collect(Collectors.toMap(SectionRequest::getSectionId, Function.identity()));
		for (Section section : project.getSections()) {
			SectionRequest secReq = sectionRequestMap.get(section.getSectionId());
			if (secReq != null) {
				BeanUtils.copyProperties(secReq, section);
			}
		}
		List<Section> newSections = projectRequest.getSectionRequests().stream()
				.filter(sectionRequest -> sectionRequest.getSectionId() == null).map(sectionRequest -> {
					Section section = new Section();
					BeanUtils.copyProperties(sectionRequest, section);
					return section;
				}).collect(Collectors.toList());
		project.getSections().addAll(newSections);
		//
		project.getSections().forEach(section -> section.setProject(project));
		projectRepository.save(project);
	}

	@Override
	public void deleteProject(Long projectId) throws BusinessException {
		Valdiator.validate(x -> x != null, projectId, "Please provide project id.");
		Optional<Project> projectOptional = projectRepository.findById(projectId);
		Valdiator.validate(Optional::isPresent, projectOptional, "No project found with provided id.");
		projectRepository.delete(projectOptional.get());
	}

	@Override
	public List<ProjectResponse> getProjects(int pageNo, int pageSize) throws BusinessException {
		Valdiator.validate(paginationValidator, new int[] { pageNo, pageSize },
				"Please provide valid stand and end indexes.");
		Pageable page = PageRequest.of(pageNo, pageSize);
		Page<Project> pageObject = projectRepository.findAll(page);
		List<ProjectResponse> projectResponses = pageObject.get().map(projectResponseMapper)
				.collect(Collectors.toList());
		return projectResponses;
	}

	@Override
	public void publish(Long projectId)
			throws BusinessException, JsonProcessingException, UnsupportedEncodingException {
		Valdiator.validate(x -> x != null, projectId, "Please provide project id.");
		Optional<Project> projectOptional = projectRepository.findById(projectId);
		Valdiator.validate(Optional::isPresent, projectOptional, "No project found with provided id.");
		Project project = projectOptional.get();
		// create a project record
		ProjectRecord projectRecord = new ProjectRecord();
		projectRecord.setSnapshot(this.createProjectSnapShot(project));
		projectRecord.setProject(project);
		project.getProjectRecords().add(projectRecord);
		// save snapshot to the project
		projectRepository.save(project);
		// send latest saved prokject record to search service
		oneSearchService.publishToOneSearch(project.getProjectRecords().get(project.getProjectRecords().size() - 1));
	}

	private String createProjectSnapShot(Project project) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Project projectClone = new Project();
		BeanUtils.copyProperties(project, projectClone, "projectRecords");
		return objectMapper.writeValueAsString(projectClone);
	}

	private final Predicate<int[]> paginationValidator = (arr) -> {
		return (arr[0] >= 0 && arr[1] >= 0);
	};

	private final Function<Section, SectionResponse> sectionResponseMapper = (section) -> {
		SectionResponse sectionResponse = new SectionResponse();
		BeanUtils.copyProperties(section, sectionResponse);
		return sectionResponse;
	};

	private Function<Project, ProjectResponse> projectResponseMapper = (project) -> {
		ProjectResponse projectResponse = new ProjectResponse();
		BeanUtils.copyProperties(project, projectResponse);
		List<SectionResponse> sectionResponses = project.getSections().stream().map(sectionResponseMapper)
				.collect(Collectors.toList());
		List<String> projectRecords = project.getProjectRecords().stream()
				.map(projectRecord -> projectRecord.getSnapshot()).collect(Collectors.toList());
		projectResponse.setSections(sectionResponses);
		projectResponse.setProjectRecords(projectRecords);
		return projectResponse;
	};

	private Function<Project, CreateResponse> createResponseMapper = (project) -> {
		CreateResponse createResponse = new CreateResponse();
		BeanUtils.copyProperties(project, createResponse);
		return createResponse;
	};

	private final Function<SectionRequest, Section> sectionRequestMapper = (sectionRequest) -> {
		Section section = new Section();
		BeanUtils.copyProperties(sectionRequest, section,"sectionId");
		return section;
	};

}
