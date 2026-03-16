package com.gpro.consulting.tiers.logistique.dto;

import lombok.Data;

import java.util.Calendar;
import java.util.List;

@Data
public class DetailOrdreTransportReadDTO {

  private Long id;
private Double quantite;
private String unite;
private Long nombreColis;
private Long produitId;
private Double remise;
private String choix;
private Long traitementFaconId;
private Double prixUnitaireHT;
private Double prixTotalHT;
private Long ficheId;
private String uniteSupplementaire;
private Double quantiteConversion;
private Boolean conversion;
private Double prixUnitaireHTConversion;
private Double tauxTva;
private Long taxeId;
private Double prixTTC;
private boolean serialisable;
private String numeroSeries;
private String description;
private String numeroOF;
private String indexationCarburant;
private Double montantTva;
private Double montantTtc;
private Double marge;
private String consigne;
private Double montantIndexationCarburant;

private Long uniteVente;
private String typeRessource;
private Long ressourceId;
private String ressourceDesignation;
private String reference;
private String designation;
private Long partieIntersseId;
private String uidImage;
private String referenceInterne;
private Boolean retour;
private String dimension;

private String typeDestinaire;
private String modeLivraison;
private String typeLivraison;
private String deLivraison;
private String periodicite;
private String frequence;
private String contrainteLivraison;
private String passageAquai;
private String tempsChargementEstime;
private String tempsLivraisonEstimeParPoint;
private String tempsGestionRetoursImportants;
private String zoneLivraison;
private String kmZoneUrbaine;
private String creneauLivraisonDe;
private String nombreMoyenPointsLivraison;
private String creneauLivraisonA;
private Long nombreRippeurs;
private String typeCarburant;

private String typeColis;
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

private String numeroChargement;
private String rueChargement;
private String codePostalChargement;
private String villeChargement;
private String nomContactChargement;
private String numTelephoneChargement;
private String instructions;
private Boolean retourPointEnlevement;
private Calendar dateEnlevement;
private String heureEnlevement;

private String type;
private Boolean livraisonJourFerie;
private Long besoinManutentionnaire;
private Boolean gestionRetour;
private Long tempsGestionRetoursEstime;
private String typeColisage;
private Boolean exigenceVehicule;
private Double chargeUtileVL;
private Double chargeUtilePL;
private Double chargeUtileSPL;
private Double hauteurMaxVehicule;
private String typeChargement;
private String specificitePl;
private String specificiteVl;
private String specificiteSpl;
private String typeVehicule;
private String priseRdv;
private String exigenceRdv;

private String userName;
private String userEmail;
private Boolean presentationObligCin;
private String ouverturePorte;
private Double prixAchat;
private Long tourneeId;

    private List<LigneTransportReadDTO> lignesTransport;
    private List<ArticleProduitReadDTO> articleProduits;
    private List<ProduitDetailReadDTO> produitDetails;
    // private List<AdresseLivraisonReadDTO> adresseLivraisonEntities;
}
