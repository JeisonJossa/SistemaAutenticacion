package com.dev.sistemaautenticacion.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase manejadora global de excepciones para la aplicación.
 * Centraliza el manejo de errores y personaliza las respuestas de error
 * que se devuelven a los clientes.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja excepciones de validación de argumentos (por ejemplo, DTOs inválidos).
     * @param ex Excepción lanzada por la validación de argumentos.
     * @return Respuesta con un mapa de errores específicos para cada campo inválido.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja excepciones genéricas de tipo IllegalArgumentException.
     * @param ex Excepción lanzada con argumentos no válidos.
     * @param request Información de la solicitud en la que ocurrió la excepción.
     * @return Respuesta con el mensaje de error y estado HTTP 400 (Bad Request).
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja cualquier otra excepción no controlada específicamente.
     * @param ex Excepción genérica que no se manejó en otro método.
     * @param request Información de la solicitud en la que ocurrió la excepción.
     * @return Respuesta con un mensaje genérico y estado HTTP 500 (Internal Server Error).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Ha ocurrido un error inesperado. Por favor, contacte al administrador.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
