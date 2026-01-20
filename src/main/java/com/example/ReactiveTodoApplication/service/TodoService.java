package com.example.ReactiveTodoApplication.service;

import com.example.ReactiveTodoApplication.exception.TaskIdNotFound;
import com.example.ReactiveTodoApplication.exception.TaskNotAddedException;
import com.example.ReactiveTodoApplication.exception.UpdateFailed;
import com.example.ReactiveTodoApplication.model.Todo;
import com.example.ReactiveTodoApplication.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    public Flux<Todo> getAllTodos(){
        return todoRepository.findAll();
    }
    public Mono<Todo> getById(Long id) {
        return todoRepository.findById(id)
                .switchIfEmpty(Mono.error(new TaskIdNotFound(id)));
    }
    public Mono<Todo> addTask(Todo todo) {
        return todoRepository.save(todo)
                .switchIfEmpty(Mono.error(new TaskNotAddedException(todo.getId())));
    }
    public Mono<Todo> updateTask(Todo updatedTask) {
        return todoRepository.save(updatedTask)
                .switchIfEmpty(Mono.error(new UpdateFailed(updatedTask.getId())));
    }
    public Mono<Void> deleteTask(Long id){
        return todoRepository.findById(id)
                .switchIfEmpty(Mono.error(new TaskIdNotFound(id)))
                .flatMap(todoRepository::delete);
    }
}
