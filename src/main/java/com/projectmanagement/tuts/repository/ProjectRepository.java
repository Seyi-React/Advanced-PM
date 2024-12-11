package com.projectmanagement.tuts.repository;

import com.projectmanagement.tuts.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {
    List<Project> findByProjectLeader_Id(Long userId);
}
