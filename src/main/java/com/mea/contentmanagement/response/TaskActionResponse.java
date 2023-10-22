package com.mea.contentmanagement.response;

import java.util.List;

import com.mea.bpm.response.BaseResponse;

public class TaskActionResponse extends BaseResponse{
	
	private List<Integer> approvedId;
	
	private List<Integer> rejectedId;

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

	
}
