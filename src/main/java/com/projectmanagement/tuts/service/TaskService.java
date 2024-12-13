package com.projectmanagement.tuts.service;

import com.projectmanagement.tuts.DTO.TaskRequest;
import com.projectmanagement.tuts.Entity.Task;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TaskService {
    Task createTask(TaskRequest taskRequest, Long id) throws Exception;
    List<Task> getProjectTasks(Long projectId) throws Exception;
}
