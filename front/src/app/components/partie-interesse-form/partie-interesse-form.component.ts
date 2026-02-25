import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { PartieInteresseService } from '../../services/partie-interesse.service';
import { PartieInteresseCreateDTO } from '../../models/partie-interesse.model';

@Component({
  selector: 'app-partie-interesse-form',
  templateUrl: './partie-interesse-form.component.html',
  styleUrls: ['./partie-interesse-form.component.scss']
})
export class PartieInteresseFormComponent implements OnInit {
  @Output() formClosed = new EventEmitter<void>();

  isLoading = false;
  errorMessage = '';
  successMessage = '';

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
  }

  onSubmit(): void {
    this.isLoading = true;
    this.errorMessage = '';
    this.successMessage = '';

    this.partieService.create(this.formulaire).subscribe({
      next: (response) => {
        this.successMessage = 'Partie intéressée créée avec succès !';
        this.isLoading = false;
        this.resetForm();

        // Retour à la liste après 2 secondes
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
