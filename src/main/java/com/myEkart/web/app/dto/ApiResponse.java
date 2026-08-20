package com.myEkart.web.app.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private String error;


    private LocalDateTime timeStamp= LocalDateTime.now();

    public static <T> ApiResponse<T> success(String message,T data)
    {

        return ApiResponse.<T> builder()
                .success(true)
                .message(message)
                .data(data)
                .build();

    }


    public static <T> ApiResponse<T> success(T data)
    {
        return success("Operation Successful", data);

    }

    public static <T> ApiResponse<T> error(String message)
    {
        return ApiResponse.<T>builder()
                .success(false)
                .error(message)
                .build();
    }

}
