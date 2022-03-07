package com.reto.blog.reactivo.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@Document(value = "comments")
public class Comment {
    @Id
    private String id;
    private LocalDate date;
    private String state;
    private String comment;
    private String userId;
    private String postId;
}
