// File: chakray/src/main/java/com/chakray/chakray/dto/UserRequest.java
package com.chakray.chakray.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * DTO utilizado para crear o actualizar usuarios.
 */
@Schema(description = "Objeto utilizado para crear un usuario")
public class UserRequest {
     @Schema(description = "Nombre completo del usuario", example = "Arturo Chavez")
     @NotBlank(message = "Nombre es requerido")
    private String name;
    @Schema(description = "Correo electrónico del usuario", example = "arturo@email.com")
     @Email(message = "Email debe ser valido")
    private String email;
      /**
     * Teléfono de 10 dígitos.
     */
     @Schema(description = "Número telefónico del usuario", example = "+525551234567")
    @Pattern(
        regexp = "^\\+?[0-9]{10,13}$",
        message = "Telefono debe ser de 10 digitos y puede incluir un prefijo +"
    )
    private String phone;
     /**
     * RFC mexicano.
     */
     @Schema(description = "RFC del usuario", example = "AARR990101XXX")
    @Pattern(
        regexp = "^[A-ZÑ&]{3,4}[0-9]{6}[A-Z0-9]{3}$",
        message = "RFC invalido. Debe seguir el formato: 3-4 letras mayúsculas, 6 dígitos y 3 caracteres alfanuméricos."
    )
   
    private String taxId;
    @Schema(description = "Contraseña del usuario en texto plano (se cifrará con AES256)", example = "mypassword123")
    @NotBlank(message = "Contraseña es requerida")
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}