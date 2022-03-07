package com.reto.blog.reactivo.repositories;

import com.reto.blog.reactivo.entities.Post;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PostRepository extends ReactiveMongoRepository<Post, String> {

    Flux<Post> findByBlogId(String blogId);

}
