package com.reto.blog.reactivo.routes;

import com.reto.blog.reactivo.handlers.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterConfiguration {

    @Bean
    public RouterFunction<ServerResponse> authorRoutes(AuthorHandler authorHandler) {
        return RouterFunctions.nest(RequestPredicates.path("/authors"),
                RouterFunctions
                        .route(GET(""), authorHandler::findAll)
                        .andRoute(GET("/{id}"), authorHandler::findById)
                        .andRoute(POST("").and(contentType(APPLICATION_JSON)), authorHandler::save)
                        .andRoute(PUT("/{id}").and(contentType(APPLICATION_JSON)), authorHandler::update)
                        .andRoute(DELETE("/{id}"), authorHandler::delete));
    }

    @Bean
    public RouterFunction<ServerResponse> blogRoutes(BlogHandler blogHandler) {
        return RouterFunctions.nest(RequestPredicates.path("/blogs"),
                RouterFunctions
                        .route(GET(""), blogHandler::findAll)
                        .andRoute(GET("/{id}"), blogHandler::findById)
                        .andRoute(POST("").and(contentType(APPLICATION_JSON)), blogHandler::save)
                        .andRoute(PUT("/{id}").and(contentType(APPLICATION_JSON)), blogHandler::update)
                        .andRoute(DELETE("/{id}"), blogHandler::delete));
    }

    @Bean
    public RouterFunction<ServerResponse> commentRoutes(CommentHandler commentHandler) {
        return RouterFunctions.nest(RequestPredicates.path("/comments"),
                RouterFunctions
                        .route(GET(""), commentHandler::findAll)
                        .andRoute(GET("/{id}"), commentHandler::findById)
                        .andRoute(POST("").and(contentType(APPLICATION_JSON)), commentHandler::save)
                        .andRoute(PUT("/{id}").and(contentType(APPLICATION_JSON)), commentHandler::update)
                        .andRoute(DELETE("/{id}"), commentHandler::delete));
    }

    @Bean
    public RouterFunction<ServerResponse> postRoutes(PostHandler postHandler) {
        return RouterFunctions.nest(RequestPredicates.path("/posts"),
                RouterFunctions
                        .route(GET(""), postHandler::findAll)
                        .andRoute(GET("/{id}"), postHandler::findById)
                        .andRoute(POST("").and(contentType(APPLICATION_JSON)), postHandler::save)
                        .andRoute(PUT("/{id}").and(contentType(APPLICATION_JSON)), postHandler::update)
                        .andRoute(DELETE("/{id}"), postHandler::delete));
    }

    @Bean
    public RouterFunction<ServerResponse> reactionRoutes(ReactionHandler reactionHandler) {
        return RouterFunctions.nest(RequestPredicates.path("/reactions"),
                RouterFunctions
                        .route(GET(""), reactionHandler::findAll)
                        .andRoute(GET("/{id}"), reactionHandler::findById)
                        .andRoute(POST("").and(contentType(APPLICATION_JSON)), reactionHandler::save)
                        .andRoute(PUT("/{id}").and(contentType(APPLICATION_JSON)), reactionHandler::update)
                        .andRoute(DELETE("/{id}"), reactionHandler::delete));
    }

    @Bean
    public RouterFunction<ServerResponse> userRoutes(UserHandler userHandler) {
        return RouterFunctions.nest(RequestPredicates.path("/users"),
                RouterFunctions
                        .route(GET(""), userHandler::findAll)
                        .andRoute(GET("/{id}"), userHandler::findById)
                        .andRoute(POST("").and(contentType(APPLICATION_JSON)), userHandler::save)
                        .andRoute(POST("/login").and(contentType(APPLICATION_JSON)), userHandler::login)
                        .andRoute(PUT("/{id}").and(contentType(APPLICATION_JSON)), userHandler::update)
                        .andRoute(DELETE("/{id}"), userHandler::delete));
    }

}
