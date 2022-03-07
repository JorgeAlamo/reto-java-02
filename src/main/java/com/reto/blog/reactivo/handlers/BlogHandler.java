package com.reto.blog.reactivo.handlers;

import com.reto.blog.reactivo.entities.Blog;
import com.reto.blog.reactivo.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class BlogHandler {

    @Autowired
    private BlogService blogService;

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(this.blogService.findAll(), Blog.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        return this.blogService
                .findById(request.pathVariable("id"))
                .flatMap(blog -> ServerResponse.ok()
                        .body(Mono.just(blog), Blog.class));
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(Blog.class)
                .flatMap(blog -> this.blogService.save(blog))
                .flatMap(blog -> ServerResponse.ok()
                        .contentType(APPLICATION_JSON)
                        .body(Mono.just(blog), Blog.class));
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        return request.bodyToMono(Blog.class)
                .flatMap(blog -> this.blogService
                        .update(request.pathVariable("id"), blog))
                .flatMap(blog -> ServerResponse.ok()
                        .contentType(APPLICATION_JSON)
                        .body(Mono.just(blog), Blog.class));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        return this.blogService
                .delete(request.pathVariable("id"))
                .then(ServerResponse.ok().build());
    }

}
