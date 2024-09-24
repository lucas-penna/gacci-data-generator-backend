package com.gacci.gacci_data_generator.controller;

import com.gacci.gacci_data_generator.model.JwtResponse;
import com.gacci.gacci_data_generator.model.LoginRequest;
import com.gacci.gacci_data_generator.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = authenticationService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }
}
