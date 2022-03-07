package com.reto.blog.reactivo.handlers;

import com.reto.blog.reactivo.entities.Comment;
import com.reto.blog.reactivo.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class CommentHandler {

    @Autowired
    private CommentService commentService;

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.commentService.findAll(), Comment.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        return this.commentService
                .findById(request.pathVariable("id"))
                .flatMap(comment -> ServerResponse.ok()
                        .body(Mono.just(comment), Comment.class));
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(Comment.class)
                .flatMap(comment -> this.commentService.save(comment))
                .flatMap(comment -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(comment), Comment.class));
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        return request.bodyToMono(Comment.class)
                .flatMap(comment -> this.commentService
                        .update(request.pathVariable("id"), comment))
                .flatMap(comment -> ServerResponse.ok()
                        .contentType(APPLICATION_JSON)
                        .body(Mono.just(comment), Comment.class));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        return this.commentService
                .delete(request.pathVariable("id"))
                .then(ServerResponse.ok().build());
    }

}
