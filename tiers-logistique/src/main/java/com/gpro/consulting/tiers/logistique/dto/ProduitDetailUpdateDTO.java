package com.gpro.consulting.tiers.logistique.dto;

import lombok.Data;

import java.util.Calendar;

@Data
public class ProduitDetailUpdateDTO {

private Long id;
private Double prixRevient;
private Double metreLineaire;
private Double nbPaletteTotal;

private String rueEnlevement;
private String villeEnlevement;
private String codePostalEnlevement;
private String nomContactEnlevement;
private String numTelContactEnlevement;
private Calendar dateEnlevement;
private String heureEnlevement;

private String rueLivraison;
private String villeLivraison;
private String codePostalLivraison;
private String nomContactLivraison;
private String numTelContactLivraison;
private Calendar dateLivraison;
private String heureLivraison;

private Long produitId;

private String typeRessource;
private String ressource;
private Long ressourceId;
private String typeVehicule;
private String imageVehicule;
private String paysLivraison;
private String paysEnlevement;
private Double poidsTotal;
private String type;

private String nomClientChargment;
private String nomClientLivraison;
private String referenceChargment;
private String referenceLivraison;
private Double prixAchat;
private String numDevis;
private String refChargement;
private String refLivraison;
private String typeHeureChargement;
private String typeHeureLivraison;
private String instructionsLivraison;
private String instructionsEnlevement;
private Long familleId;
private String heureEnlevementA;
private String heureLivraisonA;
}
