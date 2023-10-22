package com.mea.contentmanagement.model;

import java.sql.Timestamp;

public class Task {

	private long taskId;
	
	private String entityModified;
	
	private String operationPerformed;
	
	private String taskDescription;
	
	private String newObject;

	private String oldObject;

	private String decisionType;
	
	private String decisiondateTime;
	
	private String decisionComments;
	
	private int status;
	
	private String createdBy;
	
	private String createdDatetime;
	
	private String updatedBy;
	
	private String updateddateTime;

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public String getEntityModified() {
		return entityModified;
	}

	public void setEntityModified(String entityModified) {
		this.entityModified = entityModified;
	}

	public String getOperationPerformed() {
		return operationPerformed;
	}

	public void setOperationPerformed(String operationPerformed) {
		this.operationPerformed = operationPerformed;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public String getNewObject() {
		return newObject;
	}

	public void setNewObject(String newObject) {
		this.newObject = newObject;
	}

	public String getOldObject() {
		return oldObject;
	}

	public void setOldObject(String oldObject) {
		this.oldObject = oldObject;
	}

	public String getDecisionType() {
		return decisionType;
	}

	public void setDecisionType(String decisionType) {
		this.decisionType = decisionType;
	}

	public String getDecisiondateTime() {
		return decisiondateTime;
	}

	public void setDecisiondateTime(String decisiondateTime) {
		this.decisiondateTime = decisiondateTime;
	}

	public String getDecisionComments() {
		return decisionComments;
	}

	public void setDecisionComments(String decisionComments) {
		this.decisionComments = decisionComments;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(String createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdateddateTime() {
		return updateddateTime;
	}

	public void setUpdateddateTime(String updateddateTime) {
		this.updateddateTime = updateddateTime;
	}

	
	
}
