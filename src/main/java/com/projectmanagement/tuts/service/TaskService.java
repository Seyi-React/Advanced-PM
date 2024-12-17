package com.projectmanagement.tuts.service;

import com.projectmanagement.tuts.DTO.TaskCreationDTO;
import com.projectmanagement.tuts.Entity.Task;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TaskService {
    Task createTask(@Valid TaskCreationDTO taskRequest, Long id) throws Exception;
    List<Task> getProjectTasks(Long projectId) throws Exception;
    Task getTaskById(Long taskId) throws Exception;
    void deleteTask(Long taskId) throws Exception;
    Task updateProject(Long projectId, TaskCreationDTO projectDetails) throws Exception;
}
