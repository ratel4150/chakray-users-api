// File: chakray/src/main/java/com/chakray/chakray/controller/UserController.java
package com.chakray.chakray.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chakray.chakray.dto.UserRequest;
import com.chakray.chakray.dto.UserResponse;
import com.chakray.chakray.mapper.UserMapper;
import com.chakray.chakray.model.User;
import com.chakray.chakray.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

/**
 * Controller encargado de exponer los endpoints REST
 * para la gestión de usuarios.
 */
@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Endpoints para la gestión de usuarios")
public class UserController {

    private final UserService userService;

    /**
     * Inyección de dependencias del servicio de usuarios.
     *
     * @param userService servicio encargado de la lógica de negocio
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Obtiene todos los usuarios almacenados.
     *
     * Endpoint:
     * GET /users
     *
     * @return lista de usuarios
     */
    @Operation(summary = "Obtener todos los usuarios", description = "Devuelve todos los usuarios con ordenamiento y filtrado opcionales")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente")
    @GetMapping
    public List<UserResponse> getAllUsers(
            @Parameter(description = "Campo para ordenar resultados: id, name, email, phone, tax_id o created_at") @RequestParam(required = false) String sortedBy,
            @Parameter(description = "Filtro dinámico en formato field+operator+value "
                    + "Ejemplo: name+co+user") @RequestParam(required = false) String filter) {
        return userService.getAllUsers(sortedBy, filter).stream()
                .map(UserMapper::toResponse)
                .toList();
        
    }

    /**
     * Crea un nuevo usuario.
     *
     * Endpoint:
     * POST /users
     *
     * @param user datos del usuario a crear
     * @return usuario creado
     */
    @Operation(summary = "Crear usuario", description = "Crea un nuevo usuario en el sistema. "
            + "La contraseña se almacena cifrada usando AES256.")
    @ApiResponse(responseCode = "200", description = "Usuario creado correctamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    /**
     * Actualiza parcialmente un usuario existente.
     *
     * Endpoint:
     * PATCH /users/{id}
     *
     * @param id   identificador del usuario
     * @param user datos a actualizar
     * @return usuario actualizado o 404 si no existe
     */
    @Operation(summary = "Actualizar usuario", description = "Actualiza uno o más atributos de un usuario existente mediante su ID.")
    @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @Parameter(description = "ID del usuario a actualizar") @PathVariable Long id,
            @RequestBody User user) {

        User updatedUser = userService.updateUser(id, user);

        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Elimina un usuario por su ID.
     *
     * Endpoint:
     * DELETE /users/{id}
     *
     * @param id identificador del usuario
     * @return respuesta HTTP indicando el resultado
     */
    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario existente utilizando su identificador.")
    @ApiResponse(responseCode = "204", description = "Usuario eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "ID del usuario a eliminar") @PathVariable Long id) {

        boolean deleted = userService.deleteUser(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}