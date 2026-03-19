/**
 * Modèles et interfaces pour la gestion des Ordres de Transport
 */

export interface ArticleProduitCreateDTO {
  articleId?: number;
  qte?: number;
  produitSemiFini?: string;
  referenceArticle?: string;
  impressionProduitId?: number;
  grammage?: string;
  dimension?: string;
  sousFamilleArticleId?: number;
  infoMatiere?: string;
  typePalette?: string;
  longeurPalette?: number;
  largeurPalette?: number;
  hauteurPalette?: number;
  quantitePalette?: number;
  volumePaletteUnitaire?: number;
  poidsPaletteUnitaire?: number;
  volumePaletteTotal?: number;
  poidsPaletteTotal?: number;
  longeurColis?: number;
  largeurColis?: number;
  hauteurColis?: number;
  quantiteColis?: number;
  volumeColisUnitaire?: number;
  poidsColisUnitaire?: number;
  volumeColisTotal?: number;
  poidsColisTotal?: number;
  type?: string;
  gerbable?: boolean;
  capacite?: number;
  quantite?: number;
  contenu?: string;
  reutilisable?: boolean;
  choixPoidsPaletteUnitaireOuTotal?: string;
  echange?: string;
  poidsMode?: string;
  statutEchangePalette?: string;
  nbPaletteTotal?: number;
  metreLineaire?: number;
}

export interface ArticleProduitUpdateDTO extends ArticleProduitCreateDTO {
  id?: number;
}

export interface ArticleProduitReadDTO extends ArticleProduitUpdateDTO {}

export interface ProduitDetailCreateDTO {
  prixRevient?: number;
  metreLineaire?: number;
  nbPaletteTotal?: number;
  rueEnlevement?: string;
  villeEnlevement?: string;
  codePostalEnlevement?: string;
  nomContactEnlevement?: string;
  numTelContactEnlevement?: string;
  dateEnlevement?: string;
  heureEnlevement?: string;
  rueLivraison?: string;
  villeLivraison?: string;
  codePostalLivraison?: string;
  nomContactLivraison?: string;
  numTelContactLivraison?: string;
  dateLivraison?: string;
  heureLivraison?: string;
  typeRessource?: string;
  ressource?: string;
  ressourceId?: number;
  typeVehicule?: string;
  imageVehicule?: string;
  paysLivraison?: string;
  paysEnlevement?: string;
  poidsTotal?: number;
  type?: string;
  nomClientChargment?: string;
  nomClientLivraison?: string;
  referenceChargment?: string;
  referenceLivraison?: string;
  prixAchat?: number;
  numDevis?: string;
  refChargement?: string;
  refLivraison?: string;
  typeHeureChargement?: string;
  typeHeureLivraison?: string;
  instructionsLivraison?: string;
  instructionsEnlevement?: string;
  familleId?: number;
  heureEnlevementA?: string;
  heureLivraisonA?: string;
}

export interface ProduitDetailUpdateDTO extends ProduitDetailCreateDTO {
  id?: number;
}

export interface ProduitDetailReadDTO extends ProduitDetailUpdateDTO {
  articleProduits?: ArticleProduitReadDTO[];
}

export interface LigneTransportCreateDTO {
  date?: string;
  envoi?: string;
  dpt?: string;
  lieu?: string;
  denominationClient?: string;
  pdBrut?: string;
  metrage?: number;
  nombreColis?: number;
  quantiteUniteColisage?: number;
  quantite?: number;
  unite?: string;
  pu?: number;
  montantSurcharge?: number;
  chauffeur?: string;
  numero?: string;
  societe?: string;
  adresse?: string;
  codePostal?: string;
  ville?: string;
  mail?: string;
  tel?: string;
  heure?: string;
  natureColis?: string;
}

export interface LigneTransportUpdateDTO extends LigneTransportCreateDTO {
  id?: number;
}

export interface LigneTransportReadDTO extends LigneTransportUpdateDTO {}

