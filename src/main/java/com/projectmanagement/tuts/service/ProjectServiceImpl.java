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
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements  ProjectService {

    private final UserRepository userRepository;

    private final ProjectRepository projectRepository;


    @Override
    public Project createProject(ProjectRequest projectRequest, String userEmail) throws Exception {
        Optional<User> projectLeader = userRepository.findByEmail(userEmail);
        try {
            if (projectLeader.isEmpty()) {
                throw new UserEmailNotFoundException("User not found");
            }

            Project project = Project.builder()
                    .description(projectRequest.getDescription())
                    .name(projectRequest.getName())
                    .endDate(projectRequest.getEndDate())
                    .status(projectRequest.getStatus())
                    .startDate(projectRequest.getStartDate())
                    .projectLeader(projectLeader.get())
                    .build();

            return projectRepository.save(project);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Project> getUserProjects(String userEmail) throws Exception {
        Optional<User> getUser = userRepository.findByEmail(userEmail);
        try {
            if (getUser.isEmpty()) {
                throw new UserEmailNotFoundException("User not found");
            }

//            projectRepository.findByProjectLeader_Id(u);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return List.of();
    }

}
