package com.reto.blog.reactivo.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(value = "blogs")
public class Blog {
    @Id
    private String id;
    private String name;
    private String description;
    private String url;
    private String status;
    private String authorId;
}
