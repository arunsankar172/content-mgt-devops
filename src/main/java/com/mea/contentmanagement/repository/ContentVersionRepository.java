package com.mea.contentmanagement.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mea.contentmanagement.domain.ContentVersionTracking;

public interface ContentVersionRepository extends JpaRepository<ContentVersionTracking, Long>{
	
//	@Query("from ContentVersionTracking cv where dateTimePublished>=:dateTimePublished")
	ContentVersionTracking findByContentVersionIdAndDateTimePublishedGreaterThanEqual(long contentVersionId, Timestamp dateTimePublished);
	
//	@Query("SELECT cv FROM ContentVersionTracking cv ORDER BY cv.dateTimePublished DESC LIMIT 1")
//	ContentVersionTracking findLastVersion(long projectId);
	
	ContentVersionTracking findByProjectIdOrderByDateTimePublishedDesc(long projectId);
	
}
