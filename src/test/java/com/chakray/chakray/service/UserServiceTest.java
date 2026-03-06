package com.chakray.chakray.service;

import com.chakray.chakray.dto.UserRequest;
import com.chakray.chakray.dto.UserResponse;
import com.chakray.chakray.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests para UserService.
 */
class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    /**
     * Verifica que un usuario pueda ser creado correctamente.
     */
    @Test
    void shouldCreateUserSuccessfully() {

        UserRequest request = new UserRequest();
        request.setName("Arturo Chavez");
        request.setEmail("arturo@email.com");
        request.setPhone("5551234567");
        request.setTaxId("AARR990101XXX");
        request.setPassword("mypassword");

        UserResponse response = userService.createUser(request);

        assertNotNull(response);
        assertEquals("Arturo Chavez", response.getName());
        assertEquals("arturo@email.com", response.getEmail());
        assertEquals("5551234567", response.getPhone());
    }

    /**
     * Verifica que los usuarios puedan ser obtenidos.
     */
    @Test
    void shouldReturnAllUsers() {

        UserRequest request = new UserRequest();
        request.setName("Arturo");
        request.setEmail("arturo@email.com");
        request.setPhone("5551234567");
        request.setTaxId("AARR990101XXX");
        request.setPassword("password");

        userService.createUser(request);

        List<User> users = userService.getAllUsers(null, null);

        assertEquals(1, users.size());
    }

    /**
     * Verifica el ordenamiento de usuarios por nombre.
     */
    @Test
    void shouldReturnUsersSortedByName() {

        UserRequest user1 = new UserRequest();
        user1.setName("Carlos");
        user1.setEmail("carlos@email.com");
        user1.setPhone("5551231111");
        user1.setTaxId("CARL990101XXX");
        user1.setPassword("password");

        UserRequest user2 = new UserRequest();
        user2.setName("Arturo");
        user2.setEmail("arturo@email.com");
        user2.setPhone("5551232222");
        user2.setTaxId("ARTU990101XXX");
        user2.setPassword("password");

        userService.createUser(user1);
        userService.createUser(user2);

        List<User> users = userService.getAllUsers("name", null);

        assertEquals(2, users.size());
        assertEquals("Arturo", users.get(0).getName());
    }

    /**
     * Verifica todos los operadores de filtro.
     */
    @Test
    void shouldApplyAllFilterOperators() {

        UserRequest user = new UserRequest();
        user.setName("Arturo Chavez");
        user.setEmail("arturo@mail.com");
        user.setPhone("5551234567");
        user.setTaxId("AARR990101XXX");
        user.setPassword("password");

        userService.createUser(user);

        // eq
        List<User> eqResult =
                userService.getAllUsers(null, "tax_id+eq+AARR990101XXX");

        assertEquals(1, eqResult.size());

        // co
        List<User> coResult =
                userService.getAllUsers(null, "name+co+art");

        assertEquals(1, coResult.size());

        // sw
        List<User> swResult =
                userService.getAllUsers(null, "phone+sw+555");

        assertEquals(1, swResult.size());

        // ew
        List<User> ewResult =
                userService.getAllUsers(null, "email+ew+mail.com");

        assertEquals(1, ewResult.size());
    }

    /**
     * Verifica que un usuario pueda ser encontrado por ID.
     */
    @Test
    void shouldFindUserById() {

        UserRequest request = new UserRequest();
        request.setName("Arturo");
        request.setEmail("arturo@email.com");
        request.setPhone("5551234567");
        request.setTaxId("AARR990101XXX");
        request.setPassword("password");

        userService.createUser(request);

        Optional<User> user = userService.getUserById(1L);

        assertTrue(user.isPresent());
        assertEquals("Arturo", user.get().getName());
    }

    /**
     * Verifica que un usuario pueda ser actualizado.
     */
    @Test
    void shouldUpdateUser() {

        UserRequest request = new UserRequest();
        request.setName("Arturo");
        request.setEmail("arturo@email.com");
        request.setPhone("5551234567");
        request.setTaxId("AARR990101XXX");
        request.setPassword("password");

        userService.createUser(request);

        User update = new User();
        update.setName("Arturo Updated");

        User updatedUser = userService.updateUser(1L, update);

        assertNotNull(updatedUser);
        assertEquals("Arturo Updated", updatedUser.getName());
    }

    /**
     * Verifica que un usuario pueda ser eliminado.
     */
    @Test
    void shouldDeleteUser() {

        UserRequest request = new UserRequest();
        request.setName("Arturo");
        request.setEmail("arturo@email.com");
        request.setPhone("5551234567");
        request.setTaxId("AARR990101XXX");
        request.setPassword("password");

        userService.createUser(request);

        boolean deleted = userService.deleteUser(1L);

        assertTrue(deleted);
    }

    /**
     * Verifica que se pueda encontrar un usuario por taxId.
     */
    @Test
    void shouldFindUserByTaxId() {

        UserRequest request = new UserRequest();
        request.setName("Arturo");
        request.setEmail("arturo@email.com");
        request.setPhone("5551234567");
        request.setTaxId("AARR990101XXX");
        request.setPassword("password");

        userService.createUser(request);

        Optional<User> user = userService.findByTaxId("AARR990101XXX");

        assertTrue(user.isPresent());
        assertEquals("AARR990101XXX", user.get().getTaxId());
    }
}