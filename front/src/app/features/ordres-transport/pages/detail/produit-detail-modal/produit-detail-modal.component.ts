import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { OrdresTransportFacade } from '../../../state/ordres-transport.facade';
import { ProduitDetailRead, ProduitDetailCreateDTO, ProduitDetailUpdateDTO } from '../../../models/ordres-transport.model';

@Component({
  selector: 'app-produit-detail-modal',
  templateUrl: './produit-detail-modal.component.html'
})
export class ProduitDetailModalComponent implements OnInit, OnDestroy {
  form!: FormGroup;
  isEdit = false;
  isLoading = false;
  errorMessage = '';
  successMessage = '';

  @Input() ordreId!: number;
  @Input() detailId!: number;
  @Input() produitDetailData: ProduitDetailRead | null = null;

  @Output() save = new EventEmitter<ProduitDetailRead>();
  @Output() cancel = new EventEmitter<void>();

  private destroy$ = new Subject<void>();

  constructor(
    private fb: FormBuilder,
    private facade: OrdresTransportFacade
  ) {}

  ngOnInit(): void {
    this.initForm();
    if (this.produitDetailData) {
      this.isEdit = true;
      this.populateForm(this.produitDetailData);
    }
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  private initForm(): void {
    this.form = this.fb.group({
      typeRessource: [''],
      ressource: [''],
      paysEnlevement: [''],
      paysLivraison: [''],
      poidsTotal: [0],
      prixAchat: [0]
    });
  }

  private populateForm(produit: ProduitDetailRead): void {
    this.form.patchValue({
      typeRessource: produit.typeRessource,
      ressource: produit.ressource,
      paysEnlevement: produit.paysEnlevement,
      paysLivraison: produit.paysLivraison,
      poidsTotal: produit.poidsTotal,
      prixAchat: produit.prixAchat
    });
  }

  onSave(): void {
    this.isLoading = true;
    this.errorMessage = '';
    this.successMessage = '';

    const payload = this.form.value;

    if (this.isEdit && this.produitDetailData) {
      // Modifier: PUT /api/ordres-transport/{ordreId}/details/{detailId}/produits-details/{produitDetailId}
      this.facade.updateProduitDetail(
        this.ordreId,
        this.detailId,
        this.produitDetailData.id!,
        payload as ProduitDetailUpdateDTO
      )
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: (result) => {
            this.successMessage = 'Produit modifié avec succès';
            this.isLoading = false;
            this.save.emit(result);
          },
          error: (err) => {
            this.errorMessage = err?.error?.message || 'Erreur lors de la modification';
            this.isLoading = false;
          }
        });
    } else {
      // Créer: POST /api/ordres-transport/{ordreId}/details/{detailId}/produits-details
      this.facade.createProduitDetail(
        this.ordreId,
        this.detailId,
        payload as ProduitDetailCreateDTO
      )
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: (result) => {
            this.successMessage = 'Produit créé avec succès';
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
