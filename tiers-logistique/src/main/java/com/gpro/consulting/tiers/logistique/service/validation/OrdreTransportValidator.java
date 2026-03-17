package com.gpro.consulting.tiers.logistique.service.validation;

import com.gpro.consulting.tiers.logistique.dto.OrdreTransportCreateDTO;
import com.gpro.consulting.tiers.logistique.dto.OrdreTransportUpdateDTO;
import com.gpro.consulting.tiers.logistique.service.exception.BusinessRuleException;
import org.springframework.stereotype.Component;

@Component
public class OrdreTransportValidator {

    public void validateCreate(OrdreTransportCreateDTO dto) {
        if (dto == null) {
            throw BusinessRuleException.required("Le payload create ordre transport");
        }
        if (dto.getReference() == null || dto.getReference().isBlank()) {
            throw BusinessRuleException.required("La reference de l'ordre transport");
        }
    }

    public void validateUpdate(OrdreTransportUpdateDTO dto) {
        if (dto == null) {
            throw BusinessRuleException.required("Le payload update ordre transport");
        }
    }
}
