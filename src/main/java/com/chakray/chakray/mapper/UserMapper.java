package com.chakray.chakray.mapper;

import com.chakray.chakray.dto.UserRequest;
import com.chakray.chakray.dto.UserResponse;
import com.chakray.chakray.model.User;

/**
 * Clase encargada de convertir entre
 * DTOs y el modelo User.
 */
public class UserMapper {

    /**
     * Convierte un UserRequest en entidad User.
     *
     * @param request datos del usuario recibidos en la API
     * @return entidad User
     */
    public static User toEntity(UserRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setTaxId(request.getTaxId());
        user.setPassword(request.getPassword());

        return user;
    }

    /**
     * Convierte una entidad User en UserResponse.
     *
     * @param user entidad interna del sistema
     * @return DTO de respuesta
     */
    public static UserResponse toResponse(User user) {

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setTaxId(user.getTaxId());
        response.setCreatedAt(user.getCreatedAt());

        return response;
    }
}