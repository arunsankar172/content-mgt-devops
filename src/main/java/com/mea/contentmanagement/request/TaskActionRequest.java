package com.mea.contentmanagement.request;

import java.util.List;

public class TaskActionRequest {
	
	private List<Integer> approvedId;
	
	private List<Integer> rejectedId;
	
	private String comments;

	public List<Integer> getApprovedId() {
		return approvedId;
	}

	public void setApprovedId(List<Integer> approvedId) {
		this.approvedId = approvedId;
	}

	public List<Integer> getRejectedId() {
		return rejectedId;
	}

	public void setRejectedId(List<Integer> rejectedId) {
		this.rejectedId = rejectedId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	
}
