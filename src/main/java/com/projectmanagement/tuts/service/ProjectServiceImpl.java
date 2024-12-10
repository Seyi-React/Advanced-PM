package com.projectmanagement.tuts.service;

import com.projectmanagement.tuts.DTO.ProjectRequest;
import com.projectmanagement.tuts.Entity.Project;
import com.projectmanagement.tuts.Entity.User;
import com.projectmanagement.tuts.exception.UserEmailNotFoundException;
import com.projectmanagement.tuts.repository.ProjectRepository;
import com.projectmanagement.tuts.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements  ProjectService{

    private final UserRepository userRepository;

    private final ProjectRepository projectRepository;


    @Override
    public Project createProject(ProjectRequest projectRequest, String userEmail) throws Exception {
      Optional<User>  projectLeader = userRepository.findByEmail(userEmail);
        try{
            if(projectLeader.isEmpty()) {
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
}
