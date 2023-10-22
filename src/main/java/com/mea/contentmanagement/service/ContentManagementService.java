package com.mea.contentmanagement.service;

import com.mea.contentmanagement.request.TaskActionRequest;
import com.mea.contentmanagement.request.UpdateControlRequest;
import com.mea.contentmanagement.request.UpdateControlsRequest;
import com.mea.contentmanagement.response.ContentResponse;
import com.mea.contentmanagement.response.ControlsListResponse;
import com.mea.contentmanagement.response.PageDetailsResponse;
import com.mea.contentmanagement.response.PagesResponse;
import com.mea.contentmanagement.response.TaskActionResponse;
import com.mea.contentmanagement.response.TaskDetailedResponse;
import com.mea.contentmanagement.response.TaskResponse;
import com.mea.contentmanagement.response.UpdateControlResponse;
import com.mea.contentmanagement.response.UpdateControlsResponse;

public interface ContentManagementService {

	PagesResponse getPageById(int projectId);

	PagesResponse getPages();

	TaskResponse getTasks();

	TaskDetailedResponse getTasksById(long taskId);

	UpdateControlResponse updateControl(long projectId, long pageId, long controlId, UpdateControlRequest updateControl);

	UpdateControlsResponse updateControls(long projectId, UpdateControlsRequest updateControls);

	PageDetailsResponse getPageByProjectId(int projectId, String userId, String role, int status);

	TaskActionResponse taskAction(long taskId, TaskActionRequest taskActionRequest);

	ControlsListResponse getControlByTaskId(long taskId);

	ContentResponse getContent(long projectId, String contentVersion, String publishedDate);

}
