package com.israel.todolistjava.todolistjava.dtos;

import java.util.UUID;

import com.israel.todolistjava.todolistjava.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public record TodoEntityDto (UUID id, String name, String description, int priority, String username) {

}
