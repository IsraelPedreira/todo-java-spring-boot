package com.israel.todolistjava.todolistjava.dtos;

import com.israel.todolistjava.todolistjava.enums.UserRole;

public record RegisterDTO(String username, String email, String password, UserRole role) {
}
