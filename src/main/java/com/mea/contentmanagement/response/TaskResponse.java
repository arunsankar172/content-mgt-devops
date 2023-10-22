package com.mea.contentmanagement.response;

import java.util.List;

import com.mea.contentmanagement.model.Task;

public class TaskResponse {
	
	private List<Task> task;

	public List<Task> getTask() {
		return task;
	}

	public void setTask(List<Task> task) {
		this.task = task;
	}
}
