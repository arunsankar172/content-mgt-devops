package com.mea.contentmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mea.contentmanagement.domain.Page;

public interface PageRepository extends JpaRepository<Page, Long>{
	
	Page findByProjectId(int id);
	
	Page findByPageId(int id);
	
	List<Page> findAllByProjectId(int id);

}
