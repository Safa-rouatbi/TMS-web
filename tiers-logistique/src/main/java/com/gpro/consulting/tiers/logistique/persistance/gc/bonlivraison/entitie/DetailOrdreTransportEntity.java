package com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie;

import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;
import com.gpro.consulting.tiers.logistique.persistance.elementBase.entity.ArticleProduitEntity;
import com.gpro.consulting.tiers.logistique.persistance.elementBase.entity.ProduitDetailEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


    @Entity
    @Table(name = IConstanteCommerciale.TABLE_GC_DETLIVRAISONVENTE)
    public class DetailOrdreTransportEntity implements Serializable {

        private static final long serialVersionUID = 569048653919573589L;

        @Id
        @SequenceGenerator(name = "DETLIVRAISONVENTE_ID_GENERATOR", sequenceName = IConstanteCommerciale.SEQUENCE_GC_CDLV_SEQ, allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DETLIVRAISONVENTE_ID_GENERATOR")
        private Long id;


    @Column(name = "QUANTITE")
    private Double quantite;

    @Column(name = "UNITE")
    private String unite;

    @Column(name = "NOMBRE_COLIS")
    private Long nombreColis;

    @Column(name = "EB_PRODUIT_ID")
    private Long produitId;

    @Column(name = "REMISE")
    private Double remise;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GC_LIVVENTE_ID")
    private OrdreTransportEntity ordreTransport;


    @Column(name = "CHOIX")
    private String choix;


    @Column(name="GL_TRAITEMENTFACON_ID")
    private Long traitementFaconId;


    @Column(name="PUHT")
    private Double prixUnitaireHT;

    @Column(name="PTHT")
    private Double prixTotalHT;


    @Column(name="FICHE_ID")
    private Long ficheId;


    @Column(name="unite_supplementaire")
    private String  uniteSupplementaire;

    @Column(name="quantite_conversion")
    private Double  quantiteConversion;

    @Column(name="conversion")
    private Boolean conversion;

    @Column(name="puht_conversion")
    private Double prixUnitaireHTConversion;
    @Column(name="taux_tva")
    private Double tauxTva;

    @Column(name="taxe_id")
    private Long taxeId;


    @Column(name="prix_ttc")
    private Double prixTTC;

    @Column(name = "serialisable")
    private boolean serialisable;

    @Column(name="numero_series")
    private String numeroSeries;


    @Column(name="description")
    private String description;


    @Column(name="numero_of")
    private String numeroOF;



    @Column(name="indexation_carburant")
    private String indexationCarburant;

    @Column(name="montant_tva")
    private Double montantTva;
    @Column(name="montant_ttc")
    private Double montantTtc;


    @Column(name="marge")
    private Double marge;



    @Column(name="consigne")
    private String consigne;
    @Column(name="montant_indexation_carburant")
    private Double montantIndexationCarburant;


    
     @OneToMany(mappedBy = "detailOrdreTransport",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
     private List<LigneTransportEntity> listTransportVenteEntities = new ArrayList<LigneTransportEntity>();


    
     @OneToMany(fetch = FetchType.LAZY, mappedBy = "detailOrdreTransport", cascade = CascadeType.ALL, orphanRemoval = true)
     private Set<ProduitDetailEntity> produitDetailEntities ;
    @Column(name="unite_vente")
    private Long uniteVente;

    @Column(name="type_ressource")
    private String typeRessource;

    @Column(name="ressource_id")
    private Long ressourceId;

    @Column(name="ressource_designation")
    private String ressourceDesignation;

    @Column(name = "reference")
    private String reference;

    @Column(name = "designation")
    private String designation;


    @Column(name = "pi_pi_id")
    private Long partieIntersseId;
    @Column(name = "uid_image")
    private String uidImage;


    @Column(name = "REFERENCE_INTERNE")
    private String referenceInterne;


    @Column(name = "retour")
    private Boolean retour;


    @Column(name = "dimension")
    private String dimension;

 
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "detailOrdreTransport", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ArticleProduitEntity> articleProduits;


    @Column(name = "type_destinaire")
    private String typeDestinaire;

    @Column(name = "mode_livraison")
    private String modeLivraison;

    @Column(name = "type_livraison")
    private String typeLivraison;

    @Column(name = "de_livraison")
    private String deLivraison;

    @Column(name = "periodicite")
    private String periodicite;

    @Column(name = "frequence")
    private String frequence;

    @Column(name = "contrainte_livraison")
    private String contrainteLivraison;

    @Column(name = "passage_a_quai")
    private String passageAquai;
    @Column(name = "temps_chargement_estime")
    private String tempsChargementEstime;

    @Column(name = "temps_livraison_estime_par_point")
    private String tempsLivraisonEstimeParPoint;

    @Column(name = "temps_gestion_retours_importants")
    private String tempsGestionRetoursImportants;

    @Column(name = "zone_livraison")
    private String zoneLivraison;

    @Column(name = "km_zone_urbaine")
    private String kmZoneUrbaine;

    @Column(name = "creneau_livraison_de")
    private String creneauLivraisonDe;

    @Column(name = "nombre_moyen_point_livraison")
    private String nombreMoyenPointsLivraison;

    @Column(name = "creneau_livraison_a")
    private String creneauLivraisonA;

    @Column(name = "nombre_rippeurs")
    private Long nombreRippeurs;
    @Column(name = "type_carburant")
    private String typeCarburant;

    @Column(name = "type_colis")
    private String typeColis;

    @Column(name = "type_palette")
    private String typePalette;

    @Column(name = "longeur_palette")
    private Double longeurPalette;

    @Column(name = "largeur_palette")
    private Double largeurPalette;

    @Column(name = "hauteur_palette")
    private Double hauteurPalette;

    @Column(name = "qte_palette")
    private Double quantitePalette;

    @Column(name = "volume_palette_unitaire")
    private Double volumePaletteUnitaire;

    @Column(name = "poids_palette_unitaire")
    private Double poidsPaletteUnitaire;
    @Column(name = "volume_palette_total")
    private Double volumePaletteTotal;

    @Column(name = "poids_palette_total")
    private Double poidsPaletteTotal;

    @Column(name = "longeur_colis")
    private Double longeurColis;

    @Column(name = "largeur_colis")
    private Double largeurColis;

    @Column(name = "hauteur_colis")
    private Double hauteurColis;

    @Column(name = "qte_colis")
    private Double quantiteColis;

    @Column(name = "volume_colis_unitaire")
    private Double volumeColisUnitaire;

    @Column(name = "poids_colis_unitaire")
    private Double poidsColisUnitaire;

    @Column(name = "volume_colis_total")
    private Double volumeColisTotal;

    @Column(name = "poids_colis_total")
    private Double poidsColisTotal;

    @Column(name = "numero_chargement")
    private String numeroChargement;

    @Column(name = "rue_chargement")
    private String rueChargement;

    @Column(name = "code_postal_chargement")
    private String codePostalChargement;

    @Column(name = "ville_chargement")
    private String villeChargement;

    @Column(name = "nom_contact_chargement")
    private String nomContactChargement;

    @Column(name = "num_telephone_chargement")
    private String numTelephoneChargement;

    @Column(name = "instructions")
    private String instructions;

    @Column(name = "retour_point_enlevement")
    private Boolean retourPointEnlevement;

    @Column(name = "date_enlevement")
    private Calendar dateEnlevement;

    @Column(name = "heure_enlevement")
    private String heureEnlevement;

    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "detailOrdreTransport", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AdresseLivraisonEntity> adresseLivraisonEntities;*/


    @Column(name = "type")
    private String type;


    @Column(name = "livraison_jour_ferie")
    private Boolean livraisonJourFerie;

    @Column(name = "besoin_manutentionnaire")
    private Long besoinManutentionnaire;



    @Column(name = "gestion_retour")
    private Boolean gestionRetour;


    @Column(name = "temps_gestion_retours_estime")
    private Long tempsGestionRetoursEstime;

    @Column(name = "type_colisage")
    private String typeColisage;

    @Column(name = "exigence_vehicule")
    private Boolean exigenceVehicule;

    @Column(name = "charge_utile_vl")
    private Double chargeUtileVL;
    @Column(name = "charge_utile_pl")
    private Double chargeUtilePL;
    @Column(name = "charge_utile_spl")
    private Double chargeUtileSPL;
    @Column(name = "hauteur_max_vehicule")
    private Double hauteurMaxVehicule;
    @Column(name = "type_chargement")
    private String typeChargement;

    @Column(name = "specificite_pl")
    private String specificitePl;
    @Column(name = "specificite_vl")
    private String specificiteVl;
    @Column(name = "specificite_spl")
    private String specificiteSpl;
    @Column(name = "type_vehicule")
    private String typeVehicule;

    @Column(name = "prise_rdv")
    private String priseRdv;

    @Column(name = "exigence_rdv")
    private String exigenceRdv;


    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "presentation_oblig_cin")
    private Boolean presentationObligCin;


    @Column(name = "ouverture_porte")
    private String ouverturePorte;


    @Column(name="prix_achat")
    private Double prixAchat;

    @Column(name="tournee_id")
    private Long tourneeId;
}