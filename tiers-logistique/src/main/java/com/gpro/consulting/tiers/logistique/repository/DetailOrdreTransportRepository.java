package com.gpro.consulting.tiers.logistique.repository;

import org.springframework.stereotype.Repository;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.DetailOrdreTransportEntity;

import java.util.List;

@Repository
public interface DetailOrdreTransportRepository extends ReadOnlyRepository<DetailOrdreTransportEntity, Long> {
    
    
    List<DetailOrdreTransportEntity> findByOrdreTransportId(Long ordreTransportId);
    
    
    List<DetailOrdreTransportEntity> findByProduitId(Long produitId);
}