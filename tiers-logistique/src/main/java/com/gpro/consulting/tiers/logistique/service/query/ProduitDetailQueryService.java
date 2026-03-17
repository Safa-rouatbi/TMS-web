package com.gpro.consulting.tiers.logistique.service.query;

import com.gpro.consulting.tiers.logistique.dto.ProduitDetailReadDTO;

import java.util.List;

public interface ProduitDetailQueryService {

    ProduitDetailReadDTO findById(Long id);

    List<ProduitDetailReadDTO> findAll();

    List<ProduitDetailReadDTO> findByDetailOrdreTransportId(Long detailOrdreTransportId);
}
