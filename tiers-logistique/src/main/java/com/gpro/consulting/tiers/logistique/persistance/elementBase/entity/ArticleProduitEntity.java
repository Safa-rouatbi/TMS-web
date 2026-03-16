package com.gpro.consulting.tiers.logistique.persistance.elementBase.entity;


import com.gpro.consulting.tiers.logistique.coordination.gc.IConstanteCommerciale;
import com.gpro.consulting.tiers.logistique.persistance.gc.bonlivraison.entitie.DetailOrdreTransportEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = IConstanteCommerciale.TABLE_ARTICLE_PRODUIT)
public class ArticleProduitEntity implements Serializable {

    private static final long serialVersionUID = 4350985648971998529L;

    @Id
    @SequenceGenerator(name = "ARTICLE_PRODUIT_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_ARTICLE_PRODUIT)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTICLE_PRODUIT_ID_GENERATOR")
    private Long id;

    @Column(name = "article_id")
    private Long articleId;

    @Column(name = "qte")
    private Double qte;

    @Column(name = "produit_semi_fini")
    private String produitSemiFini;

    @Column(name = "reference_article")
    private String referenceArticle;

    @Column(name = "impression_produit_id")
    private Long impressionProduitId;
    
    @Column(name = "grammage")
    private String grammage;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eb_produit_id")
    private ProduitEntity produit;*/
    
    @Column(name = "dimension")
    private String dimension;
    
    @Column(name = "sous_famille_article_id")
    private Long sousFamilleArticleId;
    
    @Column(name = "info_matiere")
    private String infoMatiere;

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

    @Column(name = "type")
    private String type;

    @Column(name = "gerbable")
    private Boolean gerbable;

    @Column(name = "capacite")
    private Double capacite;

    @Column(name = "quantite")
    private Double quantite;

    @Column(name = "contenu")
    private String contenu;

    @Column(name = "reutilisable")
    private Boolean reutilisable;
    
    @Column(name = "choix_poids_palette")
    private String choixPoidsPaletteUnitaireOuTotal;
    
    @Column(name = "echange")
    private String echange;

    @Column(name = "poids_mode")
    private String poidsMode;
    
    @Column(name = "statut_echange_palette")
    private String statutEchangePalette;
    
    @Column(name = "nb_palette_total")
    private Double nbPaletteTotal;
    
    @Column(name = "metre_lineaire")
    private Double metreLineaire;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "det_livraion_id")
    private DetailOrdreTransportEntity detailOrdreTransport;
}
