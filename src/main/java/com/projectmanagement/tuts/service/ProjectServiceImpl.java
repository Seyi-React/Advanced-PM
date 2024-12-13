package com.projectmanagement.tuts.service;

import com.projectmanagement.tuts.DTO.ProjectRequest;
import com.projectmanagement.tuts.Entity.Project;
import com.projectmanagement.tuts.Entity.User;
import com.projectmanagement.tuts.exception.UserEmailNotFoundException;
import com.projectmanagement.tuts.repository.ProjectRepository;
import com.projectmanagement.tuts.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Override
    public Project createProject(ProjectRequest projectRequest, String userEmail) throws Exception {
        User projectLeader = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserEmailNotFoundException("User not found"));

        Project project = Project.builder()
                .description(projectRequest.getDescription())
                .name(projectRequest.getName())
                .endDate(projectRequest.getEndDate())
                .status(projectRequest.getStatus())
                .startDate(projectRequest.getStartDate())
                .projectLeader(projectLeader)
                .build();

        // Ensure bidirectional relationship is maintained
        projectLeader.getLeadingProjects().add(project);

        return projectRepository.save(project);
    }

    @Override
    public List<Project> getUserProjects(String userEmail) throws Exception {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserEmailNotFoundException("User not found"));

        // Fetch projects for the user
        return projectRepository.findByProjectLeader(user);
    }
}