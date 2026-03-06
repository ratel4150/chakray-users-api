// File: chakray/src/main/java/com/chakray/chakray/dto/UserResponse.java
package com.chakray.chakray.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO que representa la respuesta de usuario enviada al cliente.
 * No incluye la contraseña por seguridad.
 */
@Schema(description = "Objeto de respuesta que representa un usuario")
public class UserResponse {
     @Schema(description = "Identificador único del usuario", example = "1")
    private Long id;
    @Schema(description = "Nombre completo del usuario", example = "Arturo Chavez")
    private String name;
        @Schema(description = "Correo electrónico del usuario", example = "arturo@email.com")
    private String email;
        @Schema(description = "Número telefónico del usuario", example = "+525551234567")
    private String phone;
    @Schema(description = "RFC del usuario", example = "AARR990101XXX") 
    private String taxId;
        @Schema(description = "Fecha de creación del usuario en formato dd-MM-yyyy HH:mm", example = "01-01-2024 12:00")
    private String createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}