package com.redch.red_ch_spring.exception;

import org.springframework.http.HttpStatus;

public class RedNotFoundException extends CustomException {


    public RedNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }


}
