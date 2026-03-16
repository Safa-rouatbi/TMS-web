package com.gpro.consulting.tiers.logistique.dto;

import lombok.Data;

import java.util.Calendar;
import java.util.List;

@Data
public class OrdreTransportReadDTO {

    private Long id;
private String reference;
private String refexterne;
private String refCommande;

private Calendar date;
private Calendar dateCreation;
private Calendar dateModification;

private Double montantHTaxe;
private Double montantTTC;
private Double montantTaxe;
private Double montantRemise;
private Double totalPourcentageRemise;

private String observations;
private String natureLivraison;

private Long partieIntId;
private String typeClient;
private String referenceClient;

private String etat;
private String transporteur;

private Double metrageTotal;
private Double prixAchatTotal;

private Long idDepot;
private Long idCamion;
private Long idRemorque;
private Long idChauffeur;

private String camion;
private String chauffeur;
private String remorque;

private Long modepaiementId;
private Long marcheId;
private Long reglementId;

private Long boutiqueId;
private Long devise;

private Double tauxConversion;
private Double montantConverti;

private String identifiantLivraison;
private String referenceBlManuel;
private String numCommandeClient;

private String numTelPassager;
private String emailPassager;
private String adressePassager;

private String userName;

private Long tourneeId;

    // private List<TaxeLivraisonReadDTO> listTaxeLivraison;
    // private List<DocumentLivraisonVenteReadDTO> documentLivraisonVente;

    private List<DetailOrdreTransportReadDTO> details;
}
