import { Component, OnInit, Output, EventEmitter, Input, OnChanges, SimpleChanges } from '@angular/core';
import { PartieInteresseService } from '../../services/partie-interesse.service';
import { PartieInteresseCreateDTO, PartieInteresseReadDTO, PartieInteresseUpdateDTO } from '../../models/partie-interesse.model';

@Component({
  selector: 'app-partie-interesse-form',
  templateUrl: './partie-interesse-form.component.html',
  styleUrls: ['./partie-interesse-form.component.scss']
})
export class PartieInteresseFormComponent implements OnInit, OnChanges {
  @Input() isEditMode = false;
  @Input() partieToEdit: PartieInteresseReadDTO | null = null;
  @Output() formClosed = new EventEmitter<void>();
  @Output() partieUpdated = new EventEmitter<PartieInteresseReadDTO>();

  isLoading = false;
  errorMessage = '';
  successMessage = '';

  // Validation téléphone
  telephoneError = '';
  telephoneExists = false;

  // Formulaire complet
  formulaire: PartieInteresseCreateDTO = {
    raisonSociale: '',
    abreviation: '',
    activite: '',
    observation: '',
    email: '',
    email2: '',
    telephone: '',
    telephoneMobile: '',
    adresse: '',
    matrFiscal: '',
    identifiantFiscal: '',
    categorieTva: '',
    modePaiement: '',
    modalitePaiement: '',
    rib: '',
    iban: '',
    bic: '',
    actif: true,
    typeClient: 'CLIENT',
    prestataire: false,
    nom: '',
    prenom: '',
    numeroCin: '',
    numeroLicence: '',
    numeroLicenceComissionnaire: '',
    dateEcheanceLicenceComissionnaire: '',
    grilleTarifaire: '',
    priseRdvObligatoire: false
  };


  typesDisponibles = [
    { value: 'CLIENT', label: 'Client' },
    { value: 'FOURNISSEUR', label: 'Fournisseur' },
    { value: 'TRANSPORTEUR', label: 'Transporteur' },
    { value: 'CHAUFFEUR', label: 'Chauffeur' },
    { value: 'COMMISSIONNAIRE', label: 'Commissionnaire' },
    { value: 'BANQUE', label: 'Banque' }
  ];

  constructor(private partieService: PartieInteresseService) { }

  ngOnInit(): void {
    if (this.isEditMode && this.partieToEdit) {
      this.remplirFormulaire();
    }
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['partieToEdit'] && this.partieToEdit && this.isEditMode) {
      this.remplirFormulaire();
    }
  }

  private remplirFormulaire(): void {
    if (this.partieToEdit) {
      this.formulaire = { ...this.partieToEdit };
    }
  }

  validerTelephone(): void {
    const telephone = this.formulaire.telephone;
    this.telephoneError = '';
    this.telephoneExists = false;


    if (!telephone || telephone.trim() === '') {
      this.telephoneError = 'Le téléphone est obligatoire';
      return;
    }

    
    const telephoneNumerique = telephone.replace(/\s/g, ''); 
    if (!/^\d+$/.test(telephoneNumerique)) {
      this.telephoneError = 'Le téléphone doit contenir uniquement des chiffres';
      return;
    }

   
    if (telephoneNumerique.length < 6) {
      this.telephoneError = 'Le téléphone doit contenir au minimum 6 chiffres';
      return;
    }

   
    this.verifierTelephoneUnique(telephoneNumerique);
  }

  
  verifierTelephoneUnique(telephone: string): void {
   
    const telephonesExistants = ['0123456789', '0612345678', '0712345678']; 
    
    if (telephonesExistants.includes(telephone)) {
      this.telephoneError = 'Ce téléphone existe déjà';
      this.telephoneExists = true;
    }
  }

  onSubmit(): void {
  
    this.validerTelephone();
    
    
    if (this.telephoneError) {
      this.errorMessage = 'Veuillez corriger les erreurs avant de soumettre';
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';
    this.successMessage = '';

    if (this.isEditMode && this.partieToEdit) {
      this.updatePartie();
    } else {
      this.createPartie();
    }
  }

  private createPartie(): void {
    this.partieService.create(this.formulaire).subscribe({
      next: (response) => {
        this.successMessage = 'Partie intéressée créée avec succès !';
        this.isLoading = false;
        this.resetForm();

      
        setTimeout(() => {
          this.formClosed.emit();
        }, 2000);
      },
      error: (err) => {
        this.errorMessage = 'Erreur lors de la création de la partie intéressée';
        this.isLoading = false;
        console.error('Erreur:', err);
      }
    });
  }

  private updatePartie(): void {
    if (!this.partieToEdit) return;

    const updateData: PartieInteresseUpdateDTO = {
      raisonSociale: this.formulaire.raisonSociale,
      abreviation: this.formulaire.abreviation,
      activite: this.formulaire.activite,
      email: this.formulaire.email,
      telephone: this.formulaire.telephone,
      actif: this.formulaire.actif
    };

    this.partieService.update(this.partieToEdit.id, updateData).subscribe({
      next: (response) => {
        this.successMessage = 'Partie intéressée modifiée avec succès !';
        this.isLoading = false;
        this.partieUpdated.emit(response);

      
        setTimeout(() => {
          this.formClosed.emit();
        }, 2000);
      },
      error: (err) => {
        this.errorMessage = 'Erreur lors de la modification de la partie intéressée';
        this.isLoading = false;
        console.error('Erreur:', err);
      }
    });
  }

  resetForm(): void {
    this.formulaire = {
      raisonSociale: '',
      abreviation: '',
      activite: '',
      observation: '',
      email: '',
      email2: '',
      telephone: '',
      telephoneMobile: '',
      adresse: '',
      matrFiscal: '',
      identifiantFiscal: '',
      categorieTva: '',
      modePaiement: '',
      modalitePaiement: '',
      rib: '',
      iban: '',
      bic: '',
      actif: true,
      typeClient: 'CLIENT',
      prestataire: false,
      nom: '',
      prenom: '',
      numeroCin: '',
      numeroLicence: '',
      numeroLicenceComissionnaire: '',
      dateEcheanceLicenceComissionnaire: '',
      grilleTarifaire: '',
      priseRdvObligatoire: false
    };
    this.errorMessage = '';
    this.successMessage = '';
  }

  annuler(): void {
    this.formClosed.emit();
  }
}
