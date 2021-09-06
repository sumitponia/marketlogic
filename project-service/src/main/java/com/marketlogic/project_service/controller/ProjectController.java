package com.marketlogic.project_service.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.marketlogic.project_service.beans.request.ProjectRequest;
import com.marketlogic.project_service.beans.response.CreateResponse;
import com.marketlogic.project_service.beans.response.DeleteResponse;
import com.marketlogic.project_service.beans.response.ProjectResponse;
import com.marketlogic.project_service.beans.response.PublishResponse;
import com.marketlogic.project_service.beans.response.UpdateResponse;
import com.marketlogic.project_service.exception.BusinessException;
import com.marketlogic.project_service.service.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {

	Logger LOGGER = LogManager.getLogger(ProjectController.class);

	@Autowired
	private ProjectService projectService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CreateResponse> createProject(@RequestBody ProjectRequest project) {
		LOGGER.debug("Method Start : createProject");
		try {
			CreateResponse createResponse = projectService.createProject(project);
			return new ResponseEntity<>(createResponse, HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(new CreateResponse().withError(e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOGGER.error("Exception in calling createProject service. ", e);
			return new ResponseEntity<>(
					new CreateResponse().withError("Something went wrong in creating project. Please try again."),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<UpdateResponse> updateProject(@RequestBody ProjectRequest project) {
		LOGGER.debug("Method Start : updateProject");
		try {
			projectService.updateProject(project);
			return new ResponseEntity<>(new UpdateResponse(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(new UpdateResponse().withError(e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOGGER.error("Exception in calling updateProject service. ", e);
			return new ResponseEntity<>(
					new UpdateResponse().withError("Something went wrong in updating project. Please try again."),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<DeleteResponse> deleteProject(Long projectId) {
		LOGGER.debug("Method Start : deleteProject");
		try {
			projectService.deleteProject(projectId);
			return new ResponseEntity<>(new DeleteResponse(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(new DeleteResponse().withError(e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOGGER.error("Exception in calling deleteProject service. ", e);
			return new ResponseEntity<>(
					new DeleteResponse().withError("Something went wrong in deleting project. Please try again."),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ProjectResponse>> getProjects(@RequestParam(name = "start") int start,
			@RequestParam(name = "end") int end) {
		LOGGER.debug("Method Start : getProjects");
		try {
			List<ProjectResponse> projectResponses = projectService.getProjects(start, end);
			return new ResponseEntity<>(projectResponses, HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(Arrays.asList(new ProjectResponse().withError(e.getMessage())),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOGGER.error("Exception in calling getProjects service. ", e);
			return new ResponseEntity<>(
					Arrays.asList(new ProjectResponse()
							.withError("Something went wrong in getting list of projects. Please try again.")),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@RequestMapping(method = RequestMethod.POST, path = "{id}/_publish")
	@ResponseBody
	public ResponseEntity<PublishResponse> publish(@PathVariable(name = "id") Long projectId) {
		LOGGER.debug("Method Start : publish");
		try {
			projectService.publish(projectId);
			return new ResponseEntity<>(new PublishResponse(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(new PublishResponse().withError(e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOGGER.error("Exception in calling getProjects service. ", e);
			return new ResponseEntity<>(
					new PublishResponse()
							.withError("Something went wrong in publishing the project. Please try again."),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

}
