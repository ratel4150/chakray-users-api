// File: chakray/src/main/java/com/chakray/chakray/util/AESUtil.java
package com.chakray.chakray.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Utilidad para cifrar contraseñas utilizando AES256.
 */
public class AESUtil {

    private static final String ALGORITHM = "AES";

    private static final String SECRET_KEY = "12345678901234567890123456789012"; // Clave de 32 bytes para AES-256

    /**
     * Encripta una contraseña utilizando AES.
     *
     * @param password contraseña en texto plano
     * @return contraseña cifrada
     */
    public static String encrypt(String password) {

        try {

            SecretKeySpec key =
                    new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);

            Cipher cipher = Cipher.getInstance(ALGORITHM);

            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] encrypted =
                    cipher.doFinal(password.getBytes());

            return Base64.getEncoder().encodeToString(encrypted);

        } catch (Exception e) {

            throw new RuntimeException("Error encrypting password", e);
        }
    }
}