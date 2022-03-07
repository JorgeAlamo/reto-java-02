package com.reto.blog.reactivo.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Data
@Builder
@Document(value = "users")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    @JsonInclude(Include.NON_NULL)
    private String authorId;
}
