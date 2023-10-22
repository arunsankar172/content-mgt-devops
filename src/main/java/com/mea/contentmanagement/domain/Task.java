package com.mea.contentmanagement.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TASK",schema = "CONTENTMANAGEMENT")
public class Task {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TASKID")
	private long taskId;
	
	@Column(name="ENTITY_MODIFIED")
	private String entityModified;
	
	@Column(name="OPERATION_PERFORMED")
	private String operationPerformed;
	
	@Column(name="TASK_DESCRIPTION")
	private String taskDescription;
	
	@Column(name="NEW_OBJECT")
	private String newObject;
	
	@Column(name="OLD_OBJECT")
	private String oldObject;
	
	@Column(name="DECISION_TYPE")
	private String decisionType;
	
	@Column(name="DECISIONDATE_TIME")
	private Timestamp decisiondateTime;
	
	@Column(name="DECISION_COMMENTS")
	private String decisionComments;
	
	@Column(name="STATUS")
	private int status;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="MAKER_COMMENTS")
	private String makerComments;
	
	@Column(name="CHECKER_COMMENTS")
	private String checkerComments;
	
	@Column(name="CREATED_DATETIME")
	private Timestamp createdDatetime;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	@Column(name="UPDATED_DATETIME")
	private Timestamp updateddateTime;

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

	public Timestamp getDecisiondateTime() {
		return decisiondateTime;
	}

	public void setDecisiondateTime(Timestamp decisiondateTime) {
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

	public String getMakerComments() {
		return makerComments;
	}

	public void setMakerComments(String makerComments) {
		this.makerComments = makerComments;
	}

	public String getCheckerComments() {
		return checkerComments;
	}

	public void setCheckerComments(String checkerComments) {
		this.checkerComments = checkerComments;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdateddateTime() {
		return updateddateTime;
	}

	public void setUpdateddateTime(Timestamp updateddateTime) {
		this.updateddateTime = updateddateTime;
	}




}
