package com.mea.contentmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mea.contentmanagement.domain.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	
	@Query("update Task set status = 2 where task_id = :taskId")
	void updateTaskStatus(@Param("taskId") long taskId);
//	void updateTaskStatus(@Param("status") int status, @Param("taskId") long taskId);
	
	List<Task> findAllByOrderByUpdateddateTimeDesc();

}
