package com.reto.blog.reactivo.services.impl;

import com.reto.blog.reactivo.entities.Comment;
import com.reto.blog.reactivo.exceptions.ResourceNotFoundException;
import com.reto.blog.reactivo.exceptions.ResourceRuleException;
import com.reto.blog.reactivo.repositories.CommentRepository;
import com.reto.blog.reactivo.repositories.PostRepository;
import com.reto.blog.reactivo.repositories.UserRepository;
import com.reto.blog.reactivo.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Flux<Comment> findAll() {
        return this.commentRepository.findAll();
    }

    @Override
    public Mono<Comment> findById(String id) {
        return this.commentRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Comment not found.")));
    }

    @Override
    public Mono<Comment> save(Comment comment) {
        return (this.postRepository.existsById(comment.getPostId() == null
                ? "" : comment.getPostId()))
                .flatMap(existsPost -> existsPost
                        ? (this.userRepository.existsById(comment.getUserId() == null
                            ? "" : comment.getUserId()))
                            .flatMap(existsUser -> existsUser
                                ? this.postRepository.findById(comment.getPostId())
                                    .flatMap(post -> {
                                        comment.setDate(LocalDate.now());
                                        return post.getStatus().equals("publicado")
                                            ? this.commentRepository.save(comment)
                                            : Mono.error(new ResourceRuleException(
                                            "Comments can only be registered on posts in published status."));
                                    })
                                    : Mono.error(new ResourceNotFoundException(
                                    "User not found.")))
                        : Mono.error(new ResourceNotFoundException(
                        "Post not found.")));
    }

    @Override
    public Mono<Comment> update(String id, Comment comment) {
        return (this.postRepository.existsById(comment.getPostId() == null
                ? "" : comment.getPostId()))
                .flatMap(existsPost -> existsPost
                        ? (this.userRepository.existsById(comment.getUserId() == null
                        ? "" : comment.getUserId()))
                        .flatMap(existsUser -> existsUser
                                ? this.commentRepository.findById(id)
                                .switchIfEmpty(Mono.error(new ResourceNotFoundException(
                                        "Comment not found.")))
                                .flatMap(currentComment ->
                                        this.postRepository.findById(comment.getPostId())
                                                .flatMap(post -> {
                                                    comment.setDate(LocalDate.now());
                                                    comment.setId(currentComment.getId());
                                                    return post.getStatus().equals("publicado")
                                                            ? this.commentRepository.save(comment)
                                                            : Mono.error(new ResourceRuleException(
                                                            "Comments can only be registered on posts in published status."));
                                                }))
                        : Mono.error(new ResourceNotFoundException(
                        "User not found.")))
                : Mono.error(new ResourceNotFoundException(
                "Post not found.")));
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.commentRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Comment not found.")))
                .flatMap(post -> this.commentRepository.delete(post));
    }

}
