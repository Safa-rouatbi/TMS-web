import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { OrdresTransportFacade } from '../../../state/ordres-transport.facade';
import { OrdreDetailRead, OrdreDetailCreateDTO, OrdreDetailUpdateDTO } from '../../../models/ordres-transport.model';

@Component({
  selector: 'app-detail-modal',
  templateUrl: './detail-modal.component.html'
})
export class DetailModalComponent implements OnInit, OnDestroy {
  form!: FormGroup;
  isEdit = false;
  isLoading = false;
  errorMessage = '';
  successMessage = '';

  @Input() ordreId!: number;
  @Input() detailData: OrdreDetailRead | null = null;

  @Output() save = new EventEmitter<OrdreDetailRead>();
  @Output() cancel = new EventEmitter<void>();

  private destroy$ = new Subject<void>();

  constructor(
    private fb: FormBuilder,
    private facade: OrdresTransportFacade
  ) {}

  ngOnInit(): void {
    this.initForm();
    if (this.detailData) {
      this.isEdit = true;
      this.populateForm(this.detailData);
    }
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  private initForm(): void {
    this.form = this.fb.group({
      reference: ['', Validators.required],
      quantite: [1, Validators.required],
      prixUnitaireHT: [0],
      description: ['']
    });
  }

  private populateForm(detail: OrdreDetailRead): void {
    this.form.patchValue({
      reference: detail.reference,
      quantite: detail.quantite,
      prixUnitaireHT: detail.prixUnitaireHT,
      description: detail.description
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

    if (this.isEdit && this.detailData) {
      // Modifier: PUT /api/ordres-transport/{ordreId}/details/{detailId}
      this.facade.updateDetail(this.ordreId, this.detailData.id!, payload as OrdreDetailUpdateDTO)
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: (result) => {
            this.successMessage = 'Détail modifié avec succès';
            this.isLoading = false;
            this.save.emit(result);
          },
          error: (err) => {
            this.errorMessage = err?.error?.message || 'Erreur lors de la modification';
            this.isLoading = false;
          }
        });
    } else {
      // Créer: POST /api/ordres-transport/{ordreId}/details
      this.facade.createDetail(this.ordreId, payload as OrdreDetailCreateDTO)
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: (result) => {
            this.successMessage = 'Détail créé avec succès';
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
