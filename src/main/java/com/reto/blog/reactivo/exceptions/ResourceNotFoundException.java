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
public class ResourceNotFoundException extends RuntimeException {
    private HttpStatus status = HttpStatus.NOT_FOUND;
    private String message;

    public ResourceNotFoundException(String message){
        this.message = message;
    }

}
