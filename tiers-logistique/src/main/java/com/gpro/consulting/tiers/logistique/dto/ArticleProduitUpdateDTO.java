package com.gpro.consulting.tiers.logistique.dto;

import lombok.Data;

@Data
public class ArticleProduitUpdateDTO {

private Long id;
private Long articleId;
private Double qte;
private String produitSemiFini;
private String referenceArticle;
private Long impressionProduitId;
private String grammage;
private Long produitId;
private String dimension;
private Long sousFamilleArticleId;
private String infoMatiere;

private String typePalette;
private Double longeurPalette;
private Double largeurPalette;
private Double hauteurPalette;
private Double quantitePalette;
private Double volumePaletteUnitaire;
private Double poidsPaletteUnitaire;
private Double volumePaletteTotal;
private Double poidsPaletteTotal;

private Double longeurColis;
private Double largeurColis;
private Double hauteurColis;
private Double quantiteColis;
private Double volumeColisUnitaire;
private Double poidsColisUnitaire;
private Double volumeColisTotal;
private Double poidsColisTotal;

private String type;
private Boolean gerbable;
private Double capacite;
private Double quantite;
private String contenu;
private Boolean reutilisable;
private String choixPoidsPaletteUnitaireOuTotal;
private String echange;
private String poidsMode;
private String statutEchangePalette;
private Double nbPaletteTotal;
private Double metreLineaire;
}
