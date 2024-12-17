package com.projectmanagement.tuts.service;


import com.projectmanagement.tuts.DTO.TaskCreationDTO;
import com.projectmanagement.tuts.Entity.Project;
import com.projectmanagement.tuts.Entity.Task;
import com.projectmanagement.tuts.exception.ProjectNotFoundException;
import com.projectmanagement.tuts.exception.TaskNotFoundException;
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
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task with ID " + taskId + " not found"));

        taskRepository.delete(task);
    }

    @Override
    public Task updateTask(Long taskId, TaskCreationDTO taskDetails) throws Exception {
        // Find the existing task
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task with ID " + taskId + " not found"));

        // Update the existing task
        existingTask.setTitle(taskDetails.getTitle());
        existingTask.setDescription(taskDetails.getDescription());
        existingTask.setStatus(taskDetails.getStatus());
        existingTask.setPriority(taskDetails.getPriority());
        existingTask.setDueDate(taskDetails.getDueDate());

        // Save the updated task
        return taskRepository.save(existingTask);
    }

}
