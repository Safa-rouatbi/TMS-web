package com.gpro.consulting.tiers.logistique.service.validation;

import com.gpro.consulting.tiers.logistique.dto.ArticleProduitCreateDTO;
import com.gpro.consulting.tiers.logistique.dto.ArticleProduitUpdateDTO;
import com.gpro.consulting.tiers.logistique.service.exception.BusinessRuleException;
import org.springframework.stereotype.Component;

@Component
public class ArticleProduitValidator {

    public void validateCreate(ArticleProduitCreateDTO dto) {
        if (dto == null) {
            throw BusinessRuleException.required("Le payload create article produit");
        }
        if (dto.getQte() != null && dto.getQte() < 0) {
            throw new BusinessRuleException("La qte article produit doit etre positive");
        }
    }

    public void validateUpdate(ArticleProduitUpdateDTO dto) {
        if (dto == null) {
            throw BusinessRuleException.required("Le payload update article produit");
        }
        if (dto.getQte() != null && dto.getQte() < 0) {
            throw new BusinessRuleException("La qte article produit doit etre positive");
        }
    }
}
