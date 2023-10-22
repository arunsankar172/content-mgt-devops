package com.mea.contentmanagement.response;

import java.util.List;

import javax.persistence.Column;

import com.mea.contentmanagement.model.Page;

public class PagesResponse {

	private List<Page> pages;

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	
}
