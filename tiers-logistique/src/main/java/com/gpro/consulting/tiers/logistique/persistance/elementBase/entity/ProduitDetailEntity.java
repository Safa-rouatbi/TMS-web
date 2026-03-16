package com.gpro.consulting.tiers.logistique.persistance.elementBase.entity;


import com.gpro.consulting.tiers.logistique.coordination.gc.IConstante;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.DetailOrdreTransportEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Calendar;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = IConstante.TABLE__PRODUIT_DETAIL)
public class ProduitDetailEntity implements Serializable {

    private static final long serialVersionUID = 4350985648971998529L;

    @Id
    @SequenceGenerator(name = "ARTICLE_PRODUIT_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_DETAIL_PRODUIT)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTICLE_PRODUIT_ID_GENERATOR")
    private Long id;

    @Column(name = "prix_revient")
    private Double prixRevient;

    @Column(name = "metre_lineaire")
    private Double metreLineaire;

    @Column(name = "nb_palette_total")
    private Double nbPaletteTotal;

    @Column(name = "rue_enlevement")
    private String rueEnlevement;

    @Column(name = "ville_enlevement")
    private String villeEnlevement;

    @Column(name = "code_postal_enlevement")
    private String codePostalEnlevement;

    @Column(name = "nom_contact_enlevement")
    private String nomContactEnlevement;

    @Column(name = "num_tel_contact_enlevement")
    private String numTelContactEnlevement;

    @Column(name = "date_enlevement")
    private Calendar dateEnlevement;

    @Column(name = "heure_enlevement")
    private String heureEnlevement;

    @Column(name = "rue_livraison")
    private String rueLivraison;

    @Column(name = "ville_livraison")
    private String villeLivraison;

    @Column(name = "code_postal_livraison")
    private String codePostalLivraison;

    @Column(name = "nom_contact_livraison")
    private String nomContactLivraison;

    @Column(name = "num_tel_contact_livraison")
    private String numTelContactLivraison;

    @Column(name = "date_livraison")
    private Calendar dateLivraison;

    @Column(name = "heure_livraison")
    private String heureLivraison;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eb_produit_id")
    private ProduitEntity produit;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "detail_livraison_id")
    private DetailOrdreTransportEntity detailOrdreTransport = new DetailOrdreTransportEntity();

    @Column(name = "type_ressource")
    private String typeRessource;

    @Column(name = "ressource")
    private String ressource;

    @Column(name = "ressource_id")
    private Long ressourceId;

    @Column(name = "type_vehicule")
    private String typeVehicule;

    @Column(name = "image_vehicule")
    private String imageVehicule;

    @Column(name = "pays_livraison")
    private String paysLivraison;

    @Column(name = "pays_enlevement")
    private String paysEnlevement;

    @Column(name = "poids_total")
    private Double poidsTotal;

    @Column(name = "type")
    private String type;

    @Column(name = "nom_client_chargement")
    private String nomClientChargment;

    @Column(name = "nom_client_livraison")
    private String nomClientLivraison;

    @Column(name = "reference_chargement")
    private String referenceChargment;

    @Column(name = "reference_livraison")
    private String referenceLivraison;

    @Column(name = "prix_achat")
    private Double prixAchat;

    @Column(name = "num_devis")
    private String numDevis;

    @Column(name = "ref_chargement")
    private String refChargement;

    @Column(name = "ref_livraison")
    private String refLivraison;

    @Column(name = "type_heure_chargement")
    private String typeHeureChargement;

    @Column(name = "type_heure_livraison")
    private String typeHeureLivraison;

    @Column(name = "instructions_livraison")
    private String instructionsLivraison;

    @Column(name = "instructions_enlevement")
    private String instructionsEnlevement;

    @Column(name = "famille_id")
    private Long familleId;

    @Column(name = "heure_enlevement_a")
    private String heureEnlevementA;

    @Column(name = "heure_livraison_a")
    private String heureLivraisonA;
}
