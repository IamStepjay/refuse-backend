package com.example.refuse.dto;

import com.example.refuse.dto.Error;
import com.example.refuse.dto.ResponseCodes;

import lombok.Data;

@Data
public class Error {

    Integer code;
    String message;

    public Error(ResponseCodes code, String message) {
        this.code = code.code();
        this.message = message;
    }
}