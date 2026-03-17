package com.gpro.consulting.tiers.logistique.service.validation;

import com.gpro.consulting.tiers.logistique.dto.DetailOrdreTransportCreateDTO;
import com.gpro.consulting.tiers.logistique.dto.DetailOrdreTransportUpdateDTO;
import com.gpro.consulting.tiers.logistique.service.exception.BusinessRuleException;
import org.springframework.stereotype.Component;

@Component
public class DetailValidator {

    public void validateCreate(DetailOrdreTransportCreateDTO dto) {
        if (dto == null) {
            throw BusinessRuleException.required("Le payload create detail");
        }
        if (dto.getQuantite() != null && dto.getQuantite() < 0) {
            throw new BusinessRuleException("La quantite detail doit etre positive");
        }
    }

    public void validateUpdate(DetailOrdreTransportUpdateDTO dto) {
        if (dto == null) {
            throw BusinessRuleException.required("Le payload update detail");
        }
        if (dto.getQuantite() != null && dto.getQuantite() < 0) {
            throw new BusinessRuleException("La quantite detail doit etre positive");
        }
    }
}
