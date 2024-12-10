package com.projectmanagement.tuts.repository;

import com.projectmanagement.tuts.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {
}
