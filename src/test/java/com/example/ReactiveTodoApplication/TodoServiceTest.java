package com.example.ReactiveTodoApplication;

import com.example.ReactiveTodoApplication.model.Todo;
import com.example.ReactiveTodoApplication.repository.TodoRepository;
import com.example.ReactiveTodoApplication.service.TodoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;
    @InjectMocks
    private TodoService todoService;
    @Test
    void createTodo() {

        Todo todo = Todo.builder()
                .task("Test Create")
                .status(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(todoRepository.save(todo))
                .thenReturn(Mono.just(todo));

        StepVerifier.create(todoService.addTask(todo))
                .expectNext(todo)
                .expectComplete()
                .verify();
    }
    @Test
    void readTodos() {

        Todo todo = Todo.builder()
                .id(1L)
                .task("Test Read")
                .status(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(todoRepository.findAll())
                .thenReturn(Flux.just(todo));

        StepVerifier.create(todoService.getAllTodos())
                .expectNext(todo)
                .expectComplete()
                .verify();
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
    @Test
    void updateTodo() {

        Todo updatedTodo = Todo.builder()
                .id(1L)
                .task("Updated Task")
                .status(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        when(todoRepository.save(updatedTodo))
                .thenReturn(Mono.just(updatedTodo));
        StepVerifier.create(todoService.updateTask(updatedTodo))
                .expectNext(updatedTodo)
                .expectComplete()
                .verify();
    }
    @Test
    void deleteTodo() {
        Todo todo = Todo.builder()
                .id(1L)
                .task("Delete Task")
                .status(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        when(todoRepository.findById(1L))
                .thenReturn(Mono.just(todo));
        when(todoRepository.delete(todo))
                .thenReturn(Mono.empty());
        StepVerifier.create(todoService.deleteTask(1L))
                .expectComplete()
                .verify();
    }
}
