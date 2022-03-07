package com.reto.blog.reactivo.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.TEXT_PLAIN;

@Configuration
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {
    private ObjectMapper objectMapper;
    private DataBuffer dataBuffer;

    public GlobalExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();

        if (ex instanceof ResourceNotFoundException) {
            ResourceNotFoundException resourceNotFoundException = (ResourceNotFoundException) ex;
            exchange.getResponse().setStatusCode(resourceNotFoundException.getStatus());
            dataBuffer = null;
            try {
                dataBuffer = bufferFactory.wrap(objectMapper
                        .writeValueAsBytes(new HttpError(resourceNotFoundException.getMessage())));
            } catch (JsonProcessingException e) {
                dataBuffer = bufferFactory.wrap("".getBytes());
            }
            exchange.getResponse().getHeaders().setContentType(APPLICATION_JSON);
            return exchange.getResponse().writeWith(Mono.just(dataBuffer));
        }

        if (ex instanceof ResourceExistException) {
            ResourceExistException resourceExistException = (ResourceExistException) ex;
            exchange.getResponse().setStatusCode(resourceExistException.getStatus());
            dataBuffer = null;
            try {
                dataBuffer = bufferFactory.wrap(objectMapper
                        .writeValueAsBytes(new HttpError(resourceExistException.getMessage())));
            } catch (JsonProcessingException e) {
                dataBuffer = bufferFactory.wrap("".getBytes());
            }
            exchange.getResponse().getHeaders().setContentType(APPLICATION_JSON);
            return exchange.getResponse().writeWith(Mono.just(dataBuffer));
        }

        if (ex instanceof ResourceUnauthorizedException) {
            ResourceUnauthorizedException resourceUnauthorizedException = (ResourceUnauthorizedException) ex;
            exchange.getResponse().setStatusCode(resourceUnauthorizedException.getStatus());
            dataBuffer = null;
            try {
                dataBuffer = bufferFactory.wrap(objectMapper
                        .writeValueAsBytes(new HttpError(resourceUnauthorizedException.getMessage())));
            } catch (JsonProcessingException e) {
                dataBuffer = bufferFactory.wrap("".getBytes());
            }
            exchange.getResponse().getHeaders().setContentType(APPLICATION_JSON);
            return exchange.getResponse().writeWith(Mono.just(dataBuffer));
        }

        if (ex instanceof ResourceRuleException) {
            ResourceRuleException resourceRuleException = (ResourceRuleException) ex;
            exchange.getResponse().setStatusCode(resourceRuleException.getStatus());
            dataBuffer = null;
            try {
                dataBuffer = bufferFactory.wrap(objectMapper
                        .writeValueAsBytes(new HttpError(resourceRuleException.getMessage())));
            } catch (JsonProcessingException e) {
                dataBuffer = bufferFactory.wrap("".getBytes());
            }
            exchange.getResponse().getHeaders().setContentType(APPLICATION_JSON);
            return exchange.getResponse().writeWith(Mono.just(dataBuffer));
        }

        exchange.getResponse().setStatusCode(INTERNAL_SERVER_ERROR);
        exchange.getResponse().getHeaders().setContentType(TEXT_PLAIN);

        DataBuffer dataBuffer = bufferFactory.wrap("Unknown error".getBytes());
        return exchange.getResponse().writeWith(Mono.just(dataBuffer));
    }

    public static class HttpError {
        private final String message;

        HttpError(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

}
