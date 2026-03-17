package com.gpro.consulting.tiers.logistique.service.query.impl;

import com.gpro.consulting.tiers.logistique.dto.ProduitDetailReadDTO;
import com.gpro.consulting.tiers.logistique.mapper.ProduitDetailMapper;
import com.gpro.consulting.tiers.logistique.repository.ProduitDetailRepository;
import com.gpro.consulting.tiers.logistique.service.exception.NotFoundException;
import com.gpro.consulting.tiers.logistique.service.query.ProduitDetailQueryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ProduitDetailQueryServiceImpl implements ProduitDetailQueryService {

    private final ProduitDetailRepository produitDetailRepository;
    private final ProduitDetailMapper produitDetailMapper;

    public ProduitDetailQueryServiceImpl(ProduitDetailRepository produitDetailRepository,
                                         ProduitDetailMapper produitDetailMapper) {
        this.produitDetailRepository = produitDetailRepository;
        this.produitDetailMapper = produitDetailMapper;
    }

    @Override
    public ProduitDetailReadDTO findById(Long id) {
        return produitDetailRepository.findById(id)
                .map(produitDetailMapper::toReadDTO)
                .orElseThrow(() -> new NotFoundException("Produit detail introuvable, id=" + id));
    }

    @Override
    public List<ProduitDetailReadDTO> findAll() {
        return produitDetailRepository.findAll().stream()
                .map(produitDetailMapper::toReadDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProduitDetailReadDTO> findByDetailOrdreTransportId(Long detailOrdreTransportId) {
        return produitDetailRepository.findByDetailOrdreTransportId(detailOrdreTransportId).stream()
                .map(produitDetailMapper::toReadDTO)
                .collect(Collectors.toList());
    }
}
