package org.example.exception;

public class ResourceNotFoundException extends RuntimeException {
    // исключение в случае не найденной в бд записи
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
