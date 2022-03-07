package com.reto.blog.reactivo.services;

import com.reto.blog.reactivo.entities.Blog;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BlogService {

    Flux<Blog> findAll();
    Mono<Blog> findById(String id);
    Mono<Blog> save(Blog blog);
    Mono<Blog> update(String id, Blog blog);
    Mono<Void> delete(String id);
    Mono<Void> deleteAll();

}
