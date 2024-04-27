package com.bayareasoccerevents.utils;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {

    public String generateToken() {
        // Your token generation logic here
        return UUID.randomUUID().toString();
    }
}


