package com.team7.be.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class SaveScheduleException extends RuntimeException{
    private final HttpStatus status = HttpStatus.CONFLICT;
    private final int errorCode = 5;
    private final String errorMessage;
}
