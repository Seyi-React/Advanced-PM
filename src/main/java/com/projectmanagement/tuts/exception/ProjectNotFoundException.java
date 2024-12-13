package com.projectmanagement.tuts.exception;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(String projectNotFound) {
        super(projectNotFound);
    }
}
