package com.reto.blog.reactivo.services;

import com.reto.blog.reactivo.entities.Reaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactionService {

    Flux<Reaction> findAll();
    Mono<Reaction> findById(String id);
    Mono<Reaction> save(Reaction reaction);
    Mono<Reaction> update(String id, Reaction reaction);
    Mono<Void> delete(String id);

}
