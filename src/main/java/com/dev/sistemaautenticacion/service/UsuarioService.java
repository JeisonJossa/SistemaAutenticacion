package com.dev.sistemaautenticacion.service;

import com.dev.sistemaautenticacion.dto.RegisterRequest;
import com.dev.sistemaautenticacion.dto.UserResponse;
import com.dev.sistemaautenticacion.entity.Usuario;

import java.util.List;

/**
 * Interfaz para la capa de servicios relacionados con la entidad Usuario.
 * Define los métodos que implementarán la lógica de negocio para operaciones
 * relacionadas con los usuarios, como registro, autenticación y gestión de datos.
 */
public interface UsuarioService {

    /**
     * Registra un nuevo usuario en el sistema.
     * Valida los datos de entrada y almacena al usuario si las reglas de negocio se cumplen.
     *
     * @param request Objeto DTO que contiene los datos necesarios para registrar al usuario.
     * @return El objeto Usuario creado.
     */
    Usuario registrarUsuario(RegisterRequest request);

    /**
     * Busca un usuario por su identificador único (ID).
     *
     * @param id El ID del usuario.
     * @return Una respuesta DTO con los datos del usuario.
     */
    UserResponse obtenerUsuarioPorId(Integer id);

    /**
     * Busca un usuario por su correo electrónico.
     *
     * @param correo El correo del usuario.
     * @return Una respuesta DTO con los datos del usuario.
     */
    UserResponse  autenticarUsuario(String correo, String clave);

    /**
     * Obtiene una lista de todos los usuarios en el sistema.
     * Solo puede ser utilizada por administradores.
     *
     * @return Una lista de respuestas DTO con los datos de todos los usuarios.
     */
    List<UserResponse> listarTodosLosUsuarios();

    /**
     * Actualiza la información de un usuario existente.
     *
     * @param id El ID del usuario a actualizar.
     * @param request Objeto DTO con los datos actualizados del usuario.
     * @return La respuesta DTO del usuario actualizado.
     */
    UserResponse actualizarUsuario(Integer id, RegisterRequest request);

    /**
     * Elimina un usuario por su identificador único (ID).
     *
     * @param id El ID del usuario a eliminar.
     */
    void eliminarUsuario(Integer id);

    /**
     * Cambia el rol de un usuario en el sistema.
     * Solo puede ser realizada por administradores.
     *
     * @param id El ID del usuario.
     * @param nuevoRol El nuevo rol a asignar (usuario o administrador).
     */
    void cambiarRolDeUsuario(Integer id, String nuevoRol);

    /**
     * Cambia el estado de un usuario en el sistema.
     *
     * @param id El ID del usuario.
     * @param nuevoEstado El nuevo estado a asignar (activo o inactivo).
     */
    void cambiarEstadoDeUsuario(Integer id, String nuevoEstado);
}