export interface DetailOrdreTransportCreateDTO {
  quantite?: number;
  unite?: string;
  nombreColis?: number;
  produitId?: number;
  remise?: number;
  choix?: string;
  traitementFaconId?: number;
  prixUnitaireHT?: number;
  ficheId?: number;
  uniteSupplementaire?: string;
  serialisable?: boolean;
  numeroSeries?: string;
  description?: string;
  numeroOF?: string;
  reference?: string;
  designation?: string;
  partieIntersseId?: number;
  uidImage?: string;
  referenceInterne?: string;
  retour?: boolean;
  dimension?: string;
  typeDestinaire?: string;
  modeLivraison?: string;
  typeLivraison?: string;
  deLivraison?: string;
  periodicite?: string;
  frequence?: string;
  contrainteLivraison?: string;
  passageAquai?: string;
  tempsChargementEstime?: string;
  tempsLivraisonEstimeParPoint?: string;
  zoneLivraison?: string;
  kmZoneUrbaine?: string;
  creneauLivraisonDe?: string;
  nombreMoyenPointsLivraison?: string;
  creneauLivraisonA?: string;
  nombreRippeurs?: number;
  typeCarburant?: string;
  typeColis?: string;
  typePalette?: string;
  longeurPalette?: number;
  largeurPalette?: number;
  hauteurPalette?: number;
  quantitePalette?: number;
  numeroChargement?: string;
  rueChargement?: string;
  codePostalChargement?: string;
  villeChargement?: string;
  nomContactChargement?: string;
  numTelephoneChargement?: string;
  instructions?: string;
  retourPointEnlevement?: boolean;
  dateEnlevement?: string;
  heureEnlevement?: string;
  type?: string;
  livraisonJourFerie?: boolean;
  besoinManutentionnaire?: number;
  gestionRetour?: boolean;
  tempsGestionRetoursEstime?: number;
  typeColisage?: string;
  exigenceVehicule?: boolean;
  chargeUtileVL?: number;
  chargeUtilePL?: number;
  chargeUtileSPL?: number;
  hauteurMaxVehicule?: number;
  typeChargement?: string;
  typeVehicule?: string;
  priseRdv?: string;
  exigenceRdv?: string;
  userName?: string;
  userEmail?: string;
  prixAchat?: number;
  tourneeId?: number;
  lignesTransport?: LigneTransportCreateDTO[];
  articleProduits?: ArticleProduitCreateDTO[];
  produitDetails?: ProduitDetailCreateDTO[];
}

export interface DetailOrdreTransportUpdateDTO extends DetailOrdreTransportCreateDTO {
  id?: number;
  prixTotalHT?: number;
  quantiteConversion?: number;
  conversion?: boolean;
  prixUnitaireHTConversion?: number;
  tauxTva?: number;
  taxeId?: number;
  prixTTC?: number;
  indexationCarburant?: string;
  montantTva?: number;
  consigne?: string;
  montantIndexationCarburant?: number;
  uniteVente?: number;
  typeRessource?: string;
  ressourceId?: number;
  ressourceDesignation?: string;
  tempsGestionRetoursImportants?: string;
  specificitePl?: string;
  specificiteVl?: string;
  specificiteSpl?: string;
  presentationObligCin?: boolean;
  ouverturePorte?: string;
  lignesTransport?: LigneTransportUpdateDTO[];
  articleProduits?: ArticleProduitUpdateDTO[];
  produitDetails?: ProduitDetailUpdateDTO[];
}

export interface DetailOrdreTransportReadDTO extends DetailOrdreTransportUpdateDTO {
  montantTtc?: number;
  marge?: number;
  lignesTransport?: LigneTransportReadDTO[];
  articleProduits?: ArticleProduitReadDTO[];
  produitDetails?: ProduitDetailReadDTO[];
}

export interface OrdreTransportCreateDTO {
  reference?: string;
  refexterne?: string;
  refCommande?: string;
  date?: string;
  observations?: string;
  partieIntId?: number;
  transporteur?: string;
  modepaiementId?: number;
  marcheId?: number;
  natureLivraison?: string;
  idDepot?: number;
  idCamion?: number;
  idRemorque?: number;
  idChauffeur?: number;
  camion?: string;
  chauffeur?: string;
  remorque?: string;
  numTelPassager?: string;
  emailPassager?: string;
  adressePassager?: string;
  reglementId?: number;
  boutiqueId?: number;
  devise?: number;
  numCommandeClient?: string;
  tourneeId?: number;
  details?: DetailOrdreTransportCreateDTO[];
}

export interface OrdreTransportUpdateDTO extends OrdreTransportCreateDTO {
  etat?: string;
  tauxConversion?: number;
  referenceBlManuel?: string;
  userName?: string;
  details?: DetailOrdreTransportUpdateDTO[];
}

export interface OrdreTransportReadDTO extends OrdreTransportUpdateDTO {
  id?: number;
  dateCreation?: string;
  dateModification?: string;
  montantHTaxe?: number;
  montantTTC?: number;
  montantTaxe?: number;
  montantRemise?: number;
  totalPourcentageRemise?: number;
  typeClient?: string;
  referenceClient?: string;
  metrageTotal?: number;
  prixAchatTotal?: number;
  montantConverti?: number;
  identifiantLivraison?: string;
  details?: DetailOrdreTransportReadDTO[];
}

/**
 * Alias de compatibilité locale
 */

export type OrdreTransportRead = OrdreTransportReadDTO;
export type OrdreDetailRead = DetailOrdreTransportReadDTO;
export type OrdreDetailLigneRead = LigneTransportReadDTO;
export type ProduitDetailRead = ProduitDetailReadDTO;
export type ArticleProduitRead = ArticleProduitReadDTO;
export type OrdreDetailCreateDTO = DetailOrdreTransportCreateDTO;
export type OrdreDetailUpdateDTO = DetailOrdreTransportUpdateDTO;
export type OrdreDetailLigneCreateDTO = LigneTransportCreateDTO;
export type OrdreDetailLigneUpdateDTO = LigneTransportUpdateDTO;

/**
 * État de l'application
 */

export interface OrdresTransportState {
  ordres: OrdreTransportRead[];
  selectedOrdre: OrdreTransportRead | null;
  isLoading: boolean;
  error: string | null;
  filter: string;
}
