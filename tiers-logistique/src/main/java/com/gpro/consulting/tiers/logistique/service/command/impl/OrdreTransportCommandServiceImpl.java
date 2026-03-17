package com.gpro.consulting.tiers.logistique.service.command.impl;

import com.gpro.consulting.tiers.logistique.dto.*;
import com.gpro.consulting.tiers.logistique.mapper.*;
import com.gpro.consulting.tiers.logistique.persistance.elementBase.entity.ArticleProduitEntity;
import com.gpro.consulting.tiers.logistique.persistance.elementBase.entity.ProduitDetailEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.DetailOrdreTransportEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.LigneTransportEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.OrdreTransportEntity;
import com.gpro.consulting.tiers.logistique.repository.OrdreTransportRepository;
import com.gpro.consulting.tiers.logistique.service.command.OrdreTransportCommandService;
import com.gpro.consulting.tiers.logistique.service.command.handler.*;
import com.gpro.consulting.tiers.logistique.service.support.AggregateLoader;
import com.gpro.consulting.tiers.logistique.service.validation.AggregateConsistencyValidator;
import com.gpro.consulting.tiers.logistique.service.validation.OrdreTransportValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrdreTransportCommandServiceImpl implements OrdreTransportCommandService {

    private final OrdreTransportRepository ordreTransportRepository;
    private final OrdreTransportMapper ordreTransportMapper;
    private final DetailOrdreTransportMapper detailMapper;
    private final LigneTransportMapper ligneMapper;
    private final ProduitDetailMapper produitDetailMapper;
    private final ArticleProduitMapper articleProduitMapper;
    private final AggregateLoader aggregateLoader;
    private final OrdreTransportValidator ordreTransportValidator;
    private final AggregateConsistencyValidator consistencyValidator;
    private final DetailCommandHandler detailCommandHandler;
    private final LigneCommandHandler ligneCommandHandler;
    private final ProduitDetailCommandHandler produitDetailCommandHandler;
    private final ArticleProduitCommandHandler articleProduitCommandHandler;

    public OrdreTransportCommandServiceImpl(
            OrdreTransportRepository ordreTransportRepository,
            OrdreTransportMapper ordreTransportMapper,
            DetailOrdreTransportMapper detailMapper,
            LigneTransportMapper ligneMapper,
            ProduitDetailMapper produitDetailMapper,
            ArticleProduitMapper articleProduitMapper,
            AggregateLoader aggregateLoader,
            OrdreTransportValidator ordreTransportValidator,
            AggregateConsistencyValidator consistencyValidator,
            DetailCommandHandler detailCommandHandler,
            LigneCommandHandler ligneCommandHandler,
            ProduitDetailCommandHandler produitDetailCommandHandler,
            ArticleProduitCommandHandler articleProduitCommandHandler) {
        this.ordreTransportRepository = ordreTransportRepository;
        this.ordreTransportMapper = ordreTransportMapper;
        this.detailMapper = detailMapper;
        this.ligneMapper = ligneMapper;
        this.produitDetailMapper = produitDetailMapper;
        this.articleProduitMapper = articleProduitMapper;
        this.aggregateLoader = aggregateLoader;
        this.ordreTransportValidator = ordreTransportValidator;
        this.consistencyValidator = consistencyValidator;
        this.detailCommandHandler = detailCommandHandler;
        this.ligneCommandHandler = ligneCommandHandler;
        this.produitDetailCommandHandler = produitDetailCommandHandler;
        this.articleProduitCommandHandler = articleProduitCommandHandler;
    }

    @Override
    public OrdreTransportReadDTO createOrdre(OrdreTransportCreateDTO dto) {
        ordreTransportValidator.validateCreate(dto);

        OrdreTransportEntity ordre = ordreTransportMapper.toEntity(dto);
        consistencyValidator.validate(ordre);

        OrdreTransportEntity saved = ordreTransportRepository.save(ordre);
        return ordreTransportMapper.toReadDTO(saved);
    }

    @Override
    public OrdreTransportReadDTO updateOrdre(Long ordreId, OrdreTransportUpdateDTO dto) {
        ordreTransportValidator.validateUpdate(dto);

        OrdreTransportEntity ordre = loadOrdre(ordreId);
        ordreTransportMapper.updateEntityFromDTO(dto, ordre);

        OrdreTransportEntity saved = saveAggregate(ordre);
        return ordreTransportMapper.toReadDTO(saved);
    }

    @Override
    public void deleteOrdre(Long ordreId) {
        OrdreTransportEntity ordre = loadOrdre(ordreId);
        ordreTransportRepository.delete(ordre);
    }

    @Override
    public DetailOrdreTransportReadDTO createDetail(Long ordreId, DetailOrdreTransportCreateDTO dto) {
        OrdreTransportEntity ordre = loadOrdre(ordreId);
        DetailOrdreTransportEntity detail = detailCommandHandler.create(ordre, dto);

        saveAggregate(ordre);
        return detailMapper.toReadDTO(detail);
    }

    @Override
    public DetailOrdreTransportReadDTO updateDetail(Long ordreId, Long detailId, DetailOrdreTransportUpdateDTO dto) {
        OrdreTransportEntity ordre = loadOrdre(ordreId);
        DetailOrdreTransportEntity detail = detailCommandHandler.update(ordre, detailId, dto);

        saveAggregate(ordre);
        return detailMapper.toReadDTO(detail);
    }

    @Override
    public void deleteDetail(Long ordreId, Long detailId) {
        OrdreTransportEntity ordre = loadOrdre(ordreId);
        detailCommandHandler.delete(ordre, detailId);

        saveAggregate(ordre);
    }

    @Override
    public LigneTransportReadDTO createLigne(Long ordreId, Long detailId, LigneTransportCreateDTO dto) {
        OrdreTransportEntity ordre = loadOrdre(ordreId);
        DetailOrdreTransportEntity detail = loadDetail(ordre, detailId);
        LigneTransportEntity ligne = ligneCommandHandler.create(detail, dto);

        saveAggregate(ordre);
        return ligneMapper.toReadDTO(ligne);
    }

    @Override
    public LigneTransportReadDTO updateLigne(Long ordreId, Long detailId, Long ligneId, LigneTransportUpdateDTO dto) {
        OrdreTransportEntity ordre = loadOrdre(ordreId);
        DetailOrdreTransportEntity detail = loadDetail(ordre, detailId);
        LigneTransportEntity ligne = ligneCommandHandler.update(detail, ligneId, dto);

        saveAggregate(ordre);
        return ligneMapper.toReadDTO(ligne);
    }

    @Override
    public void deleteLigne(Long ordreId, Long detailId, Long ligneId) {
        OrdreTransportEntity ordre = loadOrdre(ordreId);
        DetailOrdreTransportEntity detail = loadDetail(ordre, detailId);
        ligneCommandHandler.delete(detail, ligneId);

        saveAggregate(ordre);
    }

    @Override
    public ProduitDetailReadDTO createProduitDetail(Long ordreId, Long detailId, ProduitDetailCreateDTO dto) {
        OrdreTransportEntity ordre = loadOrdre(ordreId);
        DetailOrdreTransportEntity detail = loadDetail(ordre, detailId);
        ProduitDetailEntity produitDetail = produitDetailCommandHandler.create(detail, dto);

        saveAggregate(ordre);
        return produitDetailMapper.toReadDTO(produitDetail);
    }

    @Override
    public ProduitDetailReadDTO updateProduitDetail(Long ordreId,
                                                    Long detailId,
                                                    Long produitDetailId,
                                                    ProduitDetailUpdateDTO dto) {
        OrdreTransportEntity ordre = loadOrdre(ordreId);
        DetailOrdreTransportEntity detail = loadDetail(ordre, detailId);
        ProduitDetailEntity produitDetail = produitDetailCommandHandler.update(detail, produitDetailId, dto);

        saveAggregate(ordre);
        return produitDetailMapper.toReadDTO(produitDetail);
    }

    @Override
    public void deleteProduitDetail(Long ordreId, Long detailId, Long produitDetailId) {
        OrdreTransportEntity ordre = loadOrdre(ordreId);
        DetailOrdreTransportEntity detail = loadDetail(ordre, detailId);
        produitDetailCommandHandler.delete(detail, produitDetailId);

        saveAggregate(ordre);
    }

    @Override
    public ArticleProduitReadDTO createArticleProduit(Long ordreId, Long detailId, ArticleProduitCreateDTO dto) {
        OrdreTransportEntity ordre = loadOrdre(ordreId);
        DetailOrdreTransportEntity detail = loadDetail(ordre, detailId);
        ArticleProduitEntity articleProduit = articleProduitCommandHandler.create(detail, dto);

        saveAggregate(ordre);
        return articleProduitMapper.toReadDTO(articleProduit);
    }

    @Override
    public ArticleProduitReadDTO updateArticleProduit(Long ordreId,
                                                      Long detailId,
                                                      Long articleProduitId,
                                                      ArticleProduitUpdateDTO dto) {
        OrdreTransportEntity ordre = loadOrdre(ordreId);
        DetailOrdreTransportEntity detail = loadDetail(ordre, detailId);
        ArticleProduitEntity articleProduit = articleProduitCommandHandler.update(detail, articleProduitId, dto);

        saveAggregate(ordre);
        return articleProduitMapper.toReadDTO(articleProduit);
    }

    @Override
    public void deleteArticleProduit(Long ordreId, Long detailId, Long articleProduitId) {
        OrdreTransportEntity ordre = loadOrdre(ordreId);
        DetailOrdreTransportEntity detail = loadDetail(ordre, detailId);
        articleProduitCommandHandler.delete(detail, articleProduitId);

        saveAggregate(ordre);
    }

    private OrdreTransportEntity loadOrdre(Long ordreId) {
        return aggregateLoader.loadOrdre(ordreId);
    }

    private DetailOrdreTransportEntity loadDetail(OrdreTransportEntity ordre, Long detailId) {
        return aggregateLoader.loadDetailFromOrdre(ordre, detailId);
    }

    private OrdreTransportEntity saveAggregate(OrdreTransportEntity ordre) {
        consistencyValidator.validate(ordre);
        return ordreTransportRepository.save(ordre);
    }
}
