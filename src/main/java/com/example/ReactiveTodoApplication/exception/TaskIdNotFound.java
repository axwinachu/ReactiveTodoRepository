package com.example.ReactiveTodoApplication.exception;

public class TaskIdNotFound extends RuntimeException {
    public TaskIdNotFound(Long id) {
        super("invalid id"+id);
    }
}
