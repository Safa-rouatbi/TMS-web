export interface PartieInteresseCreateDTO {
  reference?: string;
  raisonSociale?: string;
  abreviation?: string;
  activite?: string;
  observation?: string;
  email?: string;
  email2?: string;
  telephone?: string;
  telephoneMobile?: string;
  adresse?: string;
  matrFiscal?: string;
  identifiantFiscal?: string;
  autreIdentifiantFiscal?: string;
  categorieTva?: string;
  modePaiement?: string;
  modalitePaiement?: string;
  rib?: string;
  iban?: string;
  bic?: string;
  actif?: boolean;
  typeClient?: string;
  prestataire?: boolean;
  nom?: string;
  prenom?: string;
  numeroCin?: string;
  numeroLicence?: string;
  numeroLicenceComissionnaire?: string;
  dateEcheanceLicenceComissionnaire?: string;
  grilleTarifaire?: string;
  priseRdvObligatoire?: boolean;
}

export interface PartieInteresseReadDTO {
  id: number;
  reference?: string;
  raisonSociale?: string;
  abreviation?: string;
  activite?: string;
  observation?: string;
  email?: string;
  email2?: string;
  telephone?: string;
  telephoneMobile?: string;
  adresse?: string;
  matrFiscal?: string;
  identifiantFiscal?: string;
  autreIdentifiantFiscal?: string;
  categorieTva?: string;
  modePaiement?: string;
  modalitePaiement?: string;
  rib?: string;
  iban?: string;
  bic?: string;
  actif?: boolean;
  typeClient?: string;
  prestataire?: boolean;
  nom?: string;
  prenom?: string;
  numeroCin?: string;
  numeroLicence?: string;
  numeroLicenceComissionnaire?: string;
  dateEcheanceLicenceComissionnaire?: string;
  grilleTarifaire?: string;
  priseRdvObligatoire?: boolean;
}

export interface PartieInteresseUpdateDTO {

  raisonSociale?: string;
  abreviation?: string;
  activite?: string;
  observation?: string;
  actif?: boolean;


  email?: string;
  email2?: string;
  telephone?: string;
  telephoneMobile?: string;
  fax?: string;


  adresse?: string;
  adresseFacturation?: string;
  adresseLivraison?: string;
  paysFacturation?: string;
  villeFacturation?: string;
  codePostalFacturation?: string;
  rueFacturation?: string;
  paysLivraison?: string;
  villeLivraison?: string;
  codePostalLivraison?: string;
  rueLivraison?: string;


  categorieTva?: string;
  modePaiement?: string;
  modalitePaiement?: string;
  rib?: string;
  iban?: string;
  bic?: string;


  interlocuteur?: string;
  emailInterlocuteur?: string;
  telInterlocuteur?: string;


  nom?: string;
  prenom?: string;
  numeroCin?: string;
  numeroLicence?: string;


  numeroLicenceComissionnaire?: string;
  dateEcheanceLicenceComissionnaire?: string;


  grilleTarifaire?: string;
  priseRdvObligatoire?: boolean;
  prestataire?: boolean;
}
