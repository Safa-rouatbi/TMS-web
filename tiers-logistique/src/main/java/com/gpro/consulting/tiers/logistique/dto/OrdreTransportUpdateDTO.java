package com.gpro.consulting.tiers.logistique.dto;

import lombok.Data;

import java.util.Calendar;
import java.util.List;

@Data
public class OrdreTransportUpdateDTO {

   private String reference;
private String refexterne;
private String refCommande;

private Calendar date;

private String observations;
private String natureLivraison;

private Long partieIntId;

private String etat;
private String transporteur;

private Long modepaiementId;
private Long marcheId;
private Long reglementId;

private Long idDepot;
private Long idCamion;
private Long idRemorque;
private Long idChauffeur;

private String camion;
private String chauffeur;
private String remorque;

private Long boutiqueId;
private Long devise;

private Double tauxConversion;

private String referenceBlManuel;
private String numCommandeClient;

private String numTelPassager;
private String emailPassager;
private String adressePassager;

private String userName;

private Long tourneeId;

    // private List<TaxeLivraisonUpdateDTO> listTaxeLivraison;
    // private List<DocumentLivraisonVenteUpdateDTO> documentLivraisonVente;

    private List<DetailOrdreTransportUpdateDTO> details;
}
