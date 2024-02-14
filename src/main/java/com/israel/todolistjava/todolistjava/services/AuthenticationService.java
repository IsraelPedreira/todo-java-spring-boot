package com.israel.todolistjava.todolistjava.services;

import com.israel.todolistjava.todolistjava.entities.UserEntity;
import com.israel.todolistjava.todolistjava.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;
    public UserEntity create(UserEntity user){
        return this.userRepository.save(user);
    }
}
