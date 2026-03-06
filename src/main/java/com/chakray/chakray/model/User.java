// File: chakray/src/main/java/com/chakray/chakray/model/User.java
package com.chakray.chakray.model;

/**
 * Representa un usuario dentro del sistema de la Users REST API.
 *
 * <p>Este modelo contiene la información básica de un usuario que será
 * almacenado en memoria dentro de un arreglo (List) según los requisitos
 * del test técnico.</p>
 *
 * <p>Campos incluidos:</p>
 * <ul>
 *     <li><b>id</b> - Identificador único del usuario.</li>
 *     <li><b>name</b> - Nombre completo del usuario.</li>
 *     <li><b>email</b> - Dirección de correo electrónico del usuario.</li>
 *     <li><b>phone</b> - Número telefónico del usuario.</li>
 *     <li><b>taxId</b> - Identificador fiscal (RFC) único del usuario.</li>
 *     <li><b>password</b> - Contraseña del usuario almacenada utilizando cifrado AES256.</li>
 *     <li><b>createdAt</b> - Fecha de creación del usuario en formato
 *     <code>dd-MM-yyyy HH:mm</code> usando la zona horaria de Madagascar.</li>
 * </ul>
 *
 * <p><b>Nota:</b> El campo <code>password</code> no debe ser expuesto en
 * las respuestas de la API por razones de seguridad.</p>
 *
 * @author Arturo Raziel Chavez
 * @version 1.0
 */
public class User {

    /**
     * Identificador único del usuario.
     */
    private Long id;

    /**
     * Nombre completo del usuario.
     */
    private String name;

    /**
     * Dirección de correo electrónico del usuario.
     */
    private String email;

    /**
     * Número de teléfono del usuario.
     */
    private String phone;

    /**
     * Identificador fiscal del usuario (RFC).
     * Este campo debe ser único en el sistema.
     */
    private String taxId;

    /**
     * Contraseña cifrada del usuario usando algoritmo AES256.
     */
    private String password;

    /**
     * Fecha y hora de creación del usuario.
     * Formato: dd-MM-yyyy HH:mm
     * Zona horaria: Indian/Antananarivo (Madagascar).
     */
    private String createdAt;

    /**
     * Obtiene el identificador del usuario.
     *
     * @return id del usuario
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del usuario.
     *
     * @param id identificador único
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return nombre del usuario
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param name nombre completo
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return email del usuario
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param email correo electrónico
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el número telefónico del usuario.
     *
     * @return número de teléfono
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Establece el número telefónico del usuario.
     *
     * @param phone número de teléfono
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Obtiene el identificador fiscal del usuario (RFC).
     *
     * @return taxId del usuario
     */
    public String getTaxId() {
        return taxId;
    }

    /**
     * Establece el identificador fiscal del usuario.
     *
     * @param taxId RFC del usuario
     */
    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    /**
     * Obtiene la contraseña cifrada del usuario.
     *
     * @return contraseña cifrada
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña cifrada del usuario.
     *
     * @param password contraseña cifrada con AES256
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene la fecha de creación del usuario.
     *
     * @return fecha de creación en formato dd-MM-yyyy HH:mm
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Establece la fecha de creación del usuario.
     *
     * @param createdAt timestamp de creación
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}