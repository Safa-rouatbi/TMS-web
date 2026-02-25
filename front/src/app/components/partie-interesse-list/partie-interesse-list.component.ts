import { Component, OnInit } from '@angular/core';
import { PartieInteresseService } from '../../services/partie-interesse.service';
import { PartieInteresseReadDTO, PartieInteresseUpdateDTO } from '../../models/partie-interesse.model';

@Component({
  selector: 'app-partie-interesse-list',
  templateUrl: './partie-interesse-list.component.html',
  styleUrls: ['./partie-interesse-list.component.scss']
})
export class PartieInteresseListComponent implements OnInit {
  parties: PartieInteresseReadDTO[] = [];
  isLoading = false;
  errorMessage = '';
  successMessage = '';
  selectedType = '';
  showForm = false;
  showUpdateForm = false;
  selectedPartieId: number | null = null;
  partieToUpdate: PartieInteresseReadDTO | null = null;
  partieToView: PartieInteresseReadDTO | null = null;
  showDetailsModal = false;

  constructor(private partieService: PartieInteresseService) { }

  ngOnInit(): void {
    this.loadParties();
  }

  loadParties(): void {
    this.isLoading = true;
    this.errorMessage = '';

    this.partieService.getAll(this.selectedType || undefined).subscribe({
      next: (data) => {
        this.parties = data;
        this.isLoading = false;
      },
      error: (err) => {
        this.errorMessage = 'Erreur lors du chargement des parties intéressées';
        this.isLoading = false;
        console.error('Erreur:', err);
      }
    });
  }

  onFilterChange(): void {
    this.loadParties();
  }

  deletePartie(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir désactiver cette partie intéressée ?\n\nNote : La suppression est réversible, la partie sera simplement marquée comme inactive.')) {
      this.partieService.delete(id).subscribe({
        next: () => {
          this.loadParties(); // Recharger la liste après suppression soft
          // Message de succès
          this.successMessage = 'Partie intéressée désactivée avec succès';
          setTimeout(() => {
            this.successMessage = '';
          }, 5000);
        },
        error: (err) => {
          this.errorMessage = 'Erreur lors de la désactivation de la partie intéressée';
          console.error('Erreur:', err);
          setTimeout(() => {
            this.errorMessage = '';
          }, 5000);
        }
      });
    }
  }

  addPartie(): void {
    this.showForm = true;
  }

  updatePartie(id: number): void {
    // Charger les données de la partie à modifier
    this.isLoading = true;
    this.partieService.getById(id).subscribe({
      next: (data: PartieInteresseReadDTO) => {
        this.partieToUpdate = data;
        this.selectedPartieId = id;
        this.showUpdateForm = true;
        this.isLoading = false;
      },
      error: (err) => {
        this.errorMessage = 'Erreur lors du chargement de la partie à modifier';
        this.isLoading = false;
        console.error('Erreur:', err);
      }
    });
  }

  onUpdateSubmit(): void {
    if (!this.partieToUpdate || !this.selectedPartieId) return;

    this.isLoading = true;
    this.errorMessage = '';


    const updateData: PartieInteresseUpdateDTO = {
      raisonSociale: this.partieToUpdate.raisonSociale,
      abreviation: this.partieToUpdate.abreviation,
      activite: this.partieToUpdate.activite,
      email: this.partieToUpdate.email,
      telephone: this.partieToUpdate.telephone,
      actif: this.partieToUpdate.actif,

    };

    this.partieService.update(this.selectedPartieId, updateData).subscribe({
      next: () => {
        this.successMessage = 'Partie intéressée modifiée avec succès !';
        this.isLoading = false;
        this.closeUpdateForm();

        setTimeout(() => {
          this.successMessage = '';
        }, 3000);
      },
      error: (err) => {
        this.errorMessage = 'Erreur lors de la modification';
        this.isLoading = false;
        console.error('Erreur:', err);
      }
    });
  }

  closeUpdateForm(): void {
    this.showUpdateForm = false;
    this.selectedPartieId = null;
    this.partieToUpdate = null;
    this.loadParties();
  }

  viewPartie(id: number): void {

    this.isLoading = true;
    this.partieService.getById(id).subscribe({
      next: (data: PartieInteresseReadDTO) => {
        this.partieToView = data;
        this.showDetailsModal = true;
        this.isLoading = false;
      },
      error: (err) => {
        this.errorMessage = 'Erreur lors du chargement des détails';
        this.isLoading = false;
        console.error('Erreur:', err);
      }
    });
  }

  closeDetailsModal(): void {
    this.showDetailsModal = false;
    this.partieToView = null;
  }

  onFormClosed(): void {
    this.showForm = false;
    this.loadParties();
  }
}
