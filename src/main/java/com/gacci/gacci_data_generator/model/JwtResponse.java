package com.gacci.gacci_data_generator.model;

import lombok.Data;

@Data
public class JwtResponse {

    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }

}
