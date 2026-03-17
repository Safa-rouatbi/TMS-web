package com.gpro.consulting.tiers.logistique.service.command.handler;

import com.gpro.consulting.tiers.logistique.dto.LigneTransportCreateDTO;
import com.gpro.consulting.tiers.logistique.dto.LigneTransportUpdateDTO;
import com.gpro.consulting.tiers.logistique.mapper.LigneTransportMapper;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.DetailOrdreTransportEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.LigneTransportEntity;
import com.gpro.consulting.tiers.logistique.service.validation.LigneValidator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class LigneCommandHandler extends BaseChildCommandHandler {

    private final LigneTransportMapper ligneMapper;
    private final LigneValidator ligneValidator;

    public LigneCommandHandler(LigneTransportMapper ligneMapper, LigneValidator ligneValidator) {
        this.ligneMapper = ligneMapper;
        this.ligneValidator = ligneValidator;
    }

    public LigneTransportEntity create(DetailOrdreTransportEntity detail, LigneTransportCreateDTO dto) {
        ligneValidator.validateCreate(dto);

        LigneTransportEntity ligne = ligneMapper.toEntity(dto);
        ligne.setDetailOrdreTransport(detail);

        if (detail.getListTransportVenteEntities() == null) {
            detail.setListTransportVenteEntities(new ArrayList<>());
        }

        detail.getListTransportVenteEntities().add(ligne);
        return ligne;
    }

    public LigneTransportEntity update(DetailOrdreTransportEntity detail, Long ligneId, LigneTransportUpdateDTO dto) {
        ligneValidator.validateUpdate(dto);

        LigneTransportEntity ligne = findRequired(
                detail.getListTransportVenteEntities(),
                l -> l.getId() != null && l.getId().equals(ligneId),
                "Ligne introuvable, id=" + ligneId + ", detailId=" + detail.getId());

        ligneMapper.updateEntityFromDTO(dto, ligne);
        ligne.setDetailOrdreTransport(detail);
        return ligne;
    }

    public void delete(DetailOrdreTransportEntity detail, Long ligneId) {
        removeRequired(
                detail.getListTransportVenteEntities(),
                l -> l.getId() != null && l.getId().equals(ligneId),
                "Ligne introuvable, id=" + ligneId + ", detailId=" + detail.getId());
    }
}
