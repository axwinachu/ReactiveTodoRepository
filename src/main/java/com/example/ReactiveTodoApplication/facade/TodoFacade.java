package com.example.ReactiveTodoApplication.facade;

import com.example.ReactiveTodoApplication.Dto.TodoDto;
import com.example.ReactiveTodoApplication.mapper.Mapper;
import com.example.ReactiveTodoApplication.model.Todo;
import com.example.ReactiveTodoApplication.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TodoFacade {
    private final TodoService todoService;
    private final Mapper mapper;
    public Flux<TodoDto> getAllTodos() {
        return todoService.getAllTodos().map(mapper::entityToMapper);
    }

    public Mono<TodoDto> getById(Long id) {
        return todoService.getById(id).map(mapper::entityToMapper);
    }

    public Mono<TodoDto> addTask(TodoDto todoDto) {
         Todo newTask=mapper.dtoToEntity(todoDto);
         return todoService.addTask(newTask).map(mapper::entityToMapper);
    }

    public Mono<TodoDto> updateTask(TodoDto todoDto) {
        return todoService.getById(todoDto.getId())
                .map(existing->Todo.builder()
                        .id(existing.getId()).task(todoDto.getTask())
                        .status(todoDto.isStatus()).createdAt(existing.getCreatedAt())
                        .updatedAt(LocalDateTime.now()).build()).flatMap(todoService::updateTask)
                        .map(mapper::entityToMapper);

    }
    public Mono<Void> deleteTask(Long id){
        return todoService.deleteTask(id);
    }
}
