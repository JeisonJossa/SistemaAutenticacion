package com.dev.sistemaautenticacion.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

/**
 * Representa la entidad Usuario en el sistema de autenticación.
 * Esta clase mapea la tabla "usuario" de la base de datos y contiene
 * la información de cada usuario, como su nombre, correo, rol, estado,
 * entre otros atributos relevantes.
 * Utiliza anotaciones de JPA para definir el mapeo y de Lombok para
 * generar automáticamente los métodos getter y setter.
 */
@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {

    /**
     * Identificador único del usuario.
     * Generado automáticamente mediante la estrategia de identidad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    /**
     * Nombre del usuario. Es un campo obligatorio con un máximo de 100 caracteres.
     */
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    /**
     * Apellido del usuario. Es un campo obligatorio con un máximo de 100 caracteres.
     */
    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    /**
     * Correo electrónico único del usuario. Es un campo obligatorio
     * con un máximo de 100 caracteres.
     */
    @Column(name = "correo", nullable = false, unique = true, length = 100)
    private String correo;

    /**
     * Contraseña del usuario. Este campo debe ser almacenado de forma encriptada.
     * Es obligatorio.
     */
    @Column(name = "clave", nullable = false)
    private String clave;

    /**
     * Fecha de nacimiento del usuario. Es un campo obligatorio.
     */
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    /**
     * Teléfono del usuario. Es opcional y puede tener un máximo de 20 caracteres.
     */
    @Column(name = "telefono", length = 20)
    private String telefono;

    /**
     * Dirección del usuario. Puede contener información extensa.
     */
    @Lob
    @Column(name = "direccion")
    private String direccion;

    /**
     * Ciudad de residencia del usuario. Tiene un máximo de 100 caracteres.
     */
    @Column(name = "ciudad", length = 100)
    private String ciudad;

    /**
     * País de residencia del usuario. Tiene un máximo de 100 caracteres.
     */
    @Column(name = "pais", length = 100)
    private String pais;

    /**
     * Rol del usuario en el sistema. Puede ser 'usuario' o 'administrador'.
     * Este campo es obligatorio y está representado como texto.
     */

    @Lob
    @Column(name = "rol", nullable = false)
    private String rol;

    /**
     * Estado de la cuenta del usuario. Puede ser 'activo' o 'inactivo'.
     * Este campo es obligatorio y está representado como texto.
     */

    @Lob
    @Column(name = "estado", nullable = false)
    private String estado;

    /**
     * Fecha y hora de creación del registro.
     * Se establece automáticamente cuando se inserta un nuevo registro.
     * Este campo no es actualizable.
     */
    @Column(name = "fecha_creacion", updatable = false, insertable = false)
    private Instant fechaCreacion;

    /**
     * Fecha y hora de la última modificación del registro.
     * Se actualiza automáticamente cuando se modifica un registro existente.
     */
    @Column(name = "fecha_modificacion", insertable = false)
    private Instant fechaModificacion;


}
