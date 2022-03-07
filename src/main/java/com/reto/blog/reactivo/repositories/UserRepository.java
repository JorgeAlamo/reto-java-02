package com.reto.blog.reactivo.repositories;

import com.reto.blog.reactivo.entities.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Mono<Boolean> existsByAuthorId(String authorId);
    Mono<Boolean> existsByUsername(String username);
    Mono<Boolean> existsByUsernameAndPassword(String username, String password);

}
