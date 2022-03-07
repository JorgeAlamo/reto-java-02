package com.reto.blog.reactivo.repositories;

import com.reto.blog.reactivo.entities.Reaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ReactionRepository extends ReactiveMongoRepository<Reaction, String> {

    Flux<Reaction> findByPostId(String postId);
    Flux<Reaction> findByUserIdAndPostId(String userId, String postId);

}
