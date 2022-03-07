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
public class ResourceExistException extends RuntimeException {
    private HttpStatus status = HttpStatus.BAD_REQUEST;
    private String message;

    public ResourceExistException(String message){
        this.message = message;
    }

}
