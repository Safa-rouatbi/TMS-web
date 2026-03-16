package com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie;


import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = IConstanteCommerciale.TABLE_GC_LIVRAISONVENTE)
public class OrdreTransportEntity implements Serializable {

    private static final long serialVersionUID = 7019588587457251275L;

    @Id
    @SequenceGenerator(name = "LIVRAISONVENTE_ID_GENERATOR", sequenceName = IConstanteCommerciale.SEQUENCE_GC_CLV_SEQ, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LIVRAISONVENTE_ID_GENERATOR")
    private Long id;

    @Column(name = "REFERENCE")
    private String reference;

    @Column(name = "REFEXTERNE")
    private String refexterne;

    @Column(name = "REF_COMMANDE")
    private String refCommande;



    @Column(name = "DATE")
    private Calendar date;

    @Column(name = "MONTANTHTAXE")
    private Double montantHTaxe; //exclu

    @Column(name = "MONTANTTTC")
    private Double montantTTC;   //exclu

    @Column(name = "MONTANT_TAXE")
    private Double montantTaxe;  //exclu

    @Column(name = "MONTANT_REMISE")
    private Double montantRemise; //exc

    @Column(name = "OBSERVATIONS")
    private String observations;

    @Column(name = "INFO_SORTIE") // list des ref bonSorties | patterne:
    private String infoSortie;

    @Column(name = "PI_PARTIEINT_ID")
    private Long partieIntId;

    @Column(name = "METRAGE_TOTAL")
    private Double metrageTotal;

    @Column(name = "ETAT")
    private String etat;

    @Column(name = "TRANSPORTEUR")
    private String transporteur;

    @OneToMany(mappedBy = "ordreTransport", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DetailOrdreTransportEntity> listDetLivraisonVente;

    /*@OneToMany(mappedBy = "ordreTransport", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TaxeLivraisonEntity> listTaxeLivraison;*/

    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "ordreTransport", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DocumentLivraisonVenteEntity> documentLivraisonVente;*/

    @Column(name = "BL_SUPPRESSION")
    private Boolean blSuppression;

    @Column(name = "DATE_SUPPRESSION")
    private Calendar dateSuppression;

    @Column(name = "DATE_CREATION")
    private Calendar dateCreation;

    @Column(name = "DATE_MODIFICATION")
    private Calendar dateModification;

    @Column(name = "VERSION")
    private String version;



    // added on 18/02/2016, by Wahid Gazzah
    @Column(name = "MODIFIER")
    private Boolean modifier;

    // added on 19/02/2016, by Wahid Gazzah
    @Column(name = "GC_MODEPAIEMENT_ID")
    private Long modepaiementId;

    @Column(name = "GC_MARCHE_ID")
    private Long marcheId;

    // added on 05/10/2016, by Zeineb Medimagh
    @Column(name = "NATURE_LIVRAISON")
    private String natureLivraison;

    @Column(name = "TOTAL_POURCENTAGE_REMISE")
    private Double totalPourcentageRemise;

    // Added on 25/04/2018  By Ghazi Atroussi
    @Column(name = "STOCK")
    private Boolean stock;

    @Column(name = "ID_DEPOT")
    private Long idDepot;

    @Column(name = "ID_CAMION")
    private Long idCamion;

    @Column(name = "ID_REMORQUE")
    private Long idRemorque;

    @Column(name = "ID_CHAUFFEUR")
    private Long idChauffeur;

    @Column(name = "camion")
    private String camion;

    @Column(name = "chauffeur")
    private String chauffeur;

    @Column(name = "remorque")
    private String remorque;

    @Column(name = "DESCRIPTION")
    private Boolean description;

    @Column(name = "type_partie_Interessee")
    private Long typePartieInteressee;

    @Column(name = "declare")
    private Boolean declare;

    /** The GroupeClient. */
    @Column(name = "groupe_client_id")
    private Long groupeClientId;

    @Column(name = "num_tel_passager")
    private String numTelPassager;

    @Column(name = "email_passager")
    private String emailPassager;

    @Column(name = "adresse_passager")
    private String adressePassager;

    @Column(name = "GC_REGLEMENT_ID")
    private Long reglementId;

    @Column(name = "boutique_id")
    private Long boutiqueId;

    @Column(name = "devise")
    private Long devise;

    @Column(name = "taux_conversion")
    private Double tauxConversion;

    @Column(name = "montant_converti")
    private Double montantConverti;

    @Column(name = "REFERENCE_BL_MANUEL")
    private String referenceBlManuel;

    @Column(name = "identifiant_BL")
    private String identifiantLivraison;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "type_client")
    private String typeClient;

    @Column(name = "type_creation")
    private String typeCreation;

    @Column(name = "reference_client")
    private String referenceClient;

    @Column(name = "user_tel")
    private String userTel;

    @Column(name = "prix_achat_total")
    private Double prixAchatTotal;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournee_id")
    private TourneeEntity tourneeEntity;*/

    @Column(name = "num_commande_client")
    private String numCommandeClient;

    @Override
    public String toString() {
        return "OrdreTransportEntity [id=" + id + ", reference=" + reference
                + ", date=" + date + ", montantHTaxe=" + montantHTaxe
                + ", montantTTC=" + montantTTC + ", montantTaxe=" + montantTaxe
                + ", montantRemise=" + montantRemise + ", observations="
                + observations + ", infoSortie=" + infoSortie
                + ", partieIntId=" + partieIntId + ", metrageTotal="
                + metrageTotal + ", etat=" + etat + ", transporteur="
                + transporteur + ", listDetLivraisonVente="
                + listDetLivraisonVente + ", blSuppression=" + blSuppression
                + ", dateSuppression=" + dateSuppression + ", dateCreation="
                + dateCreation + ", dateModification=" + dateModification
                + ", version=" + version + ", modifier=" + modifier
                + ", modepaiementId=" + modepaiementId + ", marcheId="
                + marcheId + ", natureLivraison=" + natureLivraison
                + ", totalPourcentageRemise=" + totalPourcentageRemise + "]";
    }
}
