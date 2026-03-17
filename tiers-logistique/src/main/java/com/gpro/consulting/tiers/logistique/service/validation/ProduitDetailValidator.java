package com.gpro.consulting.tiers.logistique.service.validation;

import com.gpro.consulting.tiers.logistique.dto.ProduitDetailCreateDTO;
import com.gpro.consulting.tiers.logistique.dto.ProduitDetailUpdateDTO;
import com.gpro.consulting.tiers.logistique.service.exception.BusinessRuleException;
import org.springframework.stereotype.Component;

@Component
public class ProduitDetailValidator {

    public void validateCreate(ProduitDetailCreateDTO dto) {
        if (dto == null) {
            throw BusinessRuleException.required("Le payload create produit detail");
        }
        if (dto.getPoidsTotal() != null && dto.getPoidsTotal() < 0) {
            throw new BusinessRuleException("Le poids total doit etre positif");
        }
    }

    public void validateUpdate(ProduitDetailUpdateDTO dto) {
        if (dto == null) {
            throw BusinessRuleException.required("Le payload update produit detail");
        }
        if (dto.getPoidsTotal() != null && dto.getPoidsTotal() < 0) {
            throw new BusinessRuleException("Le poids total doit etre positif");
        }
    }
}
