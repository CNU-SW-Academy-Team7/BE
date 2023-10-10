package com.team7.be.global.exception.handler;


import com.team7.be.global.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {


    // Er code :1
    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ErrorMessage> emailNotFoundException(EmailNotFoundException e){
        return ResponseEntity.status(e.getStatus())
                .body(ErrorMessageFactory.from(e.getStatus(),e.getErrorCode(),e.getErrorMessage()));
    }


    // ER code :2
    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ErrorMessage> passwordMismatchException(PasswordMismatchException e){
        return ResponseEntity.status(e.getStatus())
                .body(ErrorMessageFactory.from(e.getStatus(),e.getErrorCode(),e.getErrorMessage()));
    }


    // ER code :3
    @ExceptionHandler(UserEmailDuplicatedException.class)
    public ResponseEntity<ErrorMessage> userEmailDuplicatedException(UserEmailDuplicatedException e){
        return ResponseEntity.status(e.getStatus())
                .body(ErrorMessageFactory.from(e.getStatus(),e.getErrorCode(),e.getErrorMessage()));
    }


    // ER code :4
    @ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<ErrorMessage> groupNotFoundException(GroupNotFoundException e){
        return ResponseEntity.status(e.getStatus())
                .body(ErrorMessageFactory.from(e.getStatus(),e.getErrorCode(),e.getErrorMessage()));
    }



    // ER code :4
    @ExceptionHandler(SaveScheduleException.class)
    public ResponseEntity<ErrorMessage> sveScheduleException(SaveScheduleException e){
        return ResponseEntity.status(e.getStatus())
                .body(ErrorMessageFactory.from(e.getStatus(),e.getErrorCode(),e.getErrorMessage()));
    }



}
