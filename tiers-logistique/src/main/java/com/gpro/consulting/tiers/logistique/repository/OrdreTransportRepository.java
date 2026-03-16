package com.gpro.consulting.tiers.logistique.repository;

import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.OrdreTransportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdreTransportRepository extends JpaRepository<OrdreTransportEntity, Long> {

    List<OrdreTransportEntity> findByReference(String reference);

    List<OrdreTransportEntity> findByEtat(String etat);

    List<OrdreTransportEntity> findByTransporteur(String transporteur);

    List<OrdreTransportEntity> findByPartieIntId(Long partieIntId);

    @Query("SELECT o FROM OrdreTransportEntity o "
         + "LEFT JOIN FETCH o.listDetLivraisonVente "
         + "WHERE o.id = :id")
    Optional<OrdreTransportEntity> findByIdWithDetails(Long id);

        

    List<OrdreTransportEntity> findByBlSuppressionFalseOrBlSuppressionIsNull();
}

