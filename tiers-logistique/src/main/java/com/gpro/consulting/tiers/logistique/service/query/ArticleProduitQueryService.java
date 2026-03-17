package com.gpro.consulting.tiers.logistique.service.query;

import com.gpro.consulting.tiers.logistique.dto.ArticleProduitReadDTO;

import java.util.List;

public interface ArticleProduitQueryService {

    ArticleProduitReadDTO findById(Long id);

    List<ArticleProduitReadDTO> findAll();

    List<ArticleProduitReadDTO> findByDetailOrdreTransportId(Long detailOrdreTransportId);
}
