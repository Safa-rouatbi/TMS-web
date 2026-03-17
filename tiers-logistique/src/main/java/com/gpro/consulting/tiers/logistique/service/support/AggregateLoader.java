package com.gpro.consulting.tiers.logistique.service.support;

import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.DetailOrdreTransportEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.OrdreTransportEntity;
import com.gpro.consulting.tiers.logistique.repository.OrdreTransportRepository;
import com.gpro.consulting.tiers.logistique.service.exception.NotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AggregateLoader {

    private final OrdreTransportRepository ordreTransportRepository;

    public AggregateLoader(OrdreTransportRepository ordreTransportRepository) {
        this.ordreTransportRepository = ordreTransportRepository;
    }

    public OrdreTransportEntity loadOrdre(Long ordreId) {
        return ordreTransportRepository.findByIdWithDetails(ordreId)
                .or(() -> ordreTransportRepository.findById(ordreId))
                .orElseThrow(() -> new NotFoundException("Ordre transport introuvable, id=" + ordreId));
    }

    public DetailOrdreTransportEntity loadDetailFromOrdre(OrdreTransportEntity ordre, Long detailId) {
        if (ordre.getListDetLivraisonVente() == null) {
            throw new NotFoundException("Aucun detail trouve pour ordre id=" + ordre.getId());
        }

        return ordre.getListDetLivraisonVente().stream()
                .filter(d -> d.getId() != null && d.getId().equals(detailId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(
                        "Detail introuvable, id=" + detailId + ", ordreId=" + ordre.getId()));
    }
}
