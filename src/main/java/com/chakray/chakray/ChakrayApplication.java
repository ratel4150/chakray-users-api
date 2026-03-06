// File: chakray/src/main/java/com/chakray/chakray/ChakrayApplication.java
package com.chakray.chakray;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Aplicación principal de la Users API.
 * Arranca el contexto de Spring Boot.
 */
@SpringBootApplication
public class ChakrayApplication {
     /**
     * Método principal que inicia la aplicación.
     *
     * @param args argumentos de línea de comandos
     */
	public static void main(String[] args) {
		SpringApplication.run(ChakrayApplication.class, args);
	}

}
