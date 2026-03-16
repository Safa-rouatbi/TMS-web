package com.gpro.consulting.tiers.logistique.dto;

import lombok.Data;

import java.util.Calendar;

@Data
public class ProduitDetailReadDTO {

       // Identifiants et quantités principales
    private Long id;
    
    private Double prixRevient;
    private Double poidsTotal;
    private Double metreLineaire;
    private Double nbPaletteTotal;
    private Double prixAchat;

    // Informations sur l’enlèvement
    private String rueEnlevement;
    private String villeEnlevement;
    private String codePostalEnlevement;
    private String nomContactEnlevement;
    private String numTelContactEnlevement;
    private Calendar dateEnlevement;
    private String heureEnlevement;
    private String heureEnlevementA;
    private String paysEnlevement;

    // Informations sur la livraison
    private String rueLivraison;
    private String villeLivraison;
    private String codePostalLivraison;
    private String nomContactLivraison;
    private String numTelContactLivraison;
    private Calendar dateLivraison;
    private String heureLivraison;
    private String heureLivraisonA;
    private String paysLivraison;

    // Informations logistiques et transport
    private String typeRessource;
    private String ressource;
    private Long ressourceId;
    private String typeVehicule;
    private String imageVehicule;
    private String type;

    // Informations clients / références
    private String nomClientChargment;
    private String nomClientLivraison;
    private String referenceChargment;
    private String referenceLivraison;
    private String numDevis;
    private String refChargement;
    private String refLivraison;
    private String instructionsEnlevement;
    private String instructionsLivraison;
    private Long familleId;
}
