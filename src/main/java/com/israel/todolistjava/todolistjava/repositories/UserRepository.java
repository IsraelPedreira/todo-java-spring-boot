package com.israel.todolistjava.todolistjava.repositories;

import com.israel.todolistjava.todolistjava.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    public UserEntity findByEmail(String email);

    public UserDetails findByUsername(String login);


}
