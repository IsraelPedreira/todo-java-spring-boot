package com.israel.todolistjava.todolistjava.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.israel.todolistjava.todolistjava.entities.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity, UUID> {
    public List<TodoEntity> findByOrderByPriorityDesc();

}
