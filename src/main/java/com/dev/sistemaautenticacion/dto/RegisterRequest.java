package com.dev.sistemaautenticacion.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.time.Period;

/**
 * Clase DTO para manejar los datos necesarios durante el registro de un nuevo usuario.
 * Esta clase estructura los datos enviados por el cliente para crear un usuario,
 * incluyendo validaciones básicas para garantizar que los datos sean válidos.
 * Utiliza anotaciones de validación de Bean Validation (javax.validation) para
 * garantizar la integridad de los datos.
 * Incluye Lombok para generar automáticamente los métodos getter y setter.
 */
@Getter
@Setter
public class RegisterRequest {

    /**
     * Nombre del usuario. Es obligatorio y no puede estar vacío.
     * Máximo de 100 caracteres.
     */
    @NotBlank(message = "El nombre es obligatorio.")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres.")
    private String nombre;

    /**
     * Apellido del usuario. Es obligatorio y no puede estar vacío.
     * Máximo de 100 caracteres.
     */
    @NotBlank(message = "El apellido es obligatorio.")
    @Size(max = 100, message = "El apellido no puede tener más de 100 caracteres.")
    private String apellido;

    /**
     * Correo electrónico único del usuario. Es obligatorio y debe tener formato válido.
     */
    @NotBlank(message = "El correo electrónico es obligatorio.")
    @Email(message = "El correo electrónico debe tener un formato válido.")
    @Size(max = 100, message = "El correo no puede tener más de 100 caracteres.")
    private String correo;

    /**
     * Contraseña del usuario. Es obligatoria y debe cumplir un mínimo de seguridad.
     * Este valor debe ser encriptado antes de almacenarlo en la base de datos.
     */
    @NotBlank(message = "La contraseña es obligatoria.")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres.")
    private String clave;

    /**
     * Fecha de nacimiento del usuario. Es obligatoria y debe ser una fecha en el pasado.
     */
    @NotNull(message = "La fecha de nacimiento es obligatoria.")
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada.")
    private LocalDate fechaNacimiento;

    /**
     * Teléfono del usuario. Es opcional y tiene un máximo de 20 caracteres.
     */
    @Size(max = 20, message = "El teléfono no puede tener más de 20 caracteres.")
    private String telefono;

    /**
     * Dirección del usuario. Es opcional.
     */
    private String direccion;

    /**
     * Ciudad de residencia del usuario. Es opcional y tiene un máximo de 100 caracteres.
     */
    @Size(max = 100, message = "La ciudad no puede tener más de 100 caracteres.")
    private String ciudad;

    /**
     * País de residencia del usuario. Es opcional y tiene un máximo de 100 caracteres.
     */
    @Size(max = 100, message = "El país no puede tener más de 100 caracteres.")
    private String pais;

    /**
     * Valida si el usuario es mayor de 18 años.
     *
     * @return true si el usuario tiene 18 años o más.
     */
    @AssertTrue(message = "El usuario debe ser mayor de edad.")
    public boolean isMayorDeEdad() {
        if (fechaNacimiento == null) {
            return false; // No puede validar si la fecha es nula
        }
        return Period.between(fechaNacimiento, LocalDate.now()).getYears() >= 18;
    }
}
