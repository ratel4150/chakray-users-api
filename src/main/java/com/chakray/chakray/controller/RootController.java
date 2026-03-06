// File: chakray/src/main/java/com/chakray/chakray/controller/RootController.java
package com.chakray.chakray.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller raíz de la API.
 * Sirve para verificar que la aplicación está corriendo.
 */
@RestController
public class RootController {

    /**
     * Endpoint raíz de la API.
     *
     * @return mensaje indicando que la API está activa
     */
    @GetMapping("/")
    public String root() {
        return "Users API is running 🚀";
    }

}