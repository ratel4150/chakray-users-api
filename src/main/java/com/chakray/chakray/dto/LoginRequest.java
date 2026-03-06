// File: chakray/src/main/java/com/chakray/chakray/dto/LoginRequest.java
package com.chakray.chakray.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO utilizado para la autenticación de usuarios.
 */
@Schema(description = "Objeto de autenticación de usuario")
public class LoginRequest {
     @Schema(description = "RFC del usuario utilizado como username", example = "AARR990101XXX")
    private String taxId;
    @Schema(description = "Contraseña del usuario", example = "mypassword123")
    private String password;

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}