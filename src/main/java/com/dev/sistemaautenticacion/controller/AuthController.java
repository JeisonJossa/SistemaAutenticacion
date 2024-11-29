package com.dev.sistemaautenticacion.controller;


import com.dev.sistemaautenticacion.dto.LoginRequest;
import com.dev.sistemaautenticacion.dto.RegisterRequest;
import com.dev.sistemaautenticacion.dto.UserResponse;
import com.dev.sistemaautenticacion.entity.Usuario;
import com.dev.sistemaautenticacion.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * Controlador REST para gestionar las operaciones de autenticación y registro
 * de usuarios en el sistema.
 * Proporciona endpoints para registrar nuevos usuarios y autenticar usuarios existentes.
 */
@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

    private final UsuarioService usuarioService;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param usuarioService Servicio de usuarios para manejar la lógica de autenticación y registro.
     */
    @Autowired
    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Endpoint para registrar un nuevo usuario.
     *
     * @param request Objeto DTO con los datos necesarios para registrar al usuario.
     * @return Respuesta con el usuario creado y un estado HTTP 201 (CREATED).
     */
    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterRequest request) {
        Usuario usuarioCreado = usuarioService.registrarUsuario(request);
        UserResponse response = convertirAUserResponse(usuarioCreado);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Endpoint para autenticar un usuario existente.
     *
     * @param request Objeto DTO con el correo y contraseña del usuario.
     * @return Respuesta con los datos del usuario autenticado o un error si las credenciales son inválidas.
     */
    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@Valid @RequestBody LoginRequest request) {
        UserResponse response = usuarioService.autenticarUsuario(request.getCorreo(), request.getClave());
        return ResponseEntity.ok(response);
    }

    /**
     * Método auxiliar para validar la clave del usuario.
     *
     * @param usuario La entidad Usuario.
     * @param claveIngresada La contraseña ingresada por el cliente.
     * @return true si la clave es correcta, false en caso contrario.
     */
    private boolean esClaveCorrecta(Usuario usuario, String claveIngresada) {
        // Aquí puedes implementar la lógica para verificar la contraseña cifrada
        return usuario.getClave().equals(claveIngresada); // Simplificado (reemplazar con lógica de encriptación)
    }

    /**
     * Método auxiliar para convertir una entidad Usuario en un DTO UserResponse.
     *
     * @param usuario La entidad Usuario.
     * @return Un objeto UserResponse con los datos del usuario.
     */
    private UserResponse convertirAUserResponse(Usuario usuario) {
        UserResponse response = new UserResponse();
        response.setId(usuario.getId());
        response.setNombre(usuario.getNombre());
        response.setApellido(usuario.getApellido());
        response.setCorreo(usuario.getCorreo());
        response.setFechaNacimiento(usuario.getFechaNacimiento());
        response.setTelefono(usuario.getTelefono());
        response.setDireccion(usuario.getDireccion());
        response.setCiudad(usuario.getCiudad());
        response.setPais(usuario.getPais());
        response.setRol(usuario.getRol());
        response.setEstado(usuario.getEstado());
        response.setFechaCreacion(usuario.getFechaCreacion());
        response.setFechaModificacion(usuario.getFechaModificacion());
        return response;
    }
}
