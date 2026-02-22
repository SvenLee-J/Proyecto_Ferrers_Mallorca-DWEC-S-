package FerrersArtesans.com.Backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController // Controller REST que devuelve JSON.
@ControllerAdvice // Intercepta errores GLOBALMENTE en toda la app.
public class ValidationExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class) // Maneja errores de validacion DTO.
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Crea mapa de errores por campo.
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField(); // Nombre del campo (email, password).
            String msg = error.getDefaultMessage(); // Mensaje de validacion.
            errors.put(field, msg); // campo: "mensaje de error".
        });
        return ResponseEntity.badRequest().body(errors); // 400 + errores detallados.
    }
}
