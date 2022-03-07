package com.reto.blog.reactivo.services.impl;

import com.reto.blog.reactivo.entities.Blog;
import com.reto.blog.reactivo.exceptions.ResourceNotFoundException;
import com.reto.blog.reactivo.exceptions.ResourceRuleException;
import com.reto.blog.reactivo.repositories.AuthorRepository;
import com.reto.blog.reactivo.repositories.BlogRepository;
import com.reto.blog.reactivo.repositories.PostRepository;
import com.reto.blog.reactivo.services.BlogService;
import com.reto.blog.reactivo.services.PostService;
import com.reto.blog.reactivo.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @Override
    public Flux<Blog> findAll() {
        return this.blogRepository.findAll();
    }

    @Override
    public Mono<Blog> findById(String id) {
        return this.blogRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Blog not found.")));
    }

    @Override
    public Mono<Blog> save(Blog blog) {
        return (this.authorRepository.existsById(blog.getAuthorId() == null
                ? "" : blog.getAuthorId()))
                        .flatMap(existsAuthor -> existsAuthor
                            ? this.authorRepository.findById(blog.getAuthorId())
                                .flatMap(author ->
                                        DateUtil.calculateAge(author.getBirthDate()) > 18
                                                ? this.blogRepository.findByAuthorId(blog.getAuthorId())
                                                .collectList().flatMap(blogs -> blogs.size() < 3
                                                        ? this.blogRepository.save(blog)
                                                        : Mono.error(new ResourceRuleException(
                                                            "An author can have a maximum of 03 blogs.")))
                                                : Mono.error(new ResourceRuleException(
                                                    "Only authors over the age of 18 can have blogs.")))
                            : Mono.error(new ResourceNotFoundException(
                                "Author not found.")));
    }

    @Override
    public Mono<Blog> update(String id, Blog blog) {
        return (this.authorRepository.existsById(blog.getAuthorId() == null
                ? "" : blog.getAuthorId()))
                .flatMap(existsAuthor -> existsAuthor
                        ? this.blogRepository.findById(id)
                            .switchIfEmpty(Mono.error(new ResourceNotFoundException(
                                            "Blog not found.")))
                            .flatMap(currentBlog ->
                                    this.authorRepository.findById(blog.getAuthorId())
                                    .flatMap(author ->
                                            DateUtil.calculateAge(author.getBirthDate()) > 18
                                                    ? this.blogRepository.findByAuthorId(blog.getAuthorId())
                                                    .collectList().flatMap(blogs -> {
                                                        blog.setId(currentBlog.getId());
                                                        return blogs.size() < 3
                                                            ? this.blogRepository.save(blog)
                                                            : Mono.error(new ResourceRuleException(
                                                            "An author can have a maximum of 03 blogs."));
                                                    })
                                                    : Mono.error(new ResourceRuleException(
                                                    "Only authors over the age of 18 can have blogs."))))
                        : Mono.error(new ResourceNotFoundException(
                            "Author not found.")));
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.blogRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Blog not found.")))
                .flatMap(blog -> {
                    this.postRepository.findByBlogId(blog.getId())
                            .flatMap(item -> this.postService.delete(item.getId())).subscribe();
                    return this.blogRepository.delete(blog);
                });
    }

    @Override
    public Mono<Void> deleteAll() {
        return this.blogRepository.deleteAll();
    }

}
