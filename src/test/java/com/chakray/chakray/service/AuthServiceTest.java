// File: chakray/src/test/java/com/chakray/chakray/service/AuthServiceTest.java
package com.chakray.chakray.service;

import com.chakray.chakray.dto.LoginRequest;
import com.chakray.chakray.dto.UserRequest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests para autenticación.
 */
class AuthServiceTest {

    @Test
    void shouldAuthenticateUserSuccessfully() {

        UserService userService = new UserService();
        AuthService authService = new AuthService(userService);

        UserRequest request = new UserRequest();
        request.setName("Arturo");
        request.setEmail("arturo@email.com");
        request.setPhone("5551234567");
        request.setTaxId("AARR990101XXX");
        request.setPassword("password");

        userService.createUser(request);

        LoginRequest login = new LoginRequest();
        login.setTaxId("AARR990101XXX");
        login.setPassword("password");

        boolean authenticated = authService.login(login);

        assertTrue(authenticated);
    }
}