package com.example.ReactiveTodoApplication.exception;

public class TaskNotAddedException extends RuntimeException {
    public TaskNotAddedException(Long id) {
        super(String.valueOf(id));
    }
}
