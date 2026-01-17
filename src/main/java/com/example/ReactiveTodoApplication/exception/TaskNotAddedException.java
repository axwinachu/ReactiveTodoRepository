package com.example.ReactiveTodoApplication.exception;

public class TaskNotAddedException extends RuntimeException {
    public TaskNotAddedException(String message) {
        super("task id "+message+"is not found");
    }
}
