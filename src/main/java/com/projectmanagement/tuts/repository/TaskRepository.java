package com.projectmanagement.tuts.repository;

import com.projectmanagement.tuts.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
