package com.gpro.consulting.tiers.logistique.service.validation;

import com.gpro.consulting.tiers.logistique.dto.LigneTransportCreateDTO;
import com.gpro.consulting.tiers.logistique.dto.LigneTransportUpdateDTO;
import com.gpro.consulting.tiers.logistique.service.exception.BusinessRuleException;
import org.springframework.stereotype.Component;

@Component
public class LigneValidator {

    public void validateCreate(LigneTransportCreateDTO dto) {
        if (dto == null) {
            throw BusinessRuleException.required("Le payload create ligne");
        }
        if (dto.getQuantite() != null && dto.getQuantite() < 0) {
            throw new BusinessRuleException("La quantite ligne doit etre positive");
        }
    }

    public void validateUpdate(LigneTransportUpdateDTO dto) {
        if (dto == null) {
            throw BusinessRuleException.required("Le payload update ligne");
        }
        if (dto.getQuantite() != null && dto.getQuantite() < 0) {
            throw new BusinessRuleException("La quantite ligne doit etre positive");
        }
    }
}
