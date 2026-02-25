package com.gpro.consulting.tms.mstiers.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import com.gpro.consulting.tiers.logistique.coordination.IConstante;

import lombok.*;

@Entity
@Table(name = IConstante.TABLE_PARTIE_INTERESSEE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartieInteresseEntite implements Serializable {


    private static final long serialVersionUID = 9085241173499739361L;


    @Id
    @SequenceGenerator(name = "PARTIE_INTERESSE_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_PARTIE_INTERESSEE, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARTIE_INTERESSE_ID_GENERATOR")
    private Long id;


    @Column(name = "ref")
    private String reference;


    @Column(name = "raison_sociale")
    private String raisonSociale;


    @Column(name = "abreviation")
    private String abreviation;


    @Column(name = "activite")
    private String activite;


    @Column(name = "observation")
    private String observation;


    @Column(name = "devise")
    private String devise;


    @Column(name = "date_introduction")
    private LocalDateTime dateIntroduction;


    @Column(name = "matr_fiscal")
    private String matrFiscal;


    @Column(name = "reg_com")
    private String regimeCommercial;

    @Column(name = "code_douane")
    private String codeDouane;


    @Column(name = "adresse")
    private String adresse;


    @Column(name = "email")
    private String email;


    @Column(name = "telephone")
    private String telephone;


    @Column(name = "fax")
    private String fax;


    @Column(name = "actif")
    private Boolean actif;

    @Column(name = "pi_famillepi_id")
    private Long famillePartieInteressee;


    @Column(name = "pi_typepi_id")
    private Long typePartieInteressee;


    @Column(name = "pi_com_site_id")
    private Long sitePartieInteressee;


    @Column(name = "pi_categorie_id")
    private Long categoriePartieInteressee;


    @Column(name = "bl_suppression")
    private Boolean blSuppression;


    @Column(name = "date_suppression")
    private LocalDateTime dateSuppression;


    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @Column(name = "date_modification")
    private LocalDateTime dateModification;


    @Column(name = "pi_region_id")
    private Long regionId;


    @Column(name = "pi_groupe_client_id")
    private Long groupeClientId;


    @Column(name = "passager")
    private Boolean passager;

    @Column(name = "boutique_id")
    private Long boutiqueId;


    @Column(name = "telephone_mobile")
    private String telephoneMobile;


    @Column(name = "payement_terme")
    private String payementTerme;


    @Column(name = "pi_compte_comptable_id")
    private Long compteComptablePartieInteressee;


    @Column(name = "banque_id")
    private Long banqueId;


    @Column(name = "code_bancaire")
    private String codeBancaire;


    @Column(name = "email2")
    private String email2;

    @Column(name = "code_produit")
    private String codeProduit;

    @Column(name = "nature")
    private String nature;

    @Column(name = "devise_id")
    private Long deviseId;

    @Column(name = "username")
    private String user;

    @Column(name = "identifiant_fiscal")
    private String identifiantFiscal;

    @Column(name = "categorie_tva")
    private String categorieTva;

    @Column(name = "adresse_facturation")
    private String adresseFacturation;

    @Column(name = "adresse_livraison")
    private String adresseLivraison;

    @Column(name = "numero_cin")
    private String numeroCin;

    @Column(name = "passeport")
    private String passeport;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "numero_licence")
    private String numeroLicence;

    @Column(name = "interlocuteur")
    private String interlocuteur;

    @Column(name = "email_interlocuteur")
    private String emailInterlocuteur;

    @Column(name = "tel_interlocuteur")
    private String telInterlocuteur;

    @Column(name = "date_echeance")
    private LocalDateTime dateEcheance;

    @Column(name = "code_interne_client")
    private String codeInterneClient;

    @Column(name = "type_client")
    private String typeClient;

    @Column(name = "pays_facturation")
    private String paysFacturation;

    @Column(name = "ville_facturation")
    private String villeFacturation;

    @Column(name = "code_postal_facturation")
    private String codePostalFacturation;

    @Column(name = "rue_facturation")
    private String rueFacturation;

    @Column(name = "pays_livraison")
    private String paysLivraison;

    @Column(name = "ville_livraison")
    private String villeLivraison;

    @Column(name = "code_postal_livraison")
    private String codePostalLivraison;

    @Column(name = "rue_livraison")
    private String rueLivraison;

    @Column(name = "pi_famillepi_designation")
    private String famillePartieInteresseeDesignation;

    @Column(name = "numero_licence_comissionnaire")
    private String numeroLicenceComissionnaire;

    @Column(name = "date_echeance_licenece_comissionnaire")
    private LocalDate dateEcheanceLicenceComissionnaire;

    @Column(name = "prestataire")
    private Boolean prestataire;

    @Column(name = "competences_string")
    private String competencesString;

    @Column(name = "sans_documents")
    private Boolean sansDocuments;

    @Column(name = "document_date_depasse")
    private Boolean documentDateDepasse;

    @Column(name = "adresse_livraison_different")
    private Boolean adresseLivraisonDifferent;

    @Column(name = "rib")
    private String rib;

    @Column(name = "compte_general")
    private String compteGeneral;

    @Column(name = "mode_paiement")
    private String modePaiement;

    @Column(name = "modalite_paiement")
    private String modalitePaiement;

    @Column(name = "date_echeance_commissionnaire")
    private LocalDateTime dateEcheanceCommissionnaire;

    @Column(name = "piece_identite_chauffeur_obligatoire")
    private Boolean pieceIdentiteChauffeurObligatoire;

    @Column(name = "matricule_vehicule_obligatoire")
    private Boolean matriculeVehiculeObligatoire;

    @Column(name = "prise_rdv_obligatoire")
    private Boolean priseRdvObligatoire;

    @Column(name = "iban")
    private String iban;

    @Column(name = "bic")
    private String bic;

    @Column(name = "grille_tarifaire")
    private String grilleTarifaire;

    @Column(name = "nom_pays_livraison")
    private String nomPaysLivraison;

    @Column(name = "jour_debut")
    private String jourDebut;

    @Column(name = "jour_fin")
    private String jourFin;

    @Column(name = "heure_debut")
    private String heureDebut;

    @Column(name = "heure_fin")
    private String heureFin;

    @Column(name = "autre_identifiant_fiscal")
    private String autreIdentifiantFiscal;


    public PartieInteresseEntite(Long id, String abreviation, Long famillePartieInteressee) {
        this.id = id;
        this.abreviation = abreviation;
        this.famillePartieInteressee = famillePartieInteressee;
    }
}