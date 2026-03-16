package com.gpro.consulting.tiers.logistique.repository;

import org.springframework.stereotype.Repository;
import com.gpro.consulting.tiers.logistique.persistance.elementBase.entity.ArticleProduitEntity;

import java.util.List;

@Repository
public interface ArticleProduitRepository extends ReadOnlyRepository<ArticleProduitEntity, Long> {
    
    
    List<ArticleProduitEntity> findByArticleId(Long articleId);
    
    
    List<ArticleProduitEntity> findByDetailOrdreTransportId(Long detailOrdreTransportId);
}