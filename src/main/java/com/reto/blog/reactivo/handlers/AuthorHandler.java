package com.reto.blog.reactivo.handlers;

import com.reto.blog.reactivo.entities.Author;
import com.reto.blog.reactivo.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class AuthorHandler {

    @Autowired
    private AuthorService authorService;

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(this.authorService.findAll(), Author.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        return this.authorService
                .findById(request.pathVariable("id"))
                        .flatMap(author -> ServerResponse.ok()
                        .body(Mono.just(author), Author.class));
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(Author.class)
                .flatMap(author -> this.authorService.save(author))
                .flatMap(author -> ServerResponse
                        .ok()
                        .contentType(APPLICATION_JSON)
                        .body(Mono.just(author), Author.class));
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        return request.bodyToMono(Author.class)
                .flatMap(author -> this.authorService
                        .update(request.pathVariable("id"), author))
                .flatMap(author -> ServerResponse.ok()
                        .contentType(APPLICATION_JSON)
                        .body(Mono.just(author), Author.class));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        return this.authorService
                .delete(request.pathVariable("id"))
                .then(ServerResponse.ok().build());
    }

}
