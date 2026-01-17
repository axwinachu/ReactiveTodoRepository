package com.example.ReactiveTodoApplication.exception;

public class UpdateFailed extends RuntimeException {
    public UpdateFailed(String message) {
        super(message);
    }
}
