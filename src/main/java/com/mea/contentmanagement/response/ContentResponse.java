package com.mea.contentmanagement.response;

import java.util.List;

import com.mea.contentmanagement.model.Page;

public class ContentResponse {
	
	private String contentVersion;
	
	private String publishedDate;
	
	private List<Page> pages;

	public String getContentVersion() {
		return contentVersion;
	}

	public void setContentVersion(String contentVersion) {
		this.contentVersion = contentVersion;
	}

	public String getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> list) {
		this.pages = list;
	}

}
