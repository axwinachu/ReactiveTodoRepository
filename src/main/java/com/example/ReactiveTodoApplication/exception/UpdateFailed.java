package com.example.ReactiveTodoApplication.exception;

public class UpdateFailed extends RuntimeException {
    public UpdateFailed(Long id) {
        super(String.valueOf(id));
    }
}
