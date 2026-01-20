package com.example.ReactiveTodoApplication.router;

import com.example.ReactiveTodoApplication.service.TodoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TodoRouter {
    @Bean
    RouterFunction<ServerResponse> router(RouteHandler routeHandler){
        return RouterFunctions.route()
                .GET("/fun-getAll",routeHandler::getAllTodos)
                .GET("/fun-get/{id}",routeHandler::getById)
                .POST("/fun-add",routeHandler::addTodo)
                .PUT("/fun-put",routeHandler::update)
                .DELETE("/fun-del",routeHandler::delete)
                .build();
    }
}
