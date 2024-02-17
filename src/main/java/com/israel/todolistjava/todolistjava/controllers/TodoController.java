package com.israel.todolistjava.todolistjava.controllers;

import com.israel.todolistjava.todolistjava.infra.security.SecurityFilter;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import com.israel.todolistjava.todolistjava.dtos.TodoEntityDto;
import com.israel.todolistjava.todolistjava.entities.TodoEntity;
import com.israel.todolistjava.todolistjava.services.TodoService;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/todos")
public class TodoController {

    TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }
    
    @PostMapping
    public ResponseEntity<List<TodoEntity>> create(@Valid @RequestBody TodoEntityDto todo, @RequestHeader (name="Authorization") String headerAuth){

        List<TodoEntity> response = this.todoService.create(todo, headerAuth);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<TodoEntity>> getAllTodos(@RequestHeader (name="Authorization") String headerAuth){
        return ResponseEntity.ok().body(this.todoService.getAll(headerAuth));
    }

    @PutMapping("{id}")
    public ResponseEntity<List<TodoEntity>> update(@PathVariable UUID id, @RequestBody TodoEntityDto entity, @RequestHeader (name="Authorization") String headerAuth) {
        List<TodoEntity> response = this.todoService.update(id, entity, headerAuth);
        
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<List<TodoEntity>> delete(@PathVariable("id") UUID id, @RequestHeader (name="Authorization") String headerAuth){
        List<TodoEntity> response = this.todoService.delete(id, headerAuth);

        return ResponseEntity.ok().body(response);
    }
}
