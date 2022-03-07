package com.reto.blog.reactivo.services;

import com.reto.blog.reactivo.entities.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<String> login(String username, String password);
    Flux<User> findAll();
    Mono<User> findById(String id);
    Mono<User> save(User user);
    Mono<User> update(String id, User user);
    Mono<Void> delete(String id);
    Mono<Void> deleteAll();

}
