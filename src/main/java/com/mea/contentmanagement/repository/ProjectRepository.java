package com.mea.contentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mea.contentmanagement.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
