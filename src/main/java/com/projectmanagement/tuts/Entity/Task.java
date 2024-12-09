package com.projectmanagement.tuts.Entity;

import jakarta.persistence.*;

public class Task {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate dueDate;

    // Many-to-One relationship with Project
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    // Many-to-One relationship with User (Assigned To)
    @ManyToOne
    @JoinColumn(name = "assigned_to_id")
    private User assignedTo;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

}
