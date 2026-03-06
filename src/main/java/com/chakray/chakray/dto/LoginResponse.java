// File: chakray/src/main/java/com/chakray/chakray/dto/LoginResponse.java
package com.chakray.chakray.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO que representa la respuesta de autenticación.
 */
@Schema(description = "Respuesta de autenticación")
public class LoginResponse {
    @Schema(description = "Mensaje de resultado del login", example = "Login successful")
    private String message;
    
    public LoginResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}