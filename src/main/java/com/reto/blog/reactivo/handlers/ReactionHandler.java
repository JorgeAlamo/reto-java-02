package com.reto.blog.reactivo.handlers;

import com.reto.blog.reactivo.entities.Reaction;
import com.reto.blog.reactivo.services.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class ReactionHandler {

    @Autowired
    private ReactionService reactionService;

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(this.reactionService.findAll(), Reaction.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        return this.reactionService
                .findById(request.pathVariable("id"))
                .flatMap(reaction -> ServerResponse.ok()
                        .body(Mono.just(reaction), Reaction.class));
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(Reaction.class)
                .flatMap(reaction -> this.reactionService.save(reaction))
                .flatMap(reaction -> ServerResponse.ok()
                        .contentType(APPLICATION_JSON)
                        .body(Mono.just(reaction), Reaction.class));
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        return request.bodyToMono(Reaction.class)
                .flatMap(reaction -> this.reactionService.
                        update(request.pathVariable("id"), reaction))
                .flatMap(reaction -> ServerResponse.ok()
                        .contentType(APPLICATION_JSON)
                        .body(Mono.just(reaction), Reaction.class));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        return this.reactionService
                .delete(request.pathVariable("id"))
                .then(ServerResponse.ok().build());
    }

}
