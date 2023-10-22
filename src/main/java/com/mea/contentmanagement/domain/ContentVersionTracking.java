package com.mea.contentmanagement.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CONTENTVERSIONTRACKING",schema = "CONTENTMANAGEMENT")
public class ContentVersionTracking {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CONTENTVERSIONID")
	private long contentVersionId;
	
	@Column(name="PROJECTID")
	private long projectId;
	
	@Column(name="PUBLISHEDBY")
	private String publishedBy;
	
	@Column(name="CONTENTVERSION")
	private String contentVersion;
	
	@Column(name="DATETIMEPUBLISHED")
	private Timestamp dateTimePublished;

	public long getContentVersionId() {
		return contentVersionId;
	}

	public void setContentVersionId(long contentVersionId) {
		this.contentVersionId = contentVersionId;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getPublishedBy() {
		return publishedBy;
	}

	public void setPublishedBy(String publishedBy) {
		this.publishedBy = publishedBy;
	}

	public String getContentVersion() {
		return contentVersion;
	}

	public void setContentVersion(String contentVersion) {
		this.contentVersion = contentVersion;
	}

	public Timestamp getDateTimePublished() {
		return dateTimePublished;
	}

	public void setDateTimePublished(Timestamp dateTimePublished) {
		this.dateTimePublished = dateTimePublished;
	}

}
