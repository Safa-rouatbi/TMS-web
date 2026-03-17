package com.gpro.consulting.tiers.logistique.service.command.handler;

import com.gpro.consulting.tiers.logistique.dto.ArticleProduitCreateDTO;
import com.gpro.consulting.tiers.logistique.dto.ArticleProduitUpdateDTO;
import com.gpro.consulting.tiers.logistique.mapper.ArticleProduitMapper;
import com.gpro.consulting.tiers.logistique.persistance.elementBase.entity.ArticleProduitEntity;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.DetailOrdreTransportEntity;
import com.gpro.consulting.tiers.logistique.service.validation.ArticleProduitValidator;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class ArticleProduitCommandHandler extends BaseChildCommandHandler {

    private final ArticleProduitMapper articleProduitMapper;
    private final ArticleProduitValidator articleProduitValidator;

    public ArticleProduitCommandHandler(ArticleProduitMapper articleProduitMapper,
                                        ArticleProduitValidator articleProduitValidator) {
        this.articleProduitMapper = articleProduitMapper;
        this.articleProduitValidator = articleProduitValidator;
    }

    public ArticleProduitEntity create(DetailOrdreTransportEntity detail, ArticleProduitCreateDTO dto) {
        articleProduitValidator.validateCreate(dto);

        ArticleProduitEntity articleProduit = articleProduitMapper.toEntity(dto);
        articleProduit.setDetailOrdreTransport(detail);

        if (detail.getArticleProduits() == null) {
            detail.setArticleProduits(new HashSet<>());
        }

        detail.getArticleProduits().add(articleProduit);
        return articleProduit;
    }

    public ArticleProduitEntity update(DetailOrdreTransportEntity detail,
                                       Long articleProduitId,
                                       ArticleProduitUpdateDTO dto) {
        articleProduitValidator.validateUpdate(dto);

        ArticleProduitEntity articleProduit = findRequired(
                detail.getArticleProduits(),
                a -> a.getId() != null && a.getId().equals(articleProduitId),
                "Article produit introuvable, id=" + articleProduitId + ", detailId=" + detail.getId());

        articleProduitMapper.updateEntityFromDTO(dto, articleProduit);
        articleProduit.setDetailOrdreTransport(detail);
        return articleProduit;
    }

    public void delete(DetailOrdreTransportEntity detail, Long articleProduitId) {
        removeRequired(
                detail.getArticleProduits(),
                a -> a.getId() != null && a.getId().equals(articleProduitId),
                "Article produit introuvable, id=" + articleProduitId + ", detailId=" + detail.getId());
    }
}
