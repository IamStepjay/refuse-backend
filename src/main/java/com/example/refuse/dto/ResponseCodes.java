package com.example.refuse.dto;

public enum ResponseCodes {

    SUCCESS(0),
    UNAUTHORIZED(1),
    FORBIDDEN(2),
    INVALID_REQUEST(3),
    NOT_FOUND(4),
    SYSTEM_ERROR(5), 
    CONFLICT(6);

    private Integer code;

    public Integer code() {
        return code;
    }

    ResponseCodes(Integer code) {
        this.code = code;
    }

}
