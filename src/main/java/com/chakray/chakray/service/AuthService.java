// File: chakray/src/main/java/com/chakray/chakray/service/AuthService.java
package com.chakray.chakray.service;

import com.chakray.chakray.dto.LoginRequest;
import com.chakray.chakray.model.User;
import com.chakray.chakray.util.AESUtil;

import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servicio encargado de la autenticación de usuarios.
 *
 * <p>Valida las credenciales recibidas en el endpoint /login
 * utilizando el taxId como identificador del usuario y
 * comparando la contraseña cifrada con AES.</p>
 *
 * @author Arturo Raziel Chavez
 */
@Service
public class AuthService {

    private final UserService userService;

    /**
     * Constructor para inyección de dependencias.
     *
     * @param userService servicio encargado de gestionar usuarios
     */
    public AuthService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Verifica las credenciales de un usuario.
     *
     * <p>El método busca un usuario por su taxId (RFC) y compara
     * la contraseña cifrada con la almacenada en el sistema.</p>
     *
     * @param request datos de autenticación
     * @return true si las credenciales son válidas
     */
    public boolean login(LoginRequest request) {

        Optional<User> optionalUser =
                userService.findByTaxId(request.getTaxId());

        if (optionalUser.isEmpty()) {
            return false;
        }

        User user = optionalUser.get();

        String encryptedPassword =
                AESUtil.encrypt(request.getPassword());

        return user.getPassword().equals(encryptedPassword);
    }
}