package com.gpro.consulting.tiers.logistique.service.command.handler;

import com.gpro.consulting.tiers.logistique.dto.DetailOrdreTransportCreateDTO;
import com.gpro.consulting.tiers.logistique.dto.DetailOrdreTransportUpdateDTO;
import com.gpro.consulting.tiers.logistique.mapper.DetailOrdreTransportMapper;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.DetailOrdreTransportEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.OrdreTransportEntity;
import com.gpro.consulting.tiers.logistique.service.validation.DetailValidator;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DetailCommandHandler extends BaseChildCommandHandler {

    private final DetailOrdreTransportMapper detailMapper;
    private final DetailValidator detailValidator;

    public DetailCommandHandler(DetailOrdreTransportMapper detailMapper, DetailValidator detailValidator) {
        this.detailMapper = detailMapper;
        this.detailValidator = detailValidator;
    }

    public DetailOrdreTransportEntity create(OrdreTransportEntity ordre, DetailOrdreTransportCreateDTO dto) {
        detailValidator.validateCreate(dto);

        DetailOrdreTransportEntity detail = detailMapper.toEntity(dto);
        detail.setOrdreTransport(ordre);

        if (ordre.getListDetLivraisonVente() == null) {
            ordre.setListDetLivraisonVente(new HashSet<>());
        }

        ordre.getListDetLivraisonVente().add(detail);
        return detail;
    }

    public DetailOrdreTransportEntity update(OrdreTransportEntity ordre, Long detailId, DetailOrdreTransportUpdateDTO dto) {
        detailValidator.validateUpdate(dto);

        DetailOrdreTransportEntity detail = findRequired(
                ordre.getListDetLivraisonVente(),
                d -> d.getId() != null && d.getId().equals(detailId),
                "Detail introuvable, id=" + detailId + ", ordreId=" + ordre.getId());

        detailMapper.updateEntityFromDTO(dto, detail);
        detail.setOrdreTransport(ordre);
        return detail;
    }

    public void delete(OrdreTransportEntity ordre, Long detailId) {
        removeRequired(
                ordre.getListDetLivraisonVente(),
                d -> d.getId() != null && d.getId().equals(detailId),
                "Detail introuvable, id=" + detailId + ", ordreId=" + ordre.getId());
    }
}
