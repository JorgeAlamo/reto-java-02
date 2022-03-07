package com.reto.blog.reactivo.handlers;

import com.reto.blog.reactivo.entities.User;
import com.reto.blog.reactivo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class UserHandler {

    @Autowired
    private UserService userService;

    public Mono<ServerResponse> login(ServerRequest request) {
        return request.bodyToMono(User.class)
                .flatMap(user -> this.userService.login(user.getUsername(), user.getPassword()))
                .flatMap(message -> ServerResponse.ok()
                        .contentType(APPLICATION_JSON)
                        .body(Mono.just(message), String.class));
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(this.userService.findAll(), User.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        return this.userService
                .findById(request.pathVariable("id"))
                .flatMap(user -> ServerResponse.ok()
                        .body(Mono.just(user), User.class));
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(User.class)
                .flatMap(user -> this.userService.save(user))
                .flatMap(user -> ServerResponse.ok()
                        .contentType(APPLICATION_JSON)
                        .body(Mono.just(user), User.class));
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        return request.bodyToMono(User.class)
                .flatMap(user -> this.userService
                        .update(request.pathVariable("id"), user))
                .flatMap(user -> ServerResponse.ok()
                        .contentType(APPLICATION_JSON)
                        .body(Mono.just(user), User.class));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        return this.userService
                .delete(request.pathVariable("id"))
                .then(ServerResponse.ok().build());
    }

}
