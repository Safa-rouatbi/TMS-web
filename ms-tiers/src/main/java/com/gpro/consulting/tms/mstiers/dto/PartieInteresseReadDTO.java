package com.gpro.consulting.tms.mstiers.dto;

import lombok.Data;

@Data
public class PartieInteresseReadDTO {
    private Long id;
    private String reference;
    private String raisonSociale;
    private String abreviation;
    private String activite;
    private String email;
    private String telephone;
    private Boolean actif;
    private String typeClient;
    private Boolean prestataire;
    private String nom;
    private String prenom;
    private String numeroCin;
    private String numeroLicenceComissionnaire;
    
}