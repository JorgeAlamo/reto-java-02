package com.reto.blog.reactivo.repositories;

import com.reto.blog.reactivo.entities.Blog;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BlogRepository extends ReactiveMongoRepository<Blog, String> {

    Mono<Boolean> existsById(String id);
    Flux<Blog> findByAuthorId(String authorId);

}
