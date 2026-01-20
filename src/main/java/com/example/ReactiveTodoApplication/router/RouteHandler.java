package com.example.ReactiveTodoApplication.router;

import com.example.ReactiveTodoApplication.model.Todo;
import com.example.ReactiveTodoApplication.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class RouteHandler {
    private final TodoService todoService;
    public Mono<ServerResponse> getAllTodos(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(todoService.getAllTodos(), Todo.class);
    }
    public Mono<ServerResponse> getById(ServerRequest request){
        Long id=Long.valueOf(request.pathVariable("id"));
        return todoService.getById(id)
                .flatMap(data->ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON).bodyValue(data))
                        .switchIfEmpty(ServerResponse.notFound().build());
    }
    public Mono<ServerResponse> addTodo(ServerRequest request){
        return request.bodyToMono(Todo.class)
                .flatMap(todoService::addTask)
                .flatMap(t->ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(t));
    }
    public  Mono<ServerResponse> update(ServerRequest request){
        return request.bodyToMono(Todo.class)
                .flatMap(todoService::updateTask)
                .flatMap(t-> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(t));
    }
    public Mono<ServerResponse> delete(ServerRequest request){
        Long id=Long.valueOf(request.pathVariable("id"));
        return todoService.getById(id)
                .flatMap(t->todoService.deleteTask(id))
                .flatMap(t->ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(t));
    }
}
