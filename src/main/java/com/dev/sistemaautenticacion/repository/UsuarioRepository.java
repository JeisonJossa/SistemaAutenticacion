package com.dev.sistemaautenticacion.repository;

import com.dev.sistemaautenticacion.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de repositorio para gestionar operaciones relacionadas con la entidad Usuario.
 * Extiende JpaRepository para proporcionar operaciones CRUD básicas y permite definir
 * consultas personalizadas para casos específicos.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    /**
     * Crea un nuevo usuario en la base de datos.
     *
     * @param usuario El objeto Usuario a persistir.
     * @return El usuario creado.
     */
    @SuppressWarnings("unchecked")
    @NonNull
    Usuario save(@NonNull Usuario usuario);

    /**
     * Actualiza la información de un usuario.
     *
     * @param usuario El usuario con la información actualizada.
     * @return El usuario actualizado.
     */
    @Override
    @NonNull
    Usuario  saveAndFlush(@NonNull Usuario usuario);


    /**
     * Busca un usuario por su identificador único.
     *
     * @param id El ID del usuario.
     * @return Un Optional que contiene el usuario si existe.
     */
    @NonNull
    Optional<Usuario> findById(@NonNull Integer id);

    /**
     * Busca un usuario por su correo electrónico.
     *
     * @param correo El correo electrónico del usuario.
     * @return Un Optional que contiene el usuario si existe.
     */
    Optional<Usuario> findByCorreo(String correo);

    /**
     * Busca usuarios que tengan un rol específico.
     *
     * @param rol El rol de los usuarios (ej. 'usuario', 'administrador').
     * @return Una lista de usuarios con el rol especificado.
     */
    List<Usuario> findByRol(String rol);

    /**
     * Busca usuarios que estén en un estado específico.
     *
     * @param estado El estado de los usuarios (ej. 'activo', 'inactivo').
     * @return Una lista de usuarios con el estado especificado.
     */
    List<Usuario> findByEstado(String estado);

    /**
     * Busca usuarios que residan en una ciudad específica.
     *
     * @param ciudad La ciudad de residencia.
     * @return Una lista de usuarios que residen en la ciudad especificada.
     */
    List<Usuario> findByCiudad(String ciudad);

    /**
     * Busca usuarios que residan en un país específico.
     *
     * @param pais El país de residencia.
     * @return Una lista de usuarios que residen en el país especificado.
     */
    List<Usuario> findByPais(String pais);

    /**
     * Busca usuarios creados en un rango de fechas.
     *
     * @param fechaInicio Fecha de inicio del rango.
     * @param fechaFin Fecha de fin del rango.
     * @return Una lista de usuarios creados dentro del rango de fechas especificado.
     */
    @Query("SELECT u FROM Usuario u WHERE u.fechaCreacion BETWEEN :fechaInicio AND :fechaFin")
    List<Usuario> buscarUsuariosPorRangoDeFechasDeCreacion(
            @Param("fechaInicio") Instant fechaInicio,
            @Param("fechaFin") Instant fechaFin);

    /**
     * Busca usuarios dentro de un rango de edad.
     *
     * @param edadMin Edad mínima.
     * @param edadMax Edad máxima.
     * @return Una lista de usuarios cuya edad está en el rango especificado.
     */
    @Query("SELECT u FROM Usuario u WHERE YEAR(CURRENT_DATE) - YEAR(u.fechaNacimiento) BETWEEN :edadMin AND :edadMax")
    List<Usuario> buscarUsuariosPorRangoDeEdad(
            @Param("edadMin") Integer edadMin,
            @Param("edadMax") Integer edadMax);

    /**
     * Actualiza el rol de un usuario.
     *
     * @param id El ID del usuario.
     * @param nuevoRol El nuevo rol a asignar.
     */
    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.rol = :nuevoRol WHERE u.id = :id")
    void actualizarRolDeUsuario(@Param("id") Integer id, @Param("nuevoRol") String nuevoRol);

    /**
     * Actualiza el estado de un usuario.
     *
     * @param id El ID del usuario.
     * @param nuevoEstado El nuevo estado a asignar.
     */
    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.estado = :nuevoEstado WHERE u.id = :id")
    void actualizarEstadoDeUsuario(@Param("id") Integer id, @Param("nuevoEstado") String nuevoEstado);

    /**
     * Actualiza la contraseña de un usuario.
     *
     * @param id El ID del usuario.
     * @param nuevaClave La nueva clave encriptada.
     */
    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.clave = :nuevaClave WHERE u.id = :id")
    void actualizarClave(@Param("id") Integer id, @Param("nuevaClave") String nuevaClave);

    /**
     * Actualiza el correo de un usuario.
     *
     * @param id El ID del usuario.
     * @param nuevoCorreo El nuevo correo electrónico.
     */
    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.correo = :nuevoCorreo WHERE u.id = :id")
    void actualizarCorreo(@Param("id") Integer id, @Param("nuevoCorreo") String nuevoCorreo);

    /**
     * Elimina un usuario por su identificador único.
     *
     * @param id El ID del usuario a eliminar.
     */
    @NonNull
    void deleteById(@NonNull Integer id);

    /**
     * Cuenta el número total de usuarios.
     *
     * @return El número total de usuarios.
     */
    long count();

    /**
     * Cuenta el número de usuarios con un rol específico.
     *
     * @param rol El rol de los usuarios.
     * @return El número de usuarios con el rol especificado.
     */
    long countByRol(String rol);

    /**
     * Cuenta el número de usuarios con un estado específico.
     *
     * @param estado El estado de los usuarios.
     * @return El número de usuarios con el estado especificado.
     */
    long countByEstado(String estado);

    /**
     * Valida las credenciales de un usuario.
     *
     * @param correo El correo electrónico del usuario.
     * @param clave La clave encriptada.
     * @return Verdadero si las credenciales coinciden, falso en caso contrario.
     */
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Usuario u WHERE u.correo = :correo AND u.clave = :clave")
    boolean validarCredenciales(@Param("correo") String correo, @Param("clave") String clave);

    /**
     * Verifica si un correo ya existe en la base de datos.
     *
     * @param correo El correo a verificar.
     * @return Verdadero si el correo existe, falso en caso contrario.
     */
    boolean existsByCorreo(String correo);

    /**
     * Verifica si una clave es correcta para un usuario.
     *
     * @param id El ID del usuario.
     * @param clave La clave a validar.
     * @return Verdadero si la clave coincide, falso en caso contrario.
     */
    @Query("SELECT CASE WHEN u.clave = :clave THEN true ELSE false END FROM Usuario u WHERE u.id = :id")
    boolean esClaveCorrecta(@Param("id") Integer id, @Param("clave") String clave);

    /**
     * Busca usuarios creados recientemente (últimos 7 días).
     *
     * @return Una lista de usuarios creados en los últimos 7 días.
     */
    @Query("SELECT u FROM Usuario u WHERE u.fechaCreacion >= :fechaReciente")
    List<Usuario> buscarUsuariosCreadosRecientes(@Param("fechaReciente") Instant fechaReciente);

    /**
     * Busca usuarios modificados recientemente (últimos 7 días).
     *
     * @return Una lista de usuarios modificados en los últimos 7 días.
     */
    @Query("SELECT u FROM Usuario u WHERE u.fechaModificacion >= :fechaReciente")
    List<Usuario> buscarUsuariosModificadosRecientes(@Param("fechaReciente") Instant fechaReciente);

    /**
     * Obtiene la fecha de la última modificación de un usuario.
     *
     * @param id El ID del usuario.
     * @return La fecha de la última modificación.
     */
    @Query("SELECT u.fechaModificacion FROM Usuario u WHERE u.id = :id")
    Instant obtenerUltimaModificacion(@Param("id") Integer id);
}
