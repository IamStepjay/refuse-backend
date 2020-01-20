package com.example.refuse.util;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AppUtils {

    public boolean isEmptyString(String str) {
        return str == null || "".equalsIgnoreCase(str);
    }
    
    public String getNotFoundMessage(String entity, String id) {
    	return entity + " with ID '" + id + "' not found";
    }
}
