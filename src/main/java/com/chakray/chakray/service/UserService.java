// File: chakray/src/main/java/com/chakray/chakray/service/UserService.java
package com.chakray.chakray.service;


import org.slf4j.LoggerFactory; 
import org.springframework.stereotype.Service;

import com.chakray.chakray.dto.UserRequest;
import com.chakray.chakray.dto.UserResponse;
import com.chakray.chakray.mapper.UserMapper;
import com.chakray.chakray.model.User;
import com.chakray.chakray.util.AESUtil;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


/**
 * Servicio encargado de gestionar la lógica de negocio
 * relacionada con los usuarios.
 *
 * <p>Los usuarios se almacenan en memoria utilizando
 * una lista (ArrayList) según los requisitos del ejercicio.</p>
 */
@Service
public class UserService {

      private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserService.class); 

    /**
     * Lista en memoria donde se almacenan los usuarios.
     */
    private final List<User> users = new ArrayList<>();

    /**
     * Contador simple para generar IDs únicos.
     */
    private Long idCounter = 1L;

   /**
 * Obtiene una lista de todos los usuarios, opcionalmente ordenada por un campo específico.
 * 
 * <p>Este método retorna un nuevo ArrayList que contiene todos los usuarios de la
 * colección interna. Si se proporcionan criterios de ordenamiento, la lista se
 * ordena según corresponda. La colección original permanece sin modificaciones.</p>
 *
 * @param sortedBy El nombre del campo por el cual ordenar los usuarios. Valores soportados:
 *                 <ul>
 *                   <li>"name" - ordena por nombre de usuario (sin distinción de mayúsculas/minúsculas)</li>
 *                   <li>"email" - ordena por dirección de correo (sin distinción de mayúsculas/minúsculas)</li>
 *                   <li>"phone" - ordena por número de teléfono (sin distinción de mayúsculas/minúsculas)</li>
 *                   <li>"tax_id" - ordena por ID fiscal (sin distinción de mayúsculas/minúsculas)</li>
 *                   <li>"created_at" - ordena por fecha de creación (sin distinción de mayúsculas/minúsculas)</li>
 *                   <li>"id" - ordena por ID de usuario</li>
 *                 </ul>
 *                 Si es null o está vacío, la lista se retorna sin ordenar.
 * @param filter   Actualmente no implementado. Reservado para funcionalidad futura de filtrado.
 * @return Un nuevo ArrayList que contiene todos los usuarios. Si se proporciona sortedBy
 *         y es válido, la lista se ordena según corresponda. Para campos de ordenamiento
 *         no soportados, se preserva el orden original.
 * 
 * @see User
 * @see ArrayList
 * @see Collections#sort(List, Comparator)
 */
  public List<User> getAllUsers(String sortedBy, String filter) {

    logger.info("=== INICIANDO getAllUsers ===");
    logger.debug("Parámetros - sortedBy: {}, filter: {}", sortedBy, filter);

    List<User> result = new ArrayList<>(users);

    // aplicar filtro
    if (filter != null && !filter.isEmpty()) {

        logger.info("Aplicando filtro: {}", filter);

        String[] parts = filter.split("\\+");

        if (parts.length == 3) {

            String field = parts[0];
            String operator = parts[1];
            String value = parts[2];

            result = result.stream()
                    .filter(user -> applyFilter(user, field, operator, value))
                    .toList();
        }
    }

    // aplicar ordenamiento
    if (sortedBy != null && !sortedBy.isEmpty()) {

        logger.info("Aplicando ordenamiento por: {}", sortedBy);

        result.sort((u1, u2) -> {

            switch (sortedBy) {

                case "name":
                    return u1.getName().compareToIgnoreCase(u2.getName());

                case "email":
                    return u1.getEmail().compareToIgnoreCase(u2.getEmail());

                case "phone":
                    return u1.getPhone().compareToIgnoreCase(u2.getPhone());

                case "tax_id":
                    return u1.getTaxId().compareToIgnoreCase(u2.getTaxId());

                case "created_at":
                    return u1.getCreatedAt().compareToIgnoreCase(u2.getCreatedAt());

                case "id":
                    return u1.getId().compareTo(u2.getId());

                default:
                    return 0;
            }
        });
    }

    logger.info("getAllUsers completado - Retornando {} usuarios", result.size());

    return result;
}

    /**
     * Crea un nuevo usuario y lo almacena en la lista.
     *
     * @param user usuario a crear
     * @return usuario creado
     */
    public UserResponse createUser(UserRequest request) {

     User user = UserMapper.toEntity(request);

    user.setPassword(AESUtil.encrypt(user.getPassword()));

    user.setId(idCounter++);
    user.setCreatedAt(getMadagascarTimestamp());

    users.add(user);

    return UserMapper.toResponse(user);
    }

    /**
     * Busca un usuario por su identificador.
     *
     * @param id identificador del usuario
     * @return usuario encontrado o vacío si no existe
     */
    public Optional<User> getUserById(Long id) {

        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param id identificador del usuario
     * @return true si fue eliminado
     */
    public boolean deleteUser(Long id) {

        return users.removeIf(user -> user.getId().equals(id));
    }

    /**
     * Actualiza parcialmente un usuario.
     *
     * @param id identificador del usuario
     * @param updatedUser datos a actualizar
     * @return usuario actualizado o null si no existe
     */
    public User updateUser(Long id, User updatedUser) {

        Optional<User> optionalUser = getUserById(id);

        if (optionalUser.isPresent()) {

            User existingUser = optionalUser.get();

            if (updatedUser.getName() != null) {
                existingUser.setName(updatedUser.getName());
            }

            if (updatedUser.getEmail() != null) {
                existingUser.setEmail(updatedUser.getEmail());
            }

            if (updatedUser.getPhone() != null) {
                existingUser.setPhone(updatedUser.getPhone());
            }

            if (updatedUser.getTaxId() != null) {
                existingUser.setTaxId(updatedUser.getTaxId());
            }

            return existingUser;
        }

        return null;
    }

    private boolean applyFilter(User user, String field, String operator, String value) {

    String fieldValue = "";

    switch (field) {

        case "name":
            fieldValue = user.getName();
            break;

        case "email":
            fieldValue = user.getEmail();
            break;

        case "phone":
            fieldValue = user.getPhone();
            break;

        case "tax_id":
            fieldValue = user.getTaxId();
            break;

        case "created_at":
            fieldValue = user.getCreatedAt();
            break;

        case "id":
            fieldValue = String.valueOf(user.getId());
            break;
    }

    if (fieldValue == null) return false;

    switch (operator) {

        case "eq":
            return fieldValue.equalsIgnoreCase(value);

        case "co":
            return fieldValue.toLowerCase().contains(value.toLowerCase());

        case "sw":
            return fieldValue.toLowerCase().startsWith(value.toLowerCase());

        case "ew":
            return fieldValue.toLowerCase().endsWith(value.toLowerCase());

        default:
            return false;
    }
}


private String getMadagascarTimestamp() {

    DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    ZonedDateTime madagascarTime =
            ZonedDateTime.now(ZoneId.of("Indian/Antananarivo"));

    return madagascarTime.format(formatter);
}

/**
 * Busca un usuario por su taxId (RFC).
 *
 * @param taxId identificador fiscal del usuario
 * @return Optional que contiene el usuario si existe
 */
public Optional<User> findByTaxId(String taxId) {

    return users.stream()
            .filter(user -> user.getTaxId().equals(taxId))
            .findFirst();
}
}

