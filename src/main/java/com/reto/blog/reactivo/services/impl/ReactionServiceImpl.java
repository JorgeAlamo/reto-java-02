package com.reto.blog.reactivo.services.impl;

import com.reto.blog.reactivo.entities.Reaction;
import com.reto.blog.reactivo.exceptions.ResourceNotFoundException;
import com.reto.blog.reactivo.exceptions.ResourceRuleException;
import com.reto.blog.reactivo.repositories.PostRepository;
import com.reto.blog.reactivo.repositories.ReactionRepository;
import com.reto.blog.reactivo.repositories.UserRepository;
import com.reto.blog.reactivo.services.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class ReactionServiceImpl implements ReactionService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ReactionRepository reactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Flux<Reaction> findAll() {
        return this.reactionRepository.findAll();
    }

    @Override
    public Mono<Reaction> findById(String id) {
        return this.reactionRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Reaction not found.")));
    }

    @Override
    public Mono<Reaction> save(Reaction reaction) {
        return (this.postRepository.existsById(reaction.getPostId() == null
                ? "" : reaction.getPostId()))
                .flatMap(existsPost -> existsPost
                        ? (this.userRepository.existsById(reaction.getUserId() == null
                        ? "" : reaction.getUserId()))
                        .flatMap(existsUser -> existsUser
                                ? this.reactionRepository.findByUserIdAndPostId(
                                        reaction.getUserId(), reaction.getPostId())
                                .collectList().flatMap(reactions -> {
                                    reaction.setDate(LocalDate.now());
                                    return reactions.size() < 1
                                            ? this.reactionRepository.save(reaction)
                                            : Mono.error(new ResourceRuleException(
                                            "A user can only have one reaction for each post."));
                                })
                                : Mono.error(new ResourceNotFoundException(
                                "User not found.")))
                        : Mono.error(new ResourceNotFoundException(
                        "Post not found.")));
    }

    @Override
    public Mono<Reaction> update(String id, Reaction reaction) {
        return (this.postRepository.existsById(reaction.getPostId() == null
                ? "" : reaction.getPostId()))
                .flatMap(existsPost -> existsPost
                        ? (this.userRepository.existsById(reaction.getUserId() == null
                        ? "" : reaction.getUserId()))
                        .flatMap(existsUser -> existsUser
                                ? this.reactionRepository.findById(id)
                                .switchIfEmpty(Mono.error(new ResourceNotFoundException(
                                        "Reaction not found.")))
                                .flatMap(currentComment ->
                                        this.reactionRepository.findByUserIdAndPostId(
                                                reaction.getUserId(), reaction.getPostId())
                                                .collectList().flatMap(reactions -> {
                                                    reaction.setDate(LocalDate.now());
                                                    reaction.setId(currentComment.getId());
                                                    return reactions.size() < 1
                                                            ? this.reactionRepository.save(reaction)
                                                            : Mono.error(new ResourceRuleException(
                                                            "A user can only have one reaction for each post."));
                                                }))
                                : Mono.error(new ResourceNotFoundException(
                                "User not found.")))
                        : Mono.error(new ResourceNotFoundException(
                        "Post not found.")));
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.reactionRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Reaction not found.")))
                .flatMap(post -> this.reactionRepository.delete(post));
    }

}
