package com.example.ReactiveTodoApplication.repository;

import com.example.ReactiveTodoApplication.model.Todo;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends R2dbcRepository<Todo,Long>{
}
