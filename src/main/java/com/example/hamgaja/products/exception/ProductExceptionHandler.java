package com.example.hamgaja.products.exception;

import com.example.hamgaja.message.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
public class ProductExceptionHandler {
    @ExceptionHandler(value = { ProductException.class })
    protected ResponseEntity<ResponseMessage> handleCustomException(ProductException e) {
        log.error("handleCustomException throw CustomException : {}", e.getErrorCode());
        return ResponseMessage.ErrorResponse(e.getErrorCode());
    }
}
