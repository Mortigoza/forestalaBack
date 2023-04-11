package com.asj.forestala2.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandler {

    /* Elementos no encontrados */
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleEnteredDataNotFound(HttpServletRequest request, NotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(buildResponse(e.getMessage(), HttpStatus.NOT_FOUND));
    }

    /* 500 */
    @org.springframework.web.bind.annotation.ExceptionHandler(ErrorProcessException.class)
    public ResponseEntity<?> handleEnteredDataConflict(HttpServletRequest request,
                                                       ErrorProcessException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }
    /* 400 */
    @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequest(HttpServletRequest request, BadRequestException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildResponse(e.getMessage(), HttpStatus.BAD_REQUEST));
    }
    /* validation constroller request */
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> argumentNotValidationRequest(HttpServletRequest request, MethodArgumentNotValidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildResponse(e.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST));
    }


    private ErrorResponse buildResponse(String message, HttpStatus httpStatus) {
        return new ErrorResponse(message, httpStatus.value());
    }
}
