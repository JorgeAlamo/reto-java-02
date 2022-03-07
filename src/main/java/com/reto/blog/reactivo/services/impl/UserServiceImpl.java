package com.reto.blog.reactivo.services.impl;

import com.reto.blog.reactivo.entities.User;
import com.reto.blog.reactivo.exceptions.ResourceNotFoundException;
import com.reto.blog.reactivo.exceptions.ResourceRuleException;
import com.reto.blog.reactivo.exceptions.ResourceUnauthorizedException;
import com.reto.blog.reactivo.repositories.AuthorRepository;
import com.reto.blog.reactivo.repositories.UserRepository;
import com.reto.blog.reactivo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Mono<String> login(String username, String password) {
        return this.userRepository.existsByUsernameAndPassword(username, password)
                .flatMap(exists -> exists
                        ? Mono.just("Login success.")
                        : Mono.error(new ResourceUnauthorizedException("Incorrect username or password.")));
    }

    @Override
    public Flux<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Mono<User> findById(String id) {
        return this.userRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("User not found.")));
    }

    @Override
    public Mono<User> save(User user) {
        return (user.getAuthorId() == null
                ? Mono.just(true)
                : this.authorRepository.existsById(user.getAuthorId()))
                .flatMap(existsAuthor -> existsAuthor
                        ? this.userRepository.existsByAuthorId(
                                user.getAuthorId() == null ? "" : user.getAuthorId())
                        .flatMap(findAuthorId -> !findAuthorId
                                ? this.userRepository.existsByUsername(user.getUsername())
                                .flatMap(findUsername -> !findUsername
                                        ? this.userRepository.save(user)
                                        : Mono.error(new ResourceRuleException(
                                                "Username already exists.")))
                                : Mono.error(new ResourceRuleException(
                                        "An author must have only one user.")))
                        : Mono.error(new ResourceNotFoundException(
                                "Author not found.")));
    }

    @Override
    public Mono<User> update(String id, User user) {
        return (user.getAuthorId() == null
                ? Mono.just(true)
                : this.authorRepository.existsById(user.getAuthorId()))
                .flatMap(existsAuthor -> existsAuthor
                        ? this.userRepository.findById(id)
                        .switchIfEmpty(Mono.error(new ResourceNotFoundException(
                                "User not found.")))
                        .flatMap(currentUser ->
                                (currentUser.getAuthorId().equals(user.getAuthorId())
                                        ? Mono.just(false)
                                        : this.userRepository.existsByAuthorId(user.getAuthorId()))
                                        .flatMap(findAuthorId -> !findAuthorId
                                                ? (currentUser.getUsername().equals(user.getUsername())
                                                    ? Mono.just(false)
                                                    : this.userRepository.existsByUsername(user.getUsername()))
                                                .flatMap(findUsername -> {
                                                    user.setId(currentUser.getId());
                                                    return !findUsername
                                                            ? this.userRepository.save(user)
                                                            : Mono.error(new ResourceRuleException(
                                                                    "Username already exists."));
                                                })
                                                : Mono.error(new ResourceRuleException(
                                                        "An author must have only one user."))))
                        : Mono.error(new ResourceNotFoundException(
                                "Author not found.")));
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.userRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("User not found.")))
                .flatMap(user -> this.userRepository.delete(user));
    }

    @Override
    public Mono<Void> deleteAll() {
        return this.userRepository.deleteAll();
    }

}
