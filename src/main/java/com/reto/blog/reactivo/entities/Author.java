package com.reto.blog.reactivo.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@Document(value = "authors")
public class Author {
    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private LocalDate birthDate;
}
