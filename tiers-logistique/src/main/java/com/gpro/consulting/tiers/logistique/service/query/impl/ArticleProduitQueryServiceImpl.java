package com.gpro.consulting.tiers.logistique.service.query.impl;

import com.gpro.consulting.tiers.logistique.dto.ArticleProduitReadDTO;
import com.gpro.consulting.tiers.logistique.mapper.ArticleProduitMapper;
import com.gpro.consulting.tiers.logistique.repository.ArticleProduitRepository;
import com.gpro.consulting.tiers.logistique.service.exception.NotFoundException;
import com.gpro.consulting.tiers.logistique.service.query.ArticleProduitQueryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ArticleProduitQueryServiceImpl implements ArticleProduitQueryService {

    private final ArticleProduitRepository articleProduitRepository;
    private final ArticleProduitMapper articleProduitMapper;

    public ArticleProduitQueryServiceImpl(ArticleProduitRepository articleProduitRepository,
                                          ArticleProduitMapper articleProduitMapper) {
        this.articleProduitRepository = articleProduitRepository;
        this.articleProduitMapper = articleProduitMapper;
    }

    @Override
    public ArticleProduitReadDTO findById(Long id) {
        return articleProduitRepository.findById(id)
                .map(articleProduitMapper::toReadDTO)
                .orElseThrow(() -> new NotFoundException("Article produit introuvable, id=" + id));
    }

    @Override
    public List<ArticleProduitReadDTO> findAll() {
        return articleProduitRepository.findAll().stream()
                .map(articleProduitMapper::toReadDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleProduitReadDTO> findByDetailOrdreTransportId(Long detailOrdreTransportId) {
        return articleProduitRepository.findByDetailOrdreTransportId(detailOrdreTransportId).stream()
                .map(articleProduitMapper::toReadDTO)
                .collect(Collectors.toList());
    }
}
