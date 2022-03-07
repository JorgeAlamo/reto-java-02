package com.reto.blog.reactivo.services;

import com.reto.blog.reactivo.entities.Comment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommentService {
    Flux<Comment> findAll();
    Mono<Comment> findById(String id);
    Mono<Comment> save(Comment comment);
    Mono<Comment> update(String id, Comment comment);
    Mono<Void> delete(String id);
}
