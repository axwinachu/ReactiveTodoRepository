package com.example.ReactiveTodoApplication.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TodoDto {
    private Long id;
    private String task;
    private boolean status;
}
