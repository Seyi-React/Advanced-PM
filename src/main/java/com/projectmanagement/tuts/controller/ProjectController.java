package com.projectmanagement.tuts.controller;

import com.projectmanagement.tuts.DTO.ProjectRequest;
import com.projectmanagement.tuts.Entity.Project;
import com.projectmanagement.tuts.service.ProjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@Tag(name = "Projects")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Project> createProject(
            @Valid @RequestBody ProjectRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) throws Exception {
        Project project = projectService.createProject(request, userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    @GetMapping("/GetAllProjects")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Project>> getUserProjects(
            @AuthenticationPrincipal UserDetails userDetails
    ) throws Exception {
        List<Project> projects = projectService.getUserProjects(userDetails.getUsername());
        return ResponseEntity.ok(projects);
    }
}