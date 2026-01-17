package com.example.ReactiveTodoApplication.exception;

import com.example.ReactiveTodoApplication.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TaskIdNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<Response> response(Exception ex){
        return Mono.just(new Response("not found",ex.getMessage()));
    }
}
