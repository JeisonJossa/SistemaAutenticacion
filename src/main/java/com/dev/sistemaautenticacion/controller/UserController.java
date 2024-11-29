package com.dev.sistemaautenticacion.controller;

import com.dev.sistemaautenticacion.dto.RegisterRequest;
import com.dev.sistemaautenticacion.dto.UserResponse;
import com.dev.sistemaautenticacion.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

/**
 * Controlador REST para gestionar las operaciones relacionadas con usuarios.
 * Proporciona endpoints para obtener información del perfil, actualizar datos,
 * listar usuarios (solo para administradores) y eliminar cuentas.
 */
@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    private final UsuarioService usuarioService;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param usuarioService Servicio de usuarios para manejar la lógica de negocio.
     */
    @Autowired
    public UserController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Endpoint para obtener información del perfil de un usuario por su ID.
     *
     * @param id El identificador único del usuario.
     * @return Los datos del usuario en formato UserResponse.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer id) {
        UserResponse user = usuarioService.obtenerUsuarioPorId(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Endpoint para actualizar los datos de un usuario.
     *
     * @param id El identificador único del usuario.
     * @param request Objeto DTO con los datos actualizados del usuario.
     * @return Los datos del usuario actualizado en formato UserResponse.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Integer id, @Valid @RequestBody RegisterRequest request) {
        UserResponse updatedUser = usuarioService.actualizarUsuario(id, request);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Endpoint para listar todos los usuarios.
     * Este endpoint solo debería ser accesible por administradores.
     *
     * @return Una lista de usuarios en formato UserResponse.
     */
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = usuarioService.listarTodosLosUsuarios();
        return ResponseEntity.ok(users);
    }

    /**
     * Endpoint para eliminar un usuario por su ID.
     *
     * @param id El identificador único del usuario.
     * @return Respuesta vacía con estado HTTP 204 (No Content).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para cambiar el rol de un usuario.
     * Solo accesible por administradores.
     *
     * @param id El identificador único del usuario.
     * @param nuevoRol El nuevo rol a asignar.
     * @return Respuesta con estado HTTP 200 si el rol fue cambiado correctamente.
     */
    @PatchMapping("/{id}/role")
    public ResponseEntity<Void> changeUserRole(@PathVariable Integer id, @RequestParam String nuevoRol) {
        usuarioService.cambiarRolDeUsuario(id, nuevoRol);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint para cambiar el estado de un usuario.
     *
     * @param id El identificador único del usuario.
     * @param nuevoEstado El nuevo estado a asignar.
     * @return Respuesta con estado HTTP 200 si el estado fue cambiado correctamente.
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> changeUserStatus(@PathVariable Integer id, @RequestParam String nuevoEstado) {
        usuarioService.cambiarEstadoDeUsuario(id, nuevoEstado);
        return ResponseEntity.ok().build();
    }
}
