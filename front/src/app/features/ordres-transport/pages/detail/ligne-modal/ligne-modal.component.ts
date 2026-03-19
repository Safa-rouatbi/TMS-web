import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { OrdresTransportFacade } from '../../../state/ordres-transport.facade';
import { OrdreDetailLigneRead, OrdreDetailLigneCreateDTO, OrdreDetailLigneUpdateDTO } from '../../../models/ordres-transport.model';

@Component({
  selector: 'app-ligne-modal',
  templateUrl: './ligne-modal.component.html'
})
export class LigneModalComponent implements OnInit, OnDestroy {
  form!: FormGroup;
  isEdit = false;
  isLoading = false;
  errorMessage = '';
  successMessage = '';

  @Input() ordreId!: number;
  @Input() detailId!: number;
  @Input() ligneData: OrdreDetailLigneRead | null = null;

  @Output() save = new EventEmitter<OrdreDetailLigneRead>();
  @Output() cancel = new EventEmitter<void>();

  private destroy$ = new Subject<void>();

  constructor(
    private fb: FormBuilder,
    private facade: OrdresTransportFacade
  ) {}

  ngOnInit(): void {
    this.initForm();
    if (this.ligneData) {
      this.isEdit = true;
      this.populateForm(this.ligneData);
    }
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  private initForm(): void {
    this.form = this.fb.group({
      lieu: ['', Validators.required],
      quantite: [1, Validators.required],
      pu: [0],
      societe: ['']
    });
  }

  private populateForm(ligne: OrdreDetailLigneRead): void {
    this.form.patchValue({
      lieu: ligne.lieu,
      quantite: ligne.quantite,
      pu: ligne.pu,
      societe: ligne.societe
    });
  }

  onSave(): void {
    if (this.form.invalid) {
      this.errorMessage = 'Veuillez remplir les champs obligatoires';
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';
    this.successMessage = '';

    const payload = this.form.value;

    if (this.isEdit && this.ligneData) {
      // Modifier: PUT /api/ordres-transport/{ordreId}/details/{detailId}/lignes/{ligneId}
      this.facade.updateLigne(
        this.ordreId,
        this.detailId,
        this.ligneData.id!,
        payload as OrdreDetailLigneUpdateDTO
      )
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: (result) => {
            this.successMessage = 'Ligne modifiée avec succès';
            this.isLoading = false;
            this.save.emit(result);
          },
          error: (err) => {
            this.errorMessage = err?.error?.message || 'Erreur lors de la modification';
            this.isLoading = false;
          }
        });
    } else {
      // Créer: POST /api/ordres-transport/{ordreId}/details/{detailId}/lignes
      this.facade.createLigne(
        this.ordreId,
        this.detailId,
        payload as OrdreDetailLigneCreateDTO
      )
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: (result) => {
            this.successMessage = 'Ligne créée avec succès';
            this.isLoading = false;
            this.save.emit(result);
          },
          error: (err) => {
            this.errorMessage = err?.error?.message || 'Erreur lors de la création';
            this.isLoading = false;
          }
        });
    }
  }

  onCancel(): void {
    this.cancel.emit();
  }
}
