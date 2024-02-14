package com.israel.todolistjava.todolistjava.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.israel.todolistjava.todolistjava.entities.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;
    public String generateToken(UserEntity user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            String token = JWT.create()
                    .withIssuer("todoapp")
                    .withSubject(user.getUsername())
                    .withExpiresAt(this.getExpirationDate())
                    .sign(algorithm);

            return token;
        }catch (JWTCreationException exception){
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(this.secret);

            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withIssuer("todoapp")
                    .build();

            return jwtVerifier.verify(token).getSubject();

        }catch (JWTCreationException exception){
            return "";
        }
    }

    private Instant getExpirationDate(){
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
    }
}
