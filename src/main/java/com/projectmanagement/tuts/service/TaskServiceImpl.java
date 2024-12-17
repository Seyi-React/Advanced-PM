package com.projectmanagement.tuts.service;


import com.projectmanagement.tuts.DTO.TaskCreationDTO;
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
    public Task createTask(TaskCreationDTO taskRequest, Long projectId) throws Exception {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found"));

        Task task = Task.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .dueDate(taskRequest.getDueDate())
                .priority(taskRequest.getPriority())
                .status(taskRequest.getStatus())
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

    @Override
    public Task getTaskById(Long taskId) throws Exception {
    return  taskRepository.findById(taskId)
                .orElseThrow(() -> new ProjectNotFoundException("Task not found"));
    }

    @Override
    public void deleteTask(Long taskId) throws Exception {
        Task taskID = taskRepository.findById(taskId)
                .orElseThrow(() -> new ProjectNotFoundException("Task with ID " + taskId + " not found"));

        taskRepository.delete(taskID);
    }


    @Override
    public Task updateProject(Long projectId, TaskCreationDTO projectDetails) throws Exception {
        // Find the existing project
        Task existingTask = taskRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project with ID " + projectId + " not found"));

        // Update the existing project
        existingTask.setTitle(projectDetails.getTitle());
        existingTask.setDescription(projectDetails.getDescription());
        existingTask.setStatus(projectDetails.getStatus());
        existingTask.setPriority(projectDetails.getPriority());
        existingTask.setDueDate(projectDetails.getDueDate());

        // Save the updated project
       return taskRepository.save(existingTask);



    }

}
