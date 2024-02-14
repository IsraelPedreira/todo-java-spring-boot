package com.israel.todolistjava.todolistjava.controllers;

import com.israel.todolistjava.todolistjava.dtos.LoginDTO;
import com.israel.todolistjava.todolistjava.dtos.LoginResponseDTO;
import com.israel.todolistjava.todolistjava.dtos.RegisterDTO;
import com.israel.todolistjava.todolistjava.entities.UserEntity;
import com.israel.todolistjava.todolistjava.enums.UserRole;
import com.israel.todolistjava.todolistjava.repositories.UserRepository;
import com.israel.todolistjava.todolistjava.services.AuthenticationService;
import com.israel.todolistjava.todolistjava.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Valid LoginDTO body){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(body.username(), body.password());
        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token= this.tokenService.generateToken((UserEntity) auth.getPrincipal());
        return ResponseEntity.ok().body(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register (@RequestBody @Valid RegisterDTO body){
        if(userRepository.findByUsername(body.username()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(body.password());

        UserEntity user = new UserEntity(body.username(), body.email(), encryptedPassword, body.role());

        authenticationService.create(user);

        return ResponseEntity.ok().build();

    }
}
