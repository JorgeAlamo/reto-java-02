package com.reto.blog.reactivo.services;

import com.reto.blog.reactivo.entities.Post;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostService {

    Flux<Post> findAll();
    Mono<Post> findById(String id);
    Flux<Post> filterByDate(Post post);
    Mono<Post> save(Post post);
    Mono<Post> update(String id, Post post);
    Mono<Void> delete(String id);
    Mono<Void> deleteAll();

}
