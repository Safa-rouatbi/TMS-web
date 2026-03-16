package com.gpro.consulting.tiers.logistique.repository;

import org.springframework.stereotype.Repository;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.LigneTransportEntity;

import java.util.List;

@Repository
public interface LigneTransportRepository extends ReadOnlyRepository<LigneTransportEntity, Long> {

    List<LigneTransportEntity> findByDetailOrdreTransportId(Long detailOrdreTransportId);

    List<LigneTransportEntity> findByNumero(String numero);
}