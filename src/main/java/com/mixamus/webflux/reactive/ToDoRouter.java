package com.mixamus.webflux.reactive;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ToDoRouter {

    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction(ToDoHandler toDoHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/todo/{id}")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), toDoHandler::getToDo)
                        .andRoute(RequestPredicates.GET("/todo")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), toDoHandler::getToDos);
    }
}
