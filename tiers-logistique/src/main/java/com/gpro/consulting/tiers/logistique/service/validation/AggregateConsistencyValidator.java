package com.gpro.consulting.tiers.logistique.service.validation;

import com.gpro.consulting.tiers.logistique.persistance.elementBase.entity.ArticleProduitEntity;
import com.gpro.consulting.tiers.logistique.persistance.elementBase.entity.ProduitDetailEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.DetailOrdreTransportEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.LigneTransportEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.OrdreTransportEntity;
import com.gpro.consulting.tiers.logistique.service.exception.BusinessRuleException;
import org.springframework.stereotype.Component;

@Component
public class AggregateConsistencyValidator {

    public void validate(OrdreTransportEntity ordre) {
        if (ordre == null) {
            throw BusinessRuleException.required("L'ordre transport");
        }

        if (ordre.getListDetLivraisonVente() == null) {
            return;
        }

        for (DetailOrdreTransportEntity detail : ordre.getListDetLivraisonVente()) {
            if (detail.getOrdreTransport() != ordre) {
                throw new BusinessRuleException("Detail non rattache au bon ordre transport");
            }

            if (detail.getListTransportVenteEntities() != null) {
                for (LigneTransportEntity ligne : detail.getListTransportVenteEntities()) {
                    if (ligne.getDetailOrdreTransport() != detail) {
                        throw new BusinessRuleException("Ligne non rattachee au bon detail");
                    }
                }
            }

            if (detail.getProduitDetailEntities() != null) {
                for (ProduitDetailEntity produitDetail : detail.getProduitDetailEntities()) {
                    if (produitDetail.getDetailOrdreTransport() != detail) {
                        throw new BusinessRuleException("Produit detail non rattache au bon detail");
                    }
                }
            }

            if (detail.getArticleProduits() != null) {
                for (ArticleProduitEntity article : detail.getArticleProduits()) {
                    if (article.getDetailOrdreTransport() != detail) {
                        throw new BusinessRuleException("Article produit non rattache au bon detail");
                    }
                }
            }
        }
    }
}
