package com.reto.blog.reactivo.services;

import com.reto.blog.reactivo.entities.Author;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AuthorService {

    Flux<Author> findAll();
    Mono<Author> findById(String id);
    Mono<Author> save(Author author);
    Mono<Author> update(String id, Author author);
    Mono<Void> delete(String id);
    Mono<Void> deleteAll();

}
