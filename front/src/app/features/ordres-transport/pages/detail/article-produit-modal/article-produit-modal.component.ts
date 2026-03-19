import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { OrdresTransportFacade } from '../../../state/ordres-transport.facade';
import { ArticleProduitRead, ArticleProduitCreateDTO, ArticleProduitUpdateDTO } from '../../../models/ordres-transport.model';

@Component({
  selector: 'app-article-produit-modal',
  templateUrl: './article-produit-modal.component.html'
})
export class ArticleProduitModalComponent implements OnInit, OnDestroy {
  form!: FormGroup;
  isEdit = false;
  isLoading = false;
  errorMessage = '';
  successMessage = '';

  @Input() ordreId!: number;
  @Input() detailId!: number;
  @Input() articleData: ArticleProduitRead | null = null;

  @Output() save = new EventEmitter<ArticleProduitRead>();
  @Output() cancel = new EventEmitter<void>();

  private destroy$ = new Subject<void>();

  constructor(
    private fb: FormBuilder,
    private facade: OrdresTransportFacade
  ) {}

  ngOnInit(): void {
    this.initForm();
    if (this.articleData) {
      this.isEdit = true;
      this.populateForm(this.articleData);
    }
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  private initForm(): void {
    this.form = this.fb.group({
      referenceArticle: ['', Validators.required],
      qte: [1, Validators.required],
      typePalette: [''],
      poidsPaletteTotal: [0],
      gerbable: [false]
    });
  }

  private populateForm(article: ArticleProduitRead): void {
    this.form.patchValue({
      referenceArticle: article.referenceArticle,
      qte: article.qte,
      typePalette: article.typePalette,
      poidsPaletteTotal: article.poidsPaletteTotal,
      gerbable: article.gerbable
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

    if (this.isEdit && this.articleData) {
      // Modifier: PUT /api/ordres-transport/{ordreId}/details/{detailId}/articles-produits/{articleId}
      this.facade.updateArticle(
        this.ordreId,
        this.detailId,
        this.articleData.id!,
        payload as ArticleProduitUpdateDTO
      )
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: (result) => {
            this.successMessage = 'Article modifié avec succès';
            this.isLoading = false;
            this.save.emit(result);
          },
          error: (err) => {
            this.errorMessage = err?.error?.message || 'Erreur lors de la modification';
            this.isLoading = false;
          }
        });
    } else {
      // Créer: POST /api/ordres-transport/{ordreId}/details/{detailId}/articles-produits
      this.facade.createArticle(
        this.ordreId,
        this.detailId,
        payload as ArticleProduitCreateDTO
      )
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: (result) => {
            this.successMessage = 'Article créé avec succès';
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
