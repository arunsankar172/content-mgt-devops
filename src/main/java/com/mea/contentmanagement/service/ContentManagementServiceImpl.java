package com.mea.contentmanagement.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mea.contentmanagement.constants.ContentMgtConstant;
import com.mea.contentmanagement.constants.ContentMgtErrorConstants;
import com.mea.contentmanagement.dao.ContentMgtDao;
import com.mea.contentmanagement.domain.ContentVersionTracking;
import com.mea.contentmanagement.domain.Page;
import com.mea.contentmanagement.domain.Task;
import com.mea.contentmanagement.exception.ContentMgtException;
import com.mea.contentmanagement.exception.ContentMgtResourceNotFoundException;
import com.mea.contentmanagement.mapper.ContentManagementMapper;
import com.mea.contentmanagement.model.Control;
import com.mea.contentmanagement.repository.ContentVersionRepository;
import com.mea.contentmanagement.repository.ControlRepository;
import com.mea.contentmanagement.repository.PageRepository;
import com.mea.contentmanagement.repository.ProjectRepository;
import com.mea.contentmanagement.repository.TaskRepository;
import com.mea.contentmanagement.request.TaskActionRequest;
import com.mea.contentmanagement.request.UpdateControlRequest;
import com.mea.contentmanagement.request.UpdateControlsRequest;
import com.mea.contentmanagement.request.mapper.ContentManagementRequestMapper;
import com.mea.contentmanagement.response.ContentResponse;
import com.mea.contentmanagement.response.ControlsListResponse;
import com.mea.contentmanagement.response.PageDetailsResponse;
import com.mea.contentmanagement.response.PagesResponse;
import com.mea.contentmanagement.response.TaskActionResponse;
import com.mea.contentmanagement.response.TaskDetailedResponse;
import com.mea.contentmanagement.response.TaskResponse;
import com.mea.contentmanagement.response.UpdateControlResponse;
import com.mea.contentmanagement.response.UpdateControlsResponse;
import com.mea.contentmanagement.response.mapper.ContentMgtResponseMapper;

@Service
public class ContentManagementServiceImpl implements ContentManagementService {

	@Autowired
	PageRepository pageRepo;
	
	@Autowired
	TaskRepository taskRepo;
	
	@Autowired
	ControlRepository controlRepo;
	
	@Autowired
	ContentMgtDao contentDao;
	
	@Autowired
	ContentVersionRepository contentVersionRepo;
	
	@Autowired
	ProjectRepository projectRepo;
	
	@Autowired
	ContentManagementRequestMapper requestMapper;
	
	@Autowired
	ContentMgtResponseMapper responseMapper;
	
	private Logger log = LogManager.getLogger(ContentManagementServiceImpl.class);
	
	@Override
	public PagesResponse getPageById(int projectId) {
		PagesResponse pageResponse = new PagesResponse();
		try {
			Page pageDomain = pageRepo.findByProjectId(projectId);
			if (pageDomain != null) {
				return pageResponse;
			} else {
				throw new ContentMgtException(ContentMgtErrorConstants.CM_DATA_NOT_FOUND);
			}
		} catch (Exception e) {
			throw new ContentMgtException(ContentMgtErrorConstants.CM_ERROR_IN_RETRIEVING);
		}
	}
	
