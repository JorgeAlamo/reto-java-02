package com.reto.blog.reactivo.handlers;

import com.reto.blog.reactivo.entities.Post;
import com.reto.blog.reactivo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class PostHandler {

    @Autowired
    private PostService postService;

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(this.postService.findAll(), Post.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        return this.postService
                .findById(request.pathVariable("id"))
                .flatMap(post -> ServerResponse.ok()
                        .body(Mono.just(post), Post.class));
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(Post.class)
                .flatMap(post -> this.postService.save(post))
                .flatMap(post -> ServerResponse.ok()
                        .contentType(APPLICATION_JSON)
                        .body(Mono.just(post), Post.class));
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        return request.bodyToMono(Post.class)
                .flatMap(post -> this.postService
                        .update(request.pathVariable("id"), post))
                .flatMap(post -> ServerResponse.ok()
                        .contentType(APPLICATION_JSON)
                        .body(Mono.just(post), Post.class));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        return this.postService
                .delete(request.pathVariable("id"))
                .then(ServerResponse.ok().build());
    }

}
