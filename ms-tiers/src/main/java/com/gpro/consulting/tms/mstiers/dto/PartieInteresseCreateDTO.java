package com.gpro.consulting.tms.mstiers.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class PartieInteresseCreateDTO {
    private String reference;
    private String raisonSociale;
    private String abreviation;
    private String activite;
    private String observation;
    private String email;
    private String email2;
    private String telephone;
    private String telephoneMobile;
    private String adresse;
    private String matrFiscal;
    private String identifiantFiscal;
    private String autreIdentifiantFiscal;
    private String categorieTva;
    private String modePaiement;
    private String modalitePaiement;
    private String rib;
    private String iban;
    private String bic;
    private Boolean actif;

    private String typeClient; // CLIENT / FOURNISSEUR / TRANSPORTEUR / CHAUFFEUR / COMMISSIONNAIRE / BANQUE
    private Boolean prestataire; // pour transporteur
    private String nom;
    private String prenom;
    private String numeroCin;
    private String numeroLicence;
    private String numeroLicenceComissionnaire;
    private LocalDate dateEcheanceLicenceComissionnaire;
    private String grilleTarifaire;
    private Boolean priseRdvObligatoire;
}