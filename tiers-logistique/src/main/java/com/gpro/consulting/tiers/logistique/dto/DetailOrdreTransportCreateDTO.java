package com.gpro.consulting.tiers.logistique.dto;

import lombok.Data;

import java.util.Calendar;
import java.util.List;

@Data
public class DetailOrdreTransportCreateDTO {

   private Double quantite;
private String unite;
private Long nombreColis;
private Long produitId;
private Double remise;
private String choix;
private Long traitementFaconId;
private Double prixUnitaireHT;

private Long ficheId; // si le front peut le renseigner
private String uniteSupplementaire;
private Boolean serialisable;
private String numeroSeries;
private String description;
private String numeroOF;

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
private String typeVehicule;
private String priseRdv;
private String exigenceRdv;

private String userName;
private String userEmail;

private Double prixAchat;
private Long tourneeId;

    private List<LigneTransportCreateDTO> lignesTransport;
    private List<ArticleProduitCreateDTO> articleProduits;
    private List<ProduitDetailCreateDTO> produitDetails;
    // private List<AdresseLivraisonCreateDTO> adresseLivraisonEntities;
}
