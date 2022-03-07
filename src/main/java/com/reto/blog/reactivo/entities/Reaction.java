package com.reto.blog.reactivo.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@Document(value = "reactions")
public class Reaction {
    @Id
    private String id;
    private String type;
    private LocalDate date;
    private String userId;
    private String postId;
}
