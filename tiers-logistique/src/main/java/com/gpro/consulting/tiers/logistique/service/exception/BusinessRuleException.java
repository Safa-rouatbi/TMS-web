package com.gpro.consulting.tiers.logistique.service.exception;

public class BusinessRuleException extends RuntimeException {

    public BusinessRuleException(String message) {
        super(message);
    }

    public static BusinessRuleException required(String fieldLabel) {
        return new BusinessRuleException(fieldLabel + " est obligatoire");
    }
}
