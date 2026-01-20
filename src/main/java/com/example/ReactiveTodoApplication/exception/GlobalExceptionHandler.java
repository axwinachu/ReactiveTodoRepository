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
        return Mono.just(new Response("not found id:",ex.getMessage()));
    }
    @ExceptionHandler(DeletionFailed.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<Response> handleDeletionFail(Exception ex){
        return Mono.just(new Response("deletion failed, id:",ex.getMessage()));
    }
    @ExceptionHandler(TaskNotAddedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<Response> handleTaskNotAdded(Exception ex){
        return Mono.just(new Response("insertion failed,id:",ex.getMessage()));
    }@ExceptionHandler(UpdateFailed.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<Response> handleUpdateFailed(Exception ex){
        return Mono.just(new Response("update failed,id:",ex.getMessage()));
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<Response> globalException(Exception ex){
        return Mono.just(new Response("error",ex.getMessage()));
    }
}
