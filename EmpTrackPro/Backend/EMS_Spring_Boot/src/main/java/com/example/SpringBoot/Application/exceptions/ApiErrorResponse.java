package com.example.SpringBoot.Application.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Common structure for error responses")
public class ApiErrorResponse {

    @Schema(description = "Application-specific error code", example = "EMP_404")
    private String errorCode;

    @Schema(description = "Description of the error", example = "Employee not found with ID 101")
    private String message;

    @Schema(description = "URI that caused the error", example = "/api/employees/getById/101")
    private String path;

    @Schema(description = "HTTP status code", example = "404")
    private int status;

    public ApiErrorResponse() {}

    public ApiErrorResponse(String errorCode, String message, String path, int status) {
        this.errorCode = errorCode;
        this.message = message;
        this.path = path;
        this.status = status;
    }

    // Getters and Setters
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
