package com.projectmanagement.tuts.service;


import com.projectmanagement.tuts.DTO.TaskRequest;
import com.projectmanagement.tuts.Entity.Project;
import com.projectmanagement.tuts.Entity.Task;
import com.projectmanagement.tuts.exception.ProjectNotFoundException;
import com.projectmanagement.tuts.repository.ProjectRepository;
import com.projectmanagement.tuts.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{


    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Override
    public Task createTask(TaskRequest taskRequest, Long projectId) throws Exception {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found"));

        Task task = Task.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .dueDate(taskRequest.getDueDate())
                .project(project)
                .build();

        // Maintain bidirectional relationship
        project.getTasks().add(task);

        return taskRepository.save(task);
    }

    @Override
    public List<Task> getProjectTasks(Long projectId) throws Exception {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found"));

        return taskRepository.findByProject(project);
    }
}
