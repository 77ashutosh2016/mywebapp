package com.myEkart.web.app.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DBException extends RuntimeException{

    public DBException()
    {
        super("Error occured during updating in Db");
    }
}
