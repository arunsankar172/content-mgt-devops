package com.mea.contentmanagement.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mea.contentmanagement.domain.Control;

public interface ControlRepository extends JpaRepository<Control, Long> {

	List<Control> findAllByPageIdAndStatusOrderByUpdatedDateTimeDesc(long pageId, int status);
	
	List<Control> findAllByPageIdOrderByUpdatedDateTimeDesc(long pageId);
	
	Control findAllByControlId(long controlId);
	
	List<Control>  findByPageIdAndUpdatedDateTimeGreaterThanEqual(long pageId,Timestamp updatedDateTime);
	
}
