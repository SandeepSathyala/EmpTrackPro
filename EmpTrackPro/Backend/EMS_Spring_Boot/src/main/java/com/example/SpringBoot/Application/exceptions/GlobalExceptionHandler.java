package com.example.SpringBoot.Application.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice // 🔹 Global ga exceptions ni handle cheyyadaniki use chestaru
public class GlobalExceptionHandler {

    // 🔸 1. Duplicate Email Exception ni handle chestundi
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ApiErrorResponse> handleDuplicateEmail(DuplicateEmailException ex, HttpServletRequest request) {
        // ➤ Custom response body ni prepare chesthundi
        ApiErrorResponse error = new ApiErrorResponse(
                "EMP_409", // Error code
                ex.getMessage(), // Message from exception
                request.getRequestURI(), // Request path
                HttpStatus.CONFLICT.value() // HTTP 409
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    // 🔸 2. Resource Not Found Exception ni handle chestundi
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        ApiErrorResponse error = new ApiErrorResponse(
                "EMP_404",
                ex.getMessage(),
                request.getRequestURI(),
                HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // 🔸 3. Validation errors (@NotBlank, @Email, @Pattern etc. @RequestBody lo vasthayi)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();

        // ➤ All validation errors ni collect cheyyadam
        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            errors.put(fe.getField(), fe.getDefaultMessage()); // Field: Message
        }

        ApiErrorResponse error = new ApiErrorResponse(
                "EMP_400",
                "Validation failed",
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value()
        );

        // ➤ response = {"errors": {..}, "meta": {..}}
        return new ResponseEntity<>(Map.of("errors", errors, "meta", error), HttpStatus.BAD_REQUEST);
    }

    // 🔸 4. Constraint violations (@Pattern, @Size) → path variable or request param lo use chesina validations ki
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest request) {
        ApiErrorResponse error = new ApiErrorResponse(
                "EMP_400",
                ex.getMessage(),
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // 🔸 5. Catch-all for any other unknown errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception ex, HttpServletRequest request) {
        ApiErrorResponse error = new ApiErrorResponse(
                "EMP_500",
                "Internal server error: " + ex.getMessage(),
                request.getRequestURI(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
