package com.myEkart.web.app.exception;


import com.myEkart.web.app.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptioHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>>handlerProductNotFound(ProductNotFoundException ex)
    {
        log.error("Product not Found: {}"+ ex.getMessage());


        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(ex.getMessage()));

    }


    public ResponseEntity<ApiResponse<Void>> handlerDuplicateProduct(DuplicateResourceException ex)
    {

        log.error("Duplicate Product : {}"+ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(ex.getMessage()));


    }


    public ResponseEntity<ApiResponse<Void>> DBException(DBException ex)
    {

        log.error("Error in DB "+ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(ex.getMessage()));


    }






}
