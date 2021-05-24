package org.example.controller;

import org.example.exception.BadEntityException;
import org.example.exception.EntityNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(BadEntityException.class)
    public ResponseEntity<?> handleBadEntityException(BadEntityException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFound exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException (AccessDeniedException exception){
        return new ResponseEntity<>("Insufficient access rights", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleOtherException(Exception exception){
        System.out.println(exception.getMessage());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}











