package com.mea.contentmanagement.request.mapper;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mea.contentmanagement.constants.ContentMgtConstant;
import com.mea.contentmanagement.constants.ContentMgtThreadContext;
import com.mea.contentmanagement.mapper.ContentManagementMapper;
import com.mea.contentmanagement.model.Control;
import com.mea.contentmanagement.model.Task;

@Service
public class ContentManagementRequestMapper {
	
	public com.mea.contentmanagement.domain.Task mapCreateTaskRequest(List<Control> controlList, String comments) {
		try {
			Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
			Task task = new Task();
			ObjectMapper objectMapper = new ObjectMapper();
			String listString = objectMapper.writeValueAsString(controlList);
			task.setNewObject(listString);
			task.setEntityModified(controlList.get(0).getLocaleKey());
			task.setOldObject(listString);
			task.setOperationPerformed("New Task Created");
//			task.setDecisionComments(comments);
//			task.setTaskDescription("New Task Created");
			task.setTaskDescription(comments);
			task.setStatus(ContentMgtConstant.TASK_STATUS_SUBMITED);
			task.setCreatedBy(ContentMgtThreadContext.getUserName());
//			task.setCreatedDatetime(currentTimestamp);
			task.setUpdatedBy(ContentMgtThreadContext.getUserName());
//			task.setUpdateddateTime(currentTimestamp);
			com.mea.contentmanagement.domain.Task taskDomain = ContentManagementMapper.INSTANCE
					.mapTaskTotaskDomain(task);
			taskDomain.setMakerComments(comments);
			taskDomain.setCreatedDatetime(currentTimestamp);
			taskDomain.setUpdateddateTime(currentTimestamp);
			return taskDomain;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public static com.mea.contentmanagement.domain.Control mapControlRequestToDomain(Control control) {
		Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		com.mea.contentmanagement.domain.Control controlRequest = ContentManagementMapper.INSTANCE
				.mapUpdateControlToControlDomain(control);
//		control.setLocaleValueEn(null);
//		control.setLocaleValueAr(null);
		control.setNewValueAr(StringEscapeUtils.escapeHtml(control.getNewValueAr()));
		control.setNewValueEn(StringEscapeUtils.escapeHtml(control.getNewValueEn()));
		controlRequest.setCreatedDateTime(currentTimestamp);
		controlRequest.setUpdatedDateTime(currentTimestamp);
		controlRequest.setCreatedBy(ContentMgtThreadContext.getUserName());
		controlRequest.setUpdatedBy(ContentMgtThreadContext.getUserName());
		controlRequest.setStatus(ContentMgtConstant.CONTROL_EDITED);
		return controlRequest;
	}

	public com.mea.contentmanagement.domain.Control mapUpdateControlStatusRequest(Control control, int controlStatus) {
		Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		com.mea.contentmanagement.domain.Control controlRequest = ContentManagementMapper.INSTANCE
				.mapUpdateControlToControlDomain(control);
		controlRequest.setStatus(controlStatus);
		controlRequest.setCreatedDateTime(currentTimestamp);
		controlRequest.setUpdatedDateTime(currentTimestamp);
		controlRequest.setCreatedBy(ContentMgtThreadContext.getUserName());
		controlRequest.setUpdatedBy(ContentMgtThreadContext.getUserName());
		return controlRequest;
	}
	
	public com.mea.contentmanagement.domain.Control mapTaskActionControlRequest(
			com.mea.contentmanagement.domain.Control controlRequest, boolean approved) {
		Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		if (approved) {
			controlRequest.setStatus(ContentMgtConstant.CONTROL_ACTIVE);
			controlRequest.setLocaleValueAr(controlRequest.getNewValueAr());
			controlRequest.setLocaleValueEn(controlRequest.getNewValueEn());
			controlRequest.setNewValueAr("");
			controlRequest.setNewValueEn("");
		} else {
			controlRequest.setStatus(ContentMgtConstant.CONTROL_ACTIVE);
			controlRequest.setLocaleValueAr(controlRequest.getLocaleValueAr());
			controlRequest.setLocaleValueEn(controlRequest.getLocaleValueEn());
			controlRequest.setNewValueAr("");
			controlRequest.setNewValueEn("");
		}

		controlRequest.setCreatedDateTime(currentTimestamp);
		controlRequest.setUpdatedDateTime(currentTimestamp);
		controlRequest.setCreatedBy(ContentMgtThreadContext.getUserName());
		controlRequest.setUpdatedBy(ContentMgtThreadContext.getUserName());
		return controlRequest;
	}
	
	public com.mea.contentmanagement.domain.Task mapUpdateTaskRequest(com.mea.contentmanagement.domain.Task taskRequest){
		Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		taskRequest.setCreatedBy(ContentMgtThreadContext.getUserName());
		taskRequest.setCreatedDatetime(currentTimestamp);
		taskRequest.setUpdatedBy(ContentMgtThreadContext.getUserName());
		taskRequest.setUpdateddateTime(currentTimestamp);
		return taskRequest;
	}
	
	public com.mea.contentmanagement.domain.Control createControl(com.mea.contentmanagement.domain.Control controlRequest){
		Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		
		controlRequest.setCreatedDateTime(currentTimestamp);
		controlRequest.setUpdatedDateTime(currentTimestamp);
		controlRequest.setCreatedBy(ContentMgtThreadContext.getUserName());
		controlRequest.setUpdatedBy(ContentMgtThreadContext.getUserName());
		return controlRequest;
	}
	
	

}
