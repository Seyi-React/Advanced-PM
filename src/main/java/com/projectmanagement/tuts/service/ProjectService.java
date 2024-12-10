package com.projectmanagement.tuts.service;

import com.projectmanagement.tuts.DTO.ProjectRequest;
import com.projectmanagement.tuts.Entity.Project;

public interface ProjectService {
    Project createProject(ProjectRequest projectRequest, String userEmail) throws Exception;
}
