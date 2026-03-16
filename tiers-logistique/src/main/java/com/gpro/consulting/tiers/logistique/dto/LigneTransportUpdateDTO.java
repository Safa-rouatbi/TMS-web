package com.gpro.consulting.tiers.logistique.dto;

import lombok.Data;

import java.util.Date;

@Data
public class LigneTransportUpdateDTO {

  private Long id; // nécessaire pour identifier la ligne à mettre à jour
private Date date;
private String envoi;
private String dpt;
private String lieu;
private String denominationClient;
private String pdBrut;
private Double metrage;
private Long nombreColis;
private Double quantiteUniteColisage;
private Double quantite;
private String unite;
private Double pu;
private Double montantSurcharge;
private String chauffeur;
private String numero;
private String societe;
private String adresse;
private String codePostal;
private String ville;
private String mail;
private String tel;
private String heure;
private String natureColis;
}
