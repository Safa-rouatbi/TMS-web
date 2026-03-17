package com.gpro.consulting.tiers.logistique.service.query.impl;

import com.gpro.consulting.tiers.logistique.dto.DetailOrdreTransportReadDTO;
import com.gpro.consulting.tiers.logistique.mapper.DetailOrdreTransportMapper;
import com.gpro.consulting.tiers.logistique.repository.DetailOrdreTransportRepository;
import com.gpro.consulting.tiers.logistique.service.exception.NotFoundException;
import com.gpro.consulting.tiers.logistique.service.query.DetailOrdreTransportQueryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DetailOrdreTransportQueryServiceImpl implements DetailOrdreTransportQueryService {

    private final DetailOrdreTransportRepository detailRepository;
    private final DetailOrdreTransportMapper detailMapper;

    public DetailOrdreTransportQueryServiceImpl(DetailOrdreTransportRepository detailRepository,
                                                DetailOrdreTransportMapper detailMapper) {
        this.detailRepository = detailRepository;
        this.detailMapper = detailMapper;
    }

    @Override
    public DetailOrdreTransportReadDTO findById(Long id) {
        return detailRepository.findById(id)
                .map(detailMapper::toReadDTO)
                .orElseThrow(() -> new NotFoundException("Detail introuvable, id=" + id));
    }

    @Override
    public List<DetailOrdreTransportReadDTO> findAll() {
        return detailMapper.toReadDTOList(detailRepository.findAll());
    }

    @Override
    public List<DetailOrdreTransportReadDTO> findByOrdreTransportId(Long ordreTransportId) {
        return detailMapper.toReadDTOList(detailRepository.findByOrdreTransportId(ordreTransportId));
    }
}
