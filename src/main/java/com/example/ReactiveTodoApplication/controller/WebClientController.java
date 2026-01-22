package com.example.ReactiveTodoApplication.controller;

import com.example.ReactiveTodoApplication.Dto.PostDto;
import com.example.ReactiveTodoApplication.Dto.TodoDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
@RestController
@RequestMapping("/web-client")
public class WebClientController {
    WebClient webClient=WebClient.create("https://jsonplaceholder.typicode.com");
    @GetMapping
    public Flux<PostDto> getAllPost(){
      return webClient.get()
              .uri("/posts")
              .retrieve()
              .bodyToFlux(PostDto.class);
    }
}
