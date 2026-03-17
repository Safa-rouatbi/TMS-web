package com.gpro.consulting.tiers.logistique.service.query.impl;

import com.gpro.consulting.tiers.logistique.dto.OrdreTransportReadDTO;
import com.gpro.consulting.tiers.logistique.mapper.OrdreTransportMapper;
import com.gpro.consulting.tiers.logistique.repository.OrdreTransportRepository;
import com.gpro.consulting.tiers.logistique.service.exception.NotFoundException;
import com.gpro.consulting.tiers.logistique.service.query.OrdreTransportQueryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrdreTransportQueryServiceImpl implements OrdreTransportQueryService {

    private final OrdreTransportRepository ordreTransportRepository;
    private final OrdreTransportMapper ordreTransportMapper;

    public OrdreTransportQueryServiceImpl(OrdreTransportRepository ordreTransportRepository,
                                          OrdreTransportMapper ordreTransportMapper) {
        this.ordreTransportRepository = ordreTransportRepository;
        this.ordreTransportMapper = ordreTransportMapper;
    }

    @Override
    public OrdreTransportReadDTO findById(Long id) {
        return ordreTransportRepository.findByIdWithDetails(id)
                .map(ordreTransportMapper::toReadDTO)
                .orElseGet(() -> ordreTransportRepository.findById(id)
                        .map(ordreTransportMapper::toReadDTO)
                        .orElseThrow(() -> new NotFoundException("Ordre transport introuvable, id=" + id)));
    }

    @Override
    public List<OrdreTransportReadDTO> findAll() {
        return ordreTransportMapper.toReadDTOList(ordreTransportRepository.findAll());
    }

    @Override
    public List<OrdreTransportReadDTO> findByReference(String reference) {
        return ordreTransportMapper.toReadDTOList(ordreTransportRepository.findByReference(reference));
    }

    @Override
    public List<OrdreTransportReadDTO> findByEtat(String etat) {
        return ordreTransportMapper.toReadDTOList(ordreTransportRepository.findByEtat(etat));
    }
}
