package com.example.ReactiveTodoApplication.exception;

public class DeletionFailed extends RuntimeException {
    public DeletionFailed(String message) {
        super(message);
    }
}
