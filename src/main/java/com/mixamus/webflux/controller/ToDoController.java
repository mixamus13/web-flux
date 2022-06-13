package com.mixamus.webflux.controller;

import com.mixamus.webflux.domain.ToDo;
import com.mixamus.webflux.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@RestController
@RequiredArgsConstructor
public class ToDoController {

    private final TodoRepository todoRepository;

    @GetMapping("/todo/{id}")
    public Mono<ToDo> getTodo(@PathVariable String id) {
        return this.todoRepository.getById(id);
    }

    @GetMapping("/todo")
    public Flux<ToDo> getTodoAll() {
        return this.todoRepository.findAll();
    }
}
