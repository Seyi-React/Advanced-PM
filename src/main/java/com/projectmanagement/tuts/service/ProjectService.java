package com.projectmanagement.tuts.service;

import com.projectmanagement.tuts.DTO.ProjectRequest;
import com.projectmanagement.tuts.Entity.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Project createProject(ProjectRequest projectRequest, String userEmail) throws Exception;
    List<Project> getUserProjects(String userEmail) throws Exception;
    Project getProjectById(Long projectId) throws Exception;
}
