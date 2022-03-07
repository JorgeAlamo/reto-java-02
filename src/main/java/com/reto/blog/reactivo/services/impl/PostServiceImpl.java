package com.reto.blog.reactivo.services.impl;

import com.reto.blog.reactivo.entities.Post;
import com.reto.blog.reactivo.exceptions.ResourceNotFoundException;
import com.reto.blog.reactivo.exceptions.ResourceRuleException;
import com.reto.blog.reactivo.repositories.BlogRepository;
import com.reto.blog.reactivo.repositories.CommentRepository;
import com.reto.blog.reactivo.repositories.PostRepository;
import com.reto.blog.reactivo.repositories.ReactionRepository;
import com.reto.blog.reactivo.services.PostService;
import com.reto.blog.reactivo.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ReactionRepository reactionRepository;

    @Override
    public Flux<Post> findAll() {
        return this.postRepository.findAll();
    }

    @Override
    public Mono<Post> findById(String id) {
        return this.postRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Post not found.")));
    }

    @Override
    public Flux<Post> filterByDate(Post post) {
        return this.postRepository.findAll()
                .filter(item -> DateUtil.isSameDay(item.getDate(), LocalDate.now()));
    }

    @Override
    public Mono<Post> save(Post post) {
        return (this.blogRepository.existsById(post.getBlogId() == null
                ? "" : post.getBlogId()))
                .flatMap(existsBlog -> existsBlog
                        ? this.blogRepository.findById(post.getBlogId())
                        .flatMap(blog -> blog.getStatus().equals("activo")
                                ? this.filterByDate(post)
                                    .collectList().flatMap(posts -> {
                                        post.setDate(LocalDate.now());
                                        return posts.size() == 0
                                                ? this.postRepository.save(post)
                                                : Mono.error(new ResourceRuleException(
                                                    "Only one post can be published per day."));
                                    })
                                : Mono.error(new ResourceRuleException(
                                    "Posts can only be registered in blogs in active status.")))
                        : Mono.error(new ResourceNotFoundException(
                            "Blog not found.")));
    }

    @Override
    public Mono<Post> update(String id, Post post) {
        return (this.blogRepository.existsById(post.getBlogId() == null
                ? "" : post.getBlogId()))
                .flatMap(existsBlog -> existsBlog
                        ? this.postRepository.findById(id)
                        .switchIfEmpty(Mono.error(new ResourceNotFoundException(
                                "Post not found.")))
                        .flatMap(currentPost ->
                                        this.blogRepository.findById(post.getBlogId())
                                                .flatMap(blog -> {
                                                        post.setDate(LocalDate.now());
                                                        post.setId(currentPost.getId());
                                                        return blog.getStatus().equals("activo")
                                                            ? this.postRepository.save(post)
                                                            : Mono.error(new ResourceRuleException(
                                                            "Posts can only be registered in blogs in active status."));
                                                }))
                        : Mono.error(new ResourceNotFoundException(
                        "Blog not found.")));
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.postRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Post not found.")))
                .flatMap(post -> {
                    this.commentRepository.findByPostId(post.getId())
                            .flatMap(item -> this.commentRepository.delete(item)).subscribe();
                    this.reactionRepository.findByPostId(post.getId())
                            .flatMap(item -> this.reactionRepository.delete(item)).subscribe();
                    return this.postRepository.delete(post);
                });
    }

    @Override
    public Mono<Void> deleteAll() {
        return this.postRepository.deleteAll();
    }

}
