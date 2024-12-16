package com.projectmanagement.tuts.controller;


import com.projectmanagement.tuts.DTO.TaskRequest;
import com.projectmanagement.tuts.Entity.Task;
import com.projectmanagement.tuts.exception.ProjectNotFoundException;
import com.projectmanagement.tuts.repository.TaskRepository;
import com.projectmanagement.tuts.service.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@Tag(name = "Tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("CreateTasks/{projectId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Task> createTask (@Valid @RequestBody TaskRequest taskRequest, @PathVariable Long projectId) throws  Exception {

        try {
            Task createdtask = taskService.createTask(taskRequest, projectId);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdtask);
        } catch (ProjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/{GetTaskByProjectId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Task>> getTaskId(@PathVariable("GetTaskByProjectId") Long taskId) {

        try {
          List <Task> getTaskID = taskService.getProjectTasks(taskId);
            return ResponseEntity.status(HttpStatus.OK).body(getTaskID);
        }
        catch (ProjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("taskId")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Task> getTaskById(Long taskId) {
        try{
            Task id = taskService.getTaskById(taskId);
            return ResponseEntity.status(HttpStatus.OK).body(id);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        }
    }


