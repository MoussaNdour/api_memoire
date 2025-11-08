package memoire.api.memoire_licence.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
//        Map<String, String> errorResponse = new HashMap<>();
//
//        if (ex.getMessage() != null && ex.getMessage().contains("password cannot be more than 72 bytes")) {
//            errorResponse.put("erreur", "Mot de passe trop long");
//            errorResponse.put("message", "Le mot de passe ne peut pas dépasser 72 caractères.");
//        } else {
//            errorResponse.put("error", "Entree invalide pour le mot de passe");
//            errorResponse.put("message", ex.getMessage());
//        }
//
//        return ResponseEntity.badRequest().body(errorResponse);
//    }
}
