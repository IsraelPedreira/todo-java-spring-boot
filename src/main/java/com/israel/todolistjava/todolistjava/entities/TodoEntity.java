package com.israel.todolistjava.todolistjava.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

@Entity(name = "todos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NonNull
    private String name;
    private String description;
    private int priority;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="userId", updatable = false)
    private UserEntity userEntity;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
