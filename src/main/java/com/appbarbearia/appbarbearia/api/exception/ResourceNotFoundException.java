package com.appbarbearia.appbarbearia.api.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, Object identifier) {
        super(resourceName + " com identificador '" + identifier + "' não encontrado(a).");
    }
}

