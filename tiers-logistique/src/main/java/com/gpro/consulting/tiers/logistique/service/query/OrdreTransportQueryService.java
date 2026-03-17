package com.gpro.consulting.tiers.logistique.service.query;

import com.gpro.consulting.tiers.logistique.dto.OrdreTransportReadDTO;

import java.util.List;

public interface OrdreTransportQueryService {

    OrdreTransportReadDTO findById(Long id);

    List<OrdreTransportReadDTO> findAll();

    List<OrdreTransportReadDTO> findByReference(String reference);

    List<OrdreTransportReadDTO> findByEtat(String etat);
}
