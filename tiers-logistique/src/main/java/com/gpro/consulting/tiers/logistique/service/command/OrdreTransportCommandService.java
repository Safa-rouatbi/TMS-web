package com.gpro.consulting.tiers.logistique.service.command;

import com.gpro.consulting.tiers.logistique.dto.*;

public interface OrdreTransportCommandService {

    OrdreTransportReadDTO createOrdre(OrdreTransportCreateDTO dto);

    OrdreTransportReadDTO updateOrdre(Long ordreId, OrdreTransportUpdateDTO dto);

    void deleteOrdre(Long ordreId);

    DetailOrdreTransportReadDTO createDetail(Long ordreId, DetailOrdreTransportCreateDTO dto);

    DetailOrdreTransportReadDTO updateDetail(Long ordreId, Long detailId, DetailOrdreTransportUpdateDTO dto);

    void deleteDetail(Long ordreId, Long detailId);

    LigneTransportReadDTO createLigne(Long ordreId, Long detailId, LigneTransportCreateDTO dto);

    LigneTransportReadDTO updateLigne(Long ordreId, Long detailId, Long ligneId, LigneTransportUpdateDTO dto);

    void deleteLigne(Long ordreId, Long detailId, Long ligneId);

    ProduitDetailReadDTO createProduitDetail(Long ordreId, Long detailId, ProduitDetailCreateDTO dto);

    ProduitDetailReadDTO updateProduitDetail(Long ordreId, Long detailId, Long produitDetailId, ProduitDetailUpdateDTO dto);

    void deleteProduitDetail(Long ordreId, Long detailId, Long produitDetailId);

    ArticleProduitReadDTO createArticleProduit(Long ordreId, Long detailId, ArticleProduitCreateDTO dto);

    ArticleProduitReadDTO updateArticleProduit(Long ordreId, Long detailId, Long articleProduitId, ArticleProduitUpdateDTO dto);

    void deleteArticleProduit(Long ordreId, Long detailId, Long articleProduitId);
}
