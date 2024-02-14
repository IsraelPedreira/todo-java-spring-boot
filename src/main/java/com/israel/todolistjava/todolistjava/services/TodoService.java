package com.israel.todolistjava.todolistjava.services;

import java.util.List;
import java.util.UUID;

import com.israel.todolistjava.todolistjava.entities.UserEntity;
import com.israel.todolistjava.todolistjava.infra.security.SecurityFilter;
import com.israel.todolistjava.todolistjava.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.israel.todolistjava.todolistjava.dtos.TodoEntityDto;
import com.israel.todolistjava.todolistjava.entities.TodoEntity;
import com.israel.todolistjava.todolistjava.repositories.TodoRepository;


@Service
public class TodoService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }
    
    public List<TodoEntity> create(TodoEntityDto todo, String headerAuth){
        String token = headerAuth.replace("Bearer ", "");

        String username = tokenService.validateToken(token);

        UserDetails user = userRepository.findByUsername(username);


        TodoEntity todoEntity = TodoEntity.builder()
                .description(todo.description())
                .name(todo.name())
                .priority(todo.priority())
                .userEntity((UserEntity) user)
        .build();


        this.todoRepository.save(todoEntity);
        return this.getAll();
    }

    public List<TodoEntity> getAll(){
        return this.todoRepository.findByOrderByPriorityDesc();
    }

    public List<TodoEntity> update(UUID id, TodoEntityDto entityToUpdate){
        TodoEntity entity = TodoEntity.builder()
        .id(id)
        .description(entityToUpdate.description())
        .name(entityToUpdate.name())
        .priority(entityToUpdate.priority())
        .build();
        
        this.todoRepository.save(entity);
        
        return this.getAll();
    }

    public List<TodoEntity> delete(UUID id){
        TodoEntity todo = this.todoRepository.getReferenceById(id);
        this.todoRepository.delete(todo);

        return this.getAll();
    }
}
