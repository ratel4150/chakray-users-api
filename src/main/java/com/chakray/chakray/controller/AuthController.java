// File: chakray/src/main/java/com/chakray/chakray/controller/AuthController.java
package com.chakray.chakray.controller;

import com.chakray.chakray.dto.LoginRequest;
import com.chakray.chakray.dto.LoginResponse;
import com.chakray.chakray.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller encargado de manejar la autenticación de usuarios.
 */
@RestController
@RequestMapping("/login")
@Tag(name = "Authentication", description = "Endpoints para autenticación de usuarios")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Endpoint de autenticación.
     */
      @Operation(
        summary = "Autenticación de usuario",
        description = "Permite autenticar un usuario utilizando su taxId (RFC) "
                + "y contraseña. Las contraseñas se validan usando AES256."
    )
    @ApiResponse(responseCode = "200", description = "Autenticación exitosa")
    @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        boolean authenticated = authService.login(request);

        if (!authenticated) {
            return ResponseEntity.status(401)
                    .body(new LoginResponse("Invalid credentials"));
        }

        return ResponseEntity.ok(new LoginResponse("Login successful"));
    }
}