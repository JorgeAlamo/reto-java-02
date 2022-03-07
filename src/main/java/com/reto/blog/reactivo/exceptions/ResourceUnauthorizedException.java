package com.reto.blog.reactivo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResourceUnauthorizedException extends RuntimeException {
    private HttpStatus status = HttpStatus.UNAUTHORIZED;
    private String message;

    public ResourceUnauthorizedException(String message){
        this.message = message;
    }

}
