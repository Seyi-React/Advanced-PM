package com.projectmanagement.tuts.repository;

import com.projectmanagement.tuts.Entity.Project;
import com.projectmanagement.tuts.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findByProject(Project project);
}
