package com.projectmanagement.tuts.DTO;


import com.projectmanagement.tuts.Entity.TaskPriority;
import com.projectmanagement.tuts.Entity.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskRequest {

    private String title;
    private String description;
    private LocalDate dueDate;
    private Long projectId;
    private Long assignedToId;
    private TaskPriority priority;
    private TaskStatus status;
}
