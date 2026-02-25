package com.gpro.consulting.tms.mstiers.dto;

import java.time.LocalDate;
import lombok.Data;


@Data
public class PartieInteresseUpdateDTO {

    // --- Informations générales ---
    private String raisonSociale;
    private String abreviation;
    private String activite;
    private String observation;
    private Boolean actif;

    // --- Contact ---
    private String email;
    private String email2;
    private String telephone;
    private String telephoneMobile;
    private String fax;

    // --- Adresses ---
    private String adresse;
    private String adresseFacturation;
    private String adresseLivraison;
    private String paysFacturation;
    private String villeFacturation;
    private String codePostalFacturation;
    private String rueFacturation;
    private String paysLivraison;
    private String villeLivraison;
    private String codePostalLivraison;
    private String rueLivraison;

    // --- Comptabilité / Paiement ---
    private String categorieTva;
    private String modePaiement;
    private String modalitePaiement;
    private String rib;
    private String iban;
    private String bic;

    // --- Interlocuteur ---
    private String interlocuteur;
    private String emailInterlocuteur;
    private String telInterlocuteur;

    // --- Champs spécifiques CHAUFFEUR ---
    private String nom;
    private String prenom;
    private String numeroCin;
    private String numeroLicence;

    // --- Champs spécifiques COMMISSIONNAIRE ---
    private String numeroLicenceComissionnaire;
    private LocalDate dateEcheanceLicenceComissionnaire;

    // --- Champs spécifiques TRANSPORTEUR ---
    private String grilleTarifaire;
    private Boolean priseRdvObligatoire;
    private Boolean prestataire;
}
