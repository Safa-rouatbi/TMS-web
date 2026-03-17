package com.gpro.consulting.tiers.logistique.service.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public static NotFoundException byId(String resourceName, Object id) {
        return new NotFoundException(resourceName + " introuvable (id=" + id + ")");
    }
}
