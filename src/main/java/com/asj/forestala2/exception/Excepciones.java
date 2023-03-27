package com.asj.forestala2.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Excepciones extends RuntimeException{
    private String message;
    private HttpStatus httpStatus;

    public Excepciones(String message, HttpStatus httpStatus){
        super();
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
