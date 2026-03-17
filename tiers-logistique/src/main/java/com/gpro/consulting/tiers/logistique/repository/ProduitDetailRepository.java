package com.gpro.consulting.tiers.logistique.repository;

import org.springframework.stereotype.Repository;
import com.gpro.consulting.tiers.logistique.persistance.elementBase.entity.ProduitDetailEntity;

import java.util.List;

@Repository
public interface ProduitDetailRepository extends ReadOnlyRepository<ProduitDetailEntity, Long> {

    List<ProduitDetailEntity> findByDetailOrdreTransportId(Long detailOrdreTransportId);

    List<ProduitDetailEntity> findByType(String type);
}