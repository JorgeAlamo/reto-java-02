package com.reto.blog.reactivo.services.impl;

import com.reto.blog.reactivo.entities.Author;
import com.reto.blog.reactivo.exceptions.ResourceNotFoundException;
import com.reto.blog.reactivo.repositories.AuthorRepository;
import com.reto.blog.reactivo.repositories.BlogRepository;
import com.reto.blog.reactivo.services.AuthorService;
import com.reto.blog.reactivo.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private BlogService blogService;

    @Override
    public Flux<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Mono<Author> findById(String id) {
        return this.authorRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Author not found.")));
    }

    @Override
    public Mono<Author> save(Author author) {
        return this.authorRepository.save(author);
    }

    @Override
    public Mono<Author> update(String id, Author author) {
        return this.authorRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Author not found.")))
                .flatMap(currentAuthor -> {
                    author.setId(currentAuthor.getId());
                    return this.authorRepository.save(author);
                });
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.authorRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Author not found.")))
                .flatMap(author -> {
                    this.blogRepository.findByAuthorId(author.getId())
                            .flatMap(item -> this.blogService.delete(item.getId())).subscribe();
                    return this.authorRepository.delete(author);
                });
    }

    @Override
    public Mono<Void> deleteAll() {
        return this.authorRepository.deleteAll();
    }


}
