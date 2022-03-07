package com.reto.blog.reactivo.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@Document(value = "posts")
public class Post {
    @Id
    private String id;
    private String title;
    private LocalDate date;
    private String status;
    private String content;
    private String blogId;
}
