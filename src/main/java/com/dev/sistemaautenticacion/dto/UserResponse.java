package com.dev.sistemaautenticacion.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

/**
 * Clase DTO para la transferencia de datos relacionados con la información del usuario.
 * Esta clase se utiliza para estructurar los datos que se envían al cliente, ocultando
 * información sensible como la contraseña.
 * Contiene atributos relevantes para representar un usuario, como su nombre, correo,
 * rol, estado, y otras características públicas.
 * Utiliza Lombok para generar automáticamente los métodos getter y setter.
 */
@Getter
@Setter
public class UserResponse {

    /**
     * Identificador único del usuario.
     * Representa el campo "id" del usuario.
     */
    private Integer id;

    /**
     * Nombre del usuario.
     */
    private String nombre;

    /**
     * Apellido del usuario.
     */
    private String apellido;

    /**
     * Correo electrónico del usuario.
     */
    private String correo;

    /**
     * Fecha de nacimiento del usuario.
     */
    private LocalDate fechaNacimiento;

    /**
     * Teléfono del usuario. Este campo es opcional.
     */
    private String telefono;

    /**
     * Dirección del usuario. Puede contener información extensa.
     */
    private String direccion;

    /**
     * Ciudad de residencia del usuario.
     */
    private String ciudad;

    /**
     * País de residencia del usuario.
     */
    private String pais;

    /**
     * Rol del usuario en el sistema.
     * Puede ser 'usuario' o 'administrador'.
     */
    private String rol;

    /**
     * Estado de la cuenta del usuario.
     * Puede ser 'activo' o 'inactivo'.
     */
    private String estado;

    /**
     * Fecha y hora de creación del registro.
     * Este valor se establece automáticamente cuando se crea el usuario.
     */
    private Instant fechaCreacion;

    /**
     * Fecha y hora de la última modificación del registro.
     * Este valor se actualiza automáticamente cuando se modifica el usuario.
     */
    private Instant fechaModificacion;
}
