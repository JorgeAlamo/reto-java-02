package com.reto.blog.reactivo.repositories;

import com.reto.blog.reactivo.entities.Author;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {

    Mono<Boolean> existsById(String id);

}
