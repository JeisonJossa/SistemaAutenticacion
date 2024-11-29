package com.dev.sistemaautenticacion.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.*;

/**
 * Clase DTO para manejar los datos necesarios durante el inicio de sesión de un usuario.
 * Esta clase estructura los datos enviados por el cliente para autenticar al usuario,
 * asegurando que los datos sean válidos mediante validaciones básicas.
 *
 * Utiliza anotaciones de validación de Bean Validation (javax.validation) para
 * garantizar la integridad de los datos.
 *
 * Incluye Lombok para generar automáticamente los métodos getter y setter.
 */
@Getter
@Setter
public class LoginRequest {

    /**
     * Correo electrónico del usuario.
     * Es obligatorio y debe tener un formato válido.
     */
    @NotBlank(message = "El correo electrónico es obligatorio.")
    @Email(message = "El correo electrónico debe tener un formato válido.")
    private String correo;

    /**
     * Contraseña del usuario.
     * Es obligatoria y debe cumplir con un mínimo de seguridad.
     */
    @NotBlank(message = "La contraseña es obligatoria.")
    private String clave;
}
