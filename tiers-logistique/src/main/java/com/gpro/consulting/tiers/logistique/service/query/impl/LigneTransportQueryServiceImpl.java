package com.gpro.consulting.tiers.logistique.service.query.impl;

import com.gpro.consulting.tiers.logistique.dto.LigneTransportReadDTO;
import com.gpro.consulting.tiers.logistique.mapper.LigneTransportMapper;
import com.gpro.consulting.tiers.logistique.repository.LigneTransportRepository;
import com.gpro.consulting.tiers.logistique.service.exception.NotFoundException;
import com.gpro.consulting.tiers.logistique.service.query.LigneTransportQueryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class LigneTransportQueryServiceImpl implements LigneTransportQueryService {

    private final LigneTransportRepository ligneRepository;
    private final LigneTransportMapper ligneMapper;

    public LigneTransportQueryServiceImpl(LigneTransportRepository ligneRepository,
                                          LigneTransportMapper ligneMapper) {
        this.ligneRepository = ligneRepository;
        this.ligneMapper = ligneMapper;
    }

    @Override
    public LigneTransportReadDTO findById(Long id) {
        return ligneRepository.findById(id)
                .map(ligneMapper::toReadDTO)
                .orElseThrow(() -> new NotFoundException("Ligne introuvable, id=" + id));
    }

    @Override
    public List<LigneTransportReadDTO> findAll() {
        return ligneRepository.findAll().stream()
                .map(ligneMapper::toReadDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LigneTransportReadDTO> findByDetailOrdreTransportId(Long detailOrdreTransportId) {
        return ligneRepository.findByDetailOrdreTransportId(detailOrdreTransportId).stream()
                .map(ligneMapper::toReadDTO)
                .collect(Collectors.toList());
    }
}
