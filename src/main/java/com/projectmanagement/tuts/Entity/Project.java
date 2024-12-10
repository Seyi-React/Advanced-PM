package com.projectmanagement.tuts.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    // Many-to-One relationship with User (Project Leader)
    @ManyToOne
    @JoinColumn(name = "project_leader_id")
    private User projectLeader;

    // One-to-Many relationship with Task
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;
}
