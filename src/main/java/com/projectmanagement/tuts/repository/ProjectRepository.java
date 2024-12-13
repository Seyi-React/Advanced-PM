package com.projectmanagement.tuts.repository;

import com.projectmanagement.tuts.Entity.Project;
import com.projectmanagement.tuts.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {
    List<Project> findByProjectLeader(User user);
}
