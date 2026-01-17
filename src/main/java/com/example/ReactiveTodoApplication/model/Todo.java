package com.example.ReactiveTodoApplication.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("todos")
public class Todo {
    @Id
    private Long id;
    private String task;
    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
