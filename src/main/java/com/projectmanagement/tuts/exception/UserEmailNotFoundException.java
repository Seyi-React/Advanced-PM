package com.projectmanagement.tuts.exception;

public class UserEmailNotFoundException extends RuntimeException {
    public UserEmailNotFoundException(String userNotFound) {
        super(userNotFound);
    }
}
