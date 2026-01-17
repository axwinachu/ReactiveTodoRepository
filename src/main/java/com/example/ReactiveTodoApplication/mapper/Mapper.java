package com.example.ReactiveTodoApplication.mapper;

import com.example.ReactiveTodoApplication.Dto.TodoDto;
import com.example.ReactiveTodoApplication.model.Todo;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Component
public class Mapper {
    public TodoDto entityToMapper(Todo todo){
        return TodoDto.builder().id(todo.getId()).task(todo.getTask())
                .status(todo.getStatus()).build();
    }
    public Todo dtoToEntity(TodoDto todoDto){
        return Todo.builder().task(todoDto.getTask())
                .status(todoDto.isStatus()).createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now()).build();
    }
}
