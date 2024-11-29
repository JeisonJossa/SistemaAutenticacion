package com.dev.sistemaautenticacion.service;

import com.dev.sistemaautenticacion.dto.RegisterRequest;
import com.dev.sistemaautenticacion.dto.UserResponse;
import com.dev.sistemaautenticacion.entity.Usuario;
import com.dev.sistemaautenticacion.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de la interfaz UsuarioService.
 * Gestiona la lógica de negocio relacionada con los usuarios, como registro,
 * autenticación, y administración de datos.
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param usuarioRepository Repositorio para interactuar con la base de datos de usuarios.
     */
    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario registrarUsuario(RegisterRequest request) {
        if (usuarioRepository.existsByCorreo(request.getCorreo())) {
            throw new IllegalArgumentException("El correo ya está registrado.");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setCorreo(request.getCorreo());
        usuario.setClave(encriptarClave(request.getClave())); // Suponiendo que existe un método para encriptar contraseñas
        usuario.setFechaNacimiento(request.getFechaNacimiento());
        usuario.setTelefono(request.getTelefono());
        usuario.setDireccion(request.getDireccion());
        usuario.setCiudad(request.getCiudad());
        usuario.setPais(request.getPais());
        usuario.setRol("usuario"); // Rol predeterminado
        usuario.setEstado("activo"); // Estado predeterminado

        return usuarioRepository.save(usuario);
    }

    @Override
    public UserResponse obtenerUsuarioPorId(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));
        return convertirAUserResponse(usuario);
    }

    @Override
    public UserResponse autenticarUsuario(String correo, String clave) {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));
        if (!usuario.getClave().equals(clave)) {
            throw new IllegalArgumentException("Credenciales inválidas.");
        }
        return convertirAUserResponse(usuario);
    }

    @Override
    public List<UserResponse> listarTodosLosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(this::convertirAUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse actualizarUsuario(Integer id, RegisterRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setTelefono(request.getTelefono());
        usuario.setDireccion(request.getDireccion());
        usuario.setCiudad(request.getCiudad());
        usuario.setPais(request.getPais());

        Usuario usuarioActualizado = usuarioRepository.save(usuario);
        return convertirAUserResponse(usuarioActualizado);
    }

    @Override
    public void eliminarUsuario(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuario no encontrado.");
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    public void cambiarRolDeUsuario(Integer id, String nuevoRol) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

        usuario.setRol(nuevoRol);
        usuarioRepository.save(usuario);
    }

    @Override
    public void cambiarEstadoDeUsuario(Integer id, String nuevoEstado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

        usuario.setEstado(nuevoEstado);
        usuarioRepository.save(usuario);
    }

    /**
     * Convierte una entidad Usuario a un DTO UserResponse.
     *
     * @param usuario La entidad Usuario.
     * @return Un objeto UserResponse.
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

    /**
     * Método auxiliar para encriptar contraseñas.
     *
     * @param clave La contraseña en texto plano.
     * @return La contraseña encriptada.
     */
    private String encriptarClave(String clave) {
        // Aquí puedes usar una biblioteca como BCrypt para encriptar la clave
        return clave; // Ejemplo simplificado (debería ser reemplazado por encriptación real)
    }
}
