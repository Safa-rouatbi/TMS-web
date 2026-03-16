package com.gpro.consulting.tiers.logistique.dto;

import lombok.Data;

import java.util.Calendar;
import java.util.List;

@Data
public class OrdreTransportCreateDTO {

    private String reference;
    private String refexterne;
    private String refCommande;
    private Calendar date;
    private String observations;
    private Long partieIntId;
    private String transporteur;
    private Long modepaiementId;
    private Long marcheId;
    private String natureLivraison;
    private Long idDepot;
    private Long idCamion;
    private Long idRemorque;
    private Long idChauffeur;
    private String camion;
    private String chauffeur;
    private String remorque;
    private String numTelPassager;
    private String emailPassager;
    private String adressePassager;
    private Long reglementId;
    private Long boutiqueId;
    private Long devise;
    private String numCommandeClient;
    private Long tourneeId;

    // private List<TaxeLivraisonCreateDTO> listTaxeLivraison;
    // private List<DocumentLivraisonVenteCreateDTO> documentLivraisonVente;

    private List<DetailOrdreTransportCreateDTO> details;
}
