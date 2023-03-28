package com.example.hamgaja.users.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserException extends RuntimeException {
    private final UserErrorCode errorCode;

}