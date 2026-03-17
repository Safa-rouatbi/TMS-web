package com.gpro.consulting.tiers.logistique.service.query;

import com.gpro.consulting.tiers.logistique.dto.LigneTransportReadDTO;

import java.util.List;

public interface LigneTransportQueryService {

    LigneTransportReadDTO findById(Long id);

    List<LigneTransportReadDTO> findAll();

    List<LigneTransportReadDTO> findByDetailOrdreTransportId(Long detailOrdreTransportId);
}
