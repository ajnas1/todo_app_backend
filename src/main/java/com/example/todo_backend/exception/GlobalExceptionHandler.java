package com.example.todo_backend.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Map<String, Object>> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, WebRequest request) {

        // Create a response body map
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("timestamp", Instant.now().toString()); // ISO-8601 timestamp
        responseBody.put("status", HttpStatus.BAD_REQUEST.value());
        responseBody.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase()); // e.g., "Bad Request"

        // Collect all validation errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        responseBody.put("errors", errors);

        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleResourceNotFound(ResourceNotFoundException  ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", Instant.now().toString());
        errorBody.put("status", HttpStatus.NOT_FOUND.value());
        errorBody.put("error", HttpStatus.NOT_FOUND.getReasonPhrase());
        errorBody.put("message", ex.getMessage());

        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String,Object>> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
        Map<String,Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", Instant.now().toString());
        errorBody.put("status", HttpStatus.CONFLICT.value());
        errorBody.put("error", HttpStatus.CONFLICT.getReasonPhrase());
        errorBody.put("message", ex.getMessage());

        return new ResponseEntity<>(errorBody, HttpStatus.CONFLICT);
     }


     @ExceptionHandler(InvalidCredentialsException.class)
     public ResponseEntity<Map<String,Object>> handleInvalidCredentialException(InvalidCredentialsException ex) {
        
         Map<String,Object> errorBody = new HashMap<>();
         errorBody.put("timestamp", Instant.now().toString());
         errorBody.put("status", HttpStatus.CONFLICT.value());
         errorBody.put("error", HttpStatus.CONFLICT.getReasonPhrase());
         errorBody.put("message", ex.getMessage());
 
         return new ResponseEntity<>(errorBody, HttpStatus.CONFLICT);
      }
}
