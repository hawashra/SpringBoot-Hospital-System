package com.awashra.hospital.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private List<String> details; // Optional for validation errors
    private LocalDateTime timestamp;

    public ErrorResponse(int status, String message) {
        this(status, message, null, LocalDateTime.now());
    }

    public ErrorResponse(int status, String message, List<String> details) {
        this(status, message, details, LocalDateTime.now());
    }
}