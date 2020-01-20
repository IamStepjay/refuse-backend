package com.example.refuse.dto;

import java.util.List;

import lombok.Data;

@Data
public class Response {
	
	private Integer code;

    private String message;

    private Object result;

    private List<Error> errors;

}
