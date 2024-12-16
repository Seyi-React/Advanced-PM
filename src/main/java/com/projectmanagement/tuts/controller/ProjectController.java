package com.projectmanagement.tuts.controller;

import com.projectmanagement.tuts.DTO.ProjectRequest;
import com.projectmanagement.tuts.DTO.ProjectUpdateDTO;
import com.projectmanagement.tuts.Entity.Project;
import com.projectmanagement.tuts.exception.ProjectNotFoundException;
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
import java.util.Map;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@Tag(name = "Projects")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("CreateProject")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Project> createProject(
            @Valid @RequestBody ProjectRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) throws Exception {
        Project project = projectService.createProject(request, userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    @GetMapping("/GetUserProjects")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Project>> getUserProjects(
            @AuthenticationPrincipal UserDetails userDetails
    ) throws Exception {
        List<Project> projects = projectService.getUserProjects(userDetails.getUsername());
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{GetProjectId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getProjectById(@PathVariable("GetProjectId") Long projectId) {
        try {
            Project projectID = projectService.getProjectById(projectId);
            return ResponseEntity.ok(projectID);
        } catch (ProjectNotFoundException e) {
            Map<String, String> errorResponse = Map.of(
                    "error", "Project Id not found",
                    "details", e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            Map<String, String> errorResponse = Map.of(
                    "error", "An unexpected error occurred",
                    "details", e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/{projectId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updateProject(
            @PathVariable Long projectId,
              @Valid   @RequestBody ProjectUpdateDTO projectDetails
    ) {
        try {
            ProjectUpdateDTO updatedProject = projectService.updateProject(projectId, projectDetails);
            return ResponseEntity.status(HttpStatus.OK).body(updatedProject);
        } catch (ProjectNotFoundException e) {
            Map<String, String> errorResponse = Map.of(
                    "error", "Project not found",
                    "details", e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            Map<String, String> errorResponse = Map.of(
                    "error", "Error updating project",
                    "details", e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/{projectId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId) {
        try {
            projectService.deleteProject(projectId);
            return ResponseEntity.ok(Map.of(
                    "message", "Project successfully deleted",
                    "projectId", projectId
            ));
        } catch (ProjectNotFoundException e) {
            Map<String, String> errorResponse = Map.of(
                    "error", "Project not found",
                    "details", e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            Map<String, String> errorResponse = Map.of(
                    "error", "Error deleting project",
                    "details", e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}