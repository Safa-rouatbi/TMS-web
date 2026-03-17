package com.gpro.consulting.tiers.logistique.service.query;

import com.gpro.consulting.tiers.logistique.dto.DetailOrdreTransportReadDTO;

import java.util.List;

public interface DetailOrdreTransportQueryService {

    DetailOrdreTransportReadDTO findById(Long id);

    List<DetailOrdreTransportReadDTO> findAll();

    List<DetailOrdreTransportReadDTO> findByOrdreTransportId(Long ordreTransportId);
}
