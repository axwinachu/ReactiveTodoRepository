package com.example.ReactiveTodoApplication.controller;

import com.example.ReactiveTodoApplication.Dto.TodoDto;
import com.example.ReactiveTodoApplication.facade.TodoFacade;
import com.example.ReactiveTodoApplication.model.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todo")
public class TodoController {
    private final TodoFacade todoFacade;
    @GetMapping
    public Flux<TodoDto> getAllTodos(){
        return todoFacade.getAllTodos();
    }
    @GetMapping("/{id}")
    public Mono<TodoDto> getById(@PathVariable Long id){
        return todoFacade.getById(id);
    }
    @PostMapping
    public Mono<TodoDto> addTask(@RequestBody TodoDto todoDto){
        return  todoFacade.addTask(todoDto);
    }
    @PutMapping
    public Mono<TodoDto> update(@RequestBody TodoDto todoDto){
        return todoFacade.updateTask(todoDto);
    }
    @DeleteMapping("/{id}")
    public Mono<String> deleteTask(@PathVariable Long id){
        return todoFacade.deleteTask(id).then(Mono.just("deleted successfully"));
    }
}