	@Override
	public PagesResponse getPages() {
		try {
			List<Page> pagesDomain = pageRepo.findAll();
			if (pagesDomain != null) {
				PagesResponse pagesResponse = new PagesResponse();
				List<com.mea.contentmanagement.model.Page> pageModal = ContentManagementMapper.INSTANCE
						.mapPageToPageModelList(pagesDomain);
				pagesResponse.setPages(pageModal);
				return pagesResponse;
			} else {
				throw new ContentMgtException(ContentMgtErrorConstants.CM_DATA_NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ContentMgtException(ContentMgtErrorConstants.CM_ERROR_IN_RETRIEVING);
		}
	}

	@Override
	public TaskResponse getTasks() {
		try {
//			List<Task> tasksList = taskRepo.findAll();
			List<Task> tasksList = taskRepo.findAllByOrderByUpdateddateTimeDesc();
			if (!tasksList.isEmpty()) {
				TaskResponse taskResponse = new TaskResponse();
				List<com.mea.contentmanagement.model.Task> taskModal = ContentManagementMapper.INSTANCE
						.mapTaskToTaskModal(tasksList);
				taskResponse.setTask(taskModal);
				return taskResponse;
			} else {
				throw new ContentMgtException(ContentMgtErrorConstants.CM_DATA_NOT_FOUND);
			}
		} catch (Exception e) {
			throw new ContentMgtException(ContentMgtErrorConstants.CM_ERROR_IN_RETRIEVING);
		}
	}

	@Override
	public TaskDetailedResponse getTasksById(long taskId) {
		try {
			Task task = taskRepo.findById(taskId).get();
			if (task != null) {
				com.mea.contentmanagement.model.Task taskModel = ContentManagementMapper.INSTANCE
						.mapTaskToTaskModel(task);
				TaskDetailedResponse taskDetail = new TaskDetailedResponse();
				taskDetail.setTask(taskModel);
				return taskDetail;
			} else {
				throw new ContentMgtException(ContentMgtErrorConstants.CM_DATA_NOT_FOUND);
			}
		} catch (Exception e) {
			throw new ContentMgtException(ContentMgtErrorConstants.CM_ERROR_IN_RETRIEVING);
		}
	}

	@Override
	public UpdateControlsResponse updateControls(long projectId, UpdateControlsRequest updateControl) {
		try {
			List<Control> controlList = new ArrayList<Control>();
			UpdateControlsResponse response = new UpdateControlsResponse();
			for (Control control : updateControl.getControl()) {
				controlRepo.save(requestMapper.mapUpdateControlStatusRequest(control,ContentMgtConstant.CONTROL_SUBMITTED));
				control.setStatus(ContentMgtConstant.CONTROL_SUBMITTED);
				controlList.add(control);
			}
			Task taskModel = taskRepo
					.save(requestMapper.mapCreateTaskRequest(controlList, updateControl.getComments()));
			if (taskModel != null) {
				response.setControl(controlList);
				return response;
			} else {
				throw new ContentMgtException(ContentMgtErrorConstants.CM_DATA_NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ContentMgtException(ContentMgtErrorConstants.CM_ERROR_IN_SAVING);
		}
	}

	@Override
	public PageDetailsResponse getPageByProjectId(int projectId, String userId, String role, int status) {
		PageDetailsResponse pagesResponse = new PageDetailsResponse();
		try {
			Page pageDomain = pageRepo.findByProjectId(projectId);
			if(pageDomain!=null) {
				long pageId = pageDomain.getPageId();
				List<com.mea.contentmanagement.domain.Control> controlDomain = new ArrayList<com.mea.contentmanagement.domain.Control>();
				if(status>0) {
					controlDomain = controlRepo.findAllByPageIdAndStatusOrderByUpdatedDateTimeDesc(pageId,status);
				}else {
					controlDomain = controlRepo.findAllByPageIdOrderByUpdatedDateTimeDesc(pageId);
				}
				pagesResponse.setPage(ContentManagementMapper.INSTANCE.mapPageToPageModel(pageDomain));
				if(controlDomain.size()>0) {
					pagesResponse.setControl(responseMapper.mapControlToControlModel(controlDomain));
					return pagesResponse;
				}else {
					pagesResponse.setControl(null);
					return pagesResponse;
				}
			}else {
				throw new ContentMgtResourceNotFoundException(ContentMgtErrorConstants.CM_DATA_NOT_FOUND);
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new ContentMgtException(ContentMgtErrorConstants.CM_ERROR_IN_RETRIEVING);
		}	
	}

	@Override
	public UpdateControlResponse updateControl(long projectId, long pageId, long controlId,
			UpdateControlRequest updateControl) {
		try {
			com.mea.contentmanagement.domain.Control controlRequest = ContentManagementRequestMapper.mapControlRequestToDomain(updateControl.getControl());
			controlRequest.setControlId(controlId);
			UpdateControlResponse controlResponse = new UpdateControlResponse();
			if (updateControl.isDelete()) {
				controlRequest.setStatus(ContentMgtConstant.CONTROL_ACTIVE);
				controlRequest.setNewValueEn(null);
				controlRequest.setNewValueAr(null);
				com.mea.contentmanagement.domain.Control controlDomain = controlRepo.save(requestMapper.createControl(controlRequest));
				controlResponse.setControl(ContentManagementMapper.INSTANCE.mapControlToControlModel(controlDomain));
				return controlResponse;
			} else {
//				Add status EDITED
				controlRequest.setStatus(ContentMgtConstant.CONTROL_EDITED);
				com.mea.contentmanagement.domain.Control controlDomain = controlRepo.save(requestMapper.createControl(controlRequest));
				controlResponse.setControl(ContentManagementMapper.INSTANCE.mapControlToControlModel(controlDomain));
				return controlResponse;
			}
		}catch (Exception e) {
			throw new ContentMgtException(ContentMgtErrorConstants.CM_ERROR_IN_SAVING);
		}
	}
	
	@Override
	public TaskActionResponse taskAction(long taskId, TaskActionRequest taskActionRequest) {
		try {
			Task taskDomain = taskRepo.findById(taskId).get();
			if (taskDomain != null) {
				ObjectMapper objectMapper = new ObjectMapper();
				List<Control> controlNewList = objectMapper.readValue(taskDomain.getNewObject(),
						new TypeReference<List<Control>>() {
						});
				long pageId = 0;
				for (long controlId : taskActionRequest.getApprovedId()) {
					com.mea.contentmanagement.domain.Control control = controlRepo.findById(controlId).get();
//					Change status to ACTIVE/APPROVED
					pageId = control.getPageId();
					controlRepo.save(requestMapper.mapTaskActionControlRequest(control, true));
					controlNewList.stream().filter(a -> a.getControlId() == controlId).findAny()
							.ifPresent(a -> a.setStatus(ContentMgtConstant.CONTROL_APPROVED));
//					controlNewList.removeIf(controlObj -> (controlObj.getControlId()==controlId));
//					log.info("control list remove-"+ controlNewList.removeIf(controlObj -> (controlObj.getControlId()==controlId)));
				}
				for (long controlId : taskActionRequest.getRejectedId()) {
//					Change status to ACTIVE/REJECTED
					com.mea.contentmanagement.domain.Control control = controlRepo.findById(controlId).get();
					controlRepo.save(requestMapper.mapTaskActionControlRequest(control, false));
//					controlNewList.removeIf(controlObj -> (controlObj.getControlId()==controlId));
					controlNewList.stream().filter(a -> a.getControlId() == controlId).findAny()
							.ifPresent(a -> a.setStatus(ContentMgtConstant.CONTROL_DECLINED));
				}
				taskDomain.setNewObject(objectMapper.writeValueAsString(controlNewList));
				int taskStatus = 0;
				for (Control controlItem : controlNewList) {
					if (controlItem.getStatus() == ContentMgtConstant.CONTROL_APPROVED || controlItem.getStatus() == ContentMgtConstant.CONTROL_DECLINED ) {
						taskStatus++;
					}
				}
				if (controlNewList.size() == taskStatus) {
					taskDomain.setStatus(ContentMgtConstant.TASK_STATUS_COMPLETED);
//					ContentVersionTracking newVersion = contentVersionRepo.findLastVersion(1);
					Page page = pageRepo.findByPageId((int)pageId);
//					long projectId = page.getProjectId();
					ContentVersionTracking content = contentVersionRepo.findByProjectIdOrderByDateTimePublishedDesc((long)page.getProjectId());
					double newVersion = Float.parseFloat(content.getContentVersion()) + 0.1;
					Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
					ContentVersionTracking newContent = new ContentVersionTracking();
					newContent.setContentVersion(String.format("%.2f", newVersion));
					newContent.setProjectId(page.getProjectId());
					newContent.setDateTimePublished(currentTimestamp);
					newContent.setPublishedBy("Test");
					contentVersionRepo.save(newContent);
					
				}
				taskDomain.setCheckerComments(taskActionRequest.getComments());
				taskRepo.save(requestMapper.mapUpdateTaskRequest(taskDomain));
				TaskActionResponse taskActionResponse = new TaskActionResponse();
				taskActionResponse.setApprovedId(taskActionRequest.getApprovedId());
				taskActionResponse.setRejectedId(taskActionRequest.getRejectedId());
				return taskActionResponse;
			} else {
				throw new ContentMgtException(ContentMgtErrorConstants.CM_DATA_NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ContentMgtException(ContentMgtErrorConstants.CM_ERROR_IN_SAVING);
		}
	}

	@Override
	public ControlsListResponse getControlByTaskId(long taskId) {
		try {
			ControlsListResponse controlResponse = new ControlsListResponse();
			Task taskDomain = taskRepo.findById(taskId).get();
			if (taskDomain != null) {
				ObjectMapper objectMapper = new ObjectMapper();
				List<Control> controlNewList = objectMapper.readValue(taskDomain.getNewObject(),
						new TypeReference<List<Control>>() {
						});
				controlResponse.setControl(controlNewList);
				controlResponse.setComments(taskDomain.getCheckerComments());
				return controlResponse;
			} else {
				throw new ContentMgtResourceNotFoundException(ContentMgtErrorConstants.CM_DATA_NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ContentMgtResourceNotFoundException(ContentMgtErrorConstants.CM_ERROR_IN_RETRIEVING);
		}
	}

	@Override
	public ContentResponse getContent(long projectId, String contentVersion, String publishedDate) {
		ContentResponse contentVersionResponse = new ContentResponse();
		boolean isAppFirsttimeLaunch = false;
		try {
			ContentVersionTracking contentVersionDomain = contentVersionRepo.findByContentVersionIdAndDateTimePublishedGreaterThanEqual(projectId,
					Timestamp.valueOf(publishedDate));
			if(!contentVersionDomain.getContentVersion().equals(contentVersion)) {
				if(isAppFirsttimeLaunch) {
					List<Page> pageDomainList = pageRepo.findAllByProjectId((int) projectId);
					contentVersionResponse.setPages(ContentManagementMapper.INSTANCE.mapPageToPageModelList(pageDomainList));
				}else {
					List<com.mea.contentmanagement.model.Page> pageList = new ArrayList<com.mea.contentmanagement.model.Page>();
					Page pageDomain = pageRepo.findByProjectId((int) projectId);
					List<com.mea.contentmanagement.domain.Control> controlDomain = controlRepo.findByPageIdAndUpdatedDateTimeGreaterThanEqual(pageDomain.getPageId(),Timestamp.valueOf(publishedDate));
					pageDomain.setControls(controlDomain);
					pageList.add(ContentManagementMapper.INSTANCE.mapPageToPageModel(pageDomain));
					contentVersionResponse.setPages(pageList);
				}
			}
			
			return contentVersionResponse;

		}catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}
	}
}
