package com.projectmanagement.tuts.service;


import com.projectmanagement.tuts.DTO.TaskRequest;
import com.projectmanagement.tuts.Entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    @Override
    public Task createTask(TaskRequest taskRequest, String userEmail) throws Exception {
        return null;
    }

    @Override
    public List<Task> getAllTasks(Long projectId) throws Exception {
        return List.of();
    }
}
