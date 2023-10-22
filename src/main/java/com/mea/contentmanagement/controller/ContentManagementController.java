package com.mea.contentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mea.contentmanagement.domain.Page;
import com.mea.contentmanagement.request.TaskActionRequest;
import com.mea.contentmanagement.request.UpdateControlRequest;
import com.mea.contentmanagement.request.UpdateControlsRequest;
import com.mea.contentmanagement.response.ContentResponse;
import com.mea.contentmanagement.response.ControlsListResponse;
import com.mea.contentmanagement.response.PageDetailsResponse;
import com.mea.contentmanagement.response.TaskActionResponse;
import com.mea.contentmanagement.response.TaskDetailedResponse;
import com.mea.contentmanagement.response.TaskResponse;
import com.mea.contentmanagement.response.UpdateControlResponse;
import com.mea.contentmanagement.response.UpdateControlsResponse;
import com.mea.contentmanagement.service.ContentManagementService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/content-management")
public class ContentManagementController {
	
	@Autowired
	ContentManagementService contentService;
	
	@Operation(summary = "Get a list of pages for a project")
	@GetMapping("/{projectId}/pages")
	public PageDetailsResponse getPageById(@PathVariable("projectId") int projectId, @RequestParam(required = false,name="userId") String userId,
			@RequestParam(required = false,name = "role") String role, @RequestParam(required = false,  name = "status", defaultValue = "0") int status) {
		return contentService.getPageByProjectId(projectId,userId,role,status);
	}
	
//	Status of control should be changed to EDITED -1
	@Operation(summary = "Update a control by ID for a page under a project")
	@PutMapping("/projects/{projectId}/pages/{pageId}/controls/{controlId}")
	public UpdateControlResponse updateControl(@PathVariable("projectId") long projectId, @PathVariable("pageId") long pageId,
			@PathVariable("controlId") long controlId, @RequestBody UpdateControlRequest updateControl) {
		return contentService.updateControl(projectId, pageId, controlId, updateControl);
	} 

	
//	Status of control should be changed to SUBMITED - 2, iterate through array of control request
//	Put an entry in TASK table against old control object to be inserted 
	@Operation(summary = "Updte bulk control record")
	@PutMapping("/projects/{projectId}/pages/controls/")
	public UpdateControlsResponse updateControls(@PathVariable("projectId") long projectId,
			@RequestBody UpdateControlsRequest updateControls) {
		return contentService.updateControls(projectId, updateControls);
	}
	
	
	
//	Checker API
	
	@Operation(summary = "Checkers list all task")
	@GetMapping("/checkers/tasks")
	public TaskResponse getTasks() {
		return contentService.getTasks();
	} 
	
	@Operation(summary = "Checkers get task detail")
	@GetMapping("/checkers/tasks/{taskId}")
	public TaskDetailedResponse getTasks(@PathVariable int taskId) {
		return contentService.getTasksById(taskId);
	} 
	
	@Operation(summary = "Checkers get control list by taskId")
	@GetMapping("/checkers/tasks/{taskId}/control")
	public ControlsListResponse getControlByTaskId(@PathVariable long taskId) {
		return contentService.getControlByTaskId(taskId);
	} 

	
	// Using Control ID in Approved Array, Update control table table with status Active and clear new value column,
	// In rejected iterate and clear new value and change state edited
	//	Change tasks status to completed
	@Operation(summary = "Checkers Approve/Deline API")
	@PostMapping("/checkers/tasks/{taskId}")
	public TaskActionResponse taskAction(@PathVariable long taskId, @RequestBody TaskActionRequest taskActionRequest) {
		return contentService.taskAction(taskId, taskActionRequest);
	}
	
	@GetMapping("/content")
	public ContentResponse getVersion(@RequestParam(required = false,  name = "projectId") long projectId, 
			@RequestParam(required = false,  name = "contentVersion") String contentVersion,
			@RequestParam(required = false,  name = "publishedDate") String publishedDate) {
		
		return contentService.getContent(projectId,contentVersion,publishedDate);
		
	}
	


}
