package com.gpro.consulting.tiers.logistique.service.command.handler;

import com.gpro.consulting.tiers.logistique.dto.ProduitDetailCreateDTO;
import com.gpro.consulting.tiers.logistique.dto.ProduitDetailUpdateDTO;
import com.gpro.consulting.tiers.logistique.mapper.ProduitDetailMapper;
import com.gpro.consulting.tiers.logistique.persistance.elementBase.entity.ProduitDetailEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.DetailOrdreTransportEntity;
import com.gpro.consulting.tiers.logistique.service.validation.ProduitDetailValidator;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class ProduitDetailCommandHandler extends BaseChildCommandHandler {

    private final ProduitDetailMapper produitDetailMapper;
    private final ProduitDetailValidator produitDetailValidator;

    public ProduitDetailCommandHandler(ProduitDetailMapper produitDetailMapper,
                                       ProduitDetailValidator produitDetailValidator) {
        this.produitDetailMapper = produitDetailMapper;
        this.produitDetailValidator = produitDetailValidator;
    }

    public ProduitDetailEntity create(DetailOrdreTransportEntity detail, ProduitDetailCreateDTO dto) {
        produitDetailValidator.validateCreate(dto);

        ProduitDetailEntity produitDetail = produitDetailMapper.toEntity(dto);
        produitDetail.setDetailOrdreTransport(detail);

        if (detail.getProduitDetailEntities() == null) {
            detail.setProduitDetailEntities(new HashSet<>());
        }

        detail.getProduitDetailEntities().add(produitDetail);
        return produitDetail;
    }

    public ProduitDetailEntity update(DetailOrdreTransportEntity detail,
                                      Long produitDetailId,
                                      ProduitDetailUpdateDTO dto) {
        produitDetailValidator.validateUpdate(dto);

        ProduitDetailEntity produitDetail = findRequired(
                detail.getProduitDetailEntities(),
                p -> p.getId() != null && p.getId().equals(produitDetailId),
                "Produit detail introuvable, id=" + produitDetailId + ", detailId=" + detail.getId());

        produitDetailMapper.updateEntityFromDTO(dto, produitDetail);
        produitDetail.setDetailOrdreTransport(detail);
        return produitDetail;
    }

    public void delete(DetailOrdreTransportEntity detail, Long produitDetailId) {
        removeRequired(
                detail.getProduitDetailEntities(),
                p -> p.getId() != null && p.getId().equals(produitDetailId),
                "Produit detail introuvable, id=" + produitDetailId + ", detailId=" + detail.getId());
    }
}
