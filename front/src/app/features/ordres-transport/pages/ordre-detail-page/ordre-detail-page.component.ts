import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil, switchMap } from 'rxjs/operators';
import { OrdresTransportFacade } from '../../state/ordres-transport.facade';
import { OrdreTransportRead, OrdreDetailRead, OrdreDetailLigneRead, ProduitDetailRead, ArticleProduitRead } from '../../models/ordres-transport.model';

@Component({
  selector: 'app-ordre-detail-page',
  templateUrl: './ordre-detail-page.component.html',
  styleUrls: ['./ordre-detail-page.component.scss']
})
export class OrdreDetailPageComponent implements OnInit, OnDestroy {
  ordre: OrdreTransportRead | null = null;
  isLoading$;
  error$;
  activeTab = 'details';
  actionError: string | null = null;

  showDetailModal = false;
  showLigneModal = false;
  showProduitModal = false;
  showArticleModal = false;

  selectedDetail: OrdreDetailRead | null = null;
  selectedLigne: OrdreDetailLigneRead | null = null;
  selectedProduit: ProduitDetailRead | null = null;
  selectedArticle: ArticleProduitRead | null = null;

  ordreId!: number;
  private destroy$ = new Subject<void>();

  constructor(
    private facade: OrdresTransportFacade,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.isLoading$ = this.facade.isLoading$;
    this.error$ = this.facade.error$;
  }

  ngOnInit(): void {
    this.route.params
      .pipe(
        switchMap(params => {
          this.ordreId = parseInt(params['id'], 10);
          return this.facade.loadOrdreById(this.ordreId);
        }),
        takeUntil(this.destroy$)
      )
      .subscribe({
        next: (ordre) => {
          this.ordre = ordre;
        },
        error: (err) => {
          console.error('Erreur chargement ordre:', err);
          this.router.navigate(['/ordres-transport']);
        }
      });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  onEdit(): void {
    this.router.navigate(['/ordres-transport', this.ordreId, 'edit']);
  }

  onBack(): void {
    this.router.navigate(['/ordres-transport']);
  }

  setActiveTab(tab: string): void {
    this.activeTab = tab;
  }

  onRetry(): void {
    if (this.ordreId) {
      this.refreshOrdre();
    }
  }

  onAddDetail(): void {
    this.clearActionError();
    this.selectedDetail = null;
    this.showDetailModal = true;
  }

  onEditDetail(detail: OrdreDetailRead): void {
    this.clearActionError();
    this.selectedDetail = detail;
    this.showDetailModal = true;
  }

  onDeleteDetail(detailId?: number): void {
    if (!detailId || !this.ordre?.id) {
      return;
    }

    if (!confirm('Confirmer la suppression du détail ?')) {
      return;
    }

    this.clearActionError();
    this.facade.deleteDetail(this.ordre.id, detailId)
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: () => this.refreshOrdre(),
        error: () => {
          this.actionError = 'Suppression du détail impossible';
        }
      });
  }

  onDetailSaved(): void {
    this.showDetailModal = false;
    this.selectedDetail = null;
    this.refreshOrdre();
  }

  onDetailModalClosed(): void {
    this.showDetailModal = false;
    this.selectedDetail = null;
  }

  onAddLigne(): void {
    if (!this.hasFirstDetail()) {
      this.actionError = 'Ajoutez un détail avant de créer une ligne';
      return;
    }

    this.clearActionError();
    this.selectedLigne = null;
    this.showLigneModal = true;
  }

  onEditLigne(ligne: OrdreDetailLigneRead): void {
    if (!this.hasFirstDetail()) {
      this.actionError = 'Aucun détail parent trouvé pour cette ligne';
      return;
    }

    this.clearActionError();
    this.selectedLigne = ligne;
    this.showLigneModal = true;
  }

  onDeleteLigne(ligneId?: number): void {
    const detailId = this.getFirstDetailId();
    if (!ligneId || !detailId || !this.ordre?.id) {
      return;
    }

    if (!confirm('Confirmer la suppression de la ligne ?')) {
      return;
    }

    this.clearActionError();
    this.facade.deleteLigne(this.ordre.id, detailId, ligneId)
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: () => this.refreshOrdre(),
        error: () => {
          this.actionError = 'Suppression de la ligne impossible';
        }
      });
  }

  onLigneSaved(): void {
    this.showLigneModal = false;
    this.selectedLigne = null;
    this.refreshOrdre();
  }

  onLigneModalClosed(): void {
    this.showLigneModal = false;
    this.selectedLigne = null;
  }

  onAddProduit(): void {
    if (!this.hasFirstDetail()) {
      this.actionError = 'Ajoutez un détail avant de créer un produit détail';
      return;
    }

    this.clearActionError();
    this.selectedProduit = null;
    this.showProduitModal = true;
  }

  onEditProduit(produit: ProduitDetailRead): void {
    if (!this.hasFirstDetail()) {
      this.actionError = 'Aucun détail parent trouvé pour ce produit';
      return;
    }

    this.clearActionError();
    this.selectedProduit = produit;
    this.showProduitModal = true;
  }

  onDeleteProduit(produitId?: number): void {
    const detailId = this.getFirstDetailId();
    if (!produitId || !detailId || !this.ordre?.id) {
      return;
    }

    if (!confirm('Confirmer la suppression du produit détail ?')) {
      return;
    }

    this.clearActionError();
    this.facade.deleteProduitDetail(this.ordre.id, detailId, produitId)
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: () => this.refreshOrdre(),
        error: () => {
          this.actionError = 'Suppression du produit détail impossible';
        }
      });
  }

  onProduitSaved(): void {
    this.showProduitModal = false;
    this.selectedProduit = null;
    this.refreshOrdre();
  }

  onProduitModalClosed(): void {
    this.showProduitModal = false;
    this.selectedProduit = null;
  }

  onAddArticle(): void {
    if (!this.hasFirstDetail()) {
      this.actionError = 'Ajoutez un détail avant de créer un article';
      return;
    }

    this.clearActionError();
    this.selectedArticle = null;
    this.showArticleModal = true;
  }

  onEditArticle(article: ArticleProduitRead): void {
    if (!this.hasFirstDetail()) {
      this.actionError = 'Aucun détail parent trouvé pour cet article';
      return;
    }

    this.clearActionError();
    this.selectedArticle = article;
    this.showArticleModal = true;
  }

  onDeleteArticle(articleId?: number): void {
    const detailId = this.getFirstDetailId();
    if (!articleId || !detailId || !this.ordre?.id) {
      return;
    }

    if (!confirm('Confirmer la suppression de l\'article ?')) {
      return;
    }

    this.clearActionError();
    this.facade.deleteArticle(this.ordre.id, detailId, articleId)
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: () => this.refreshOrdre(),
        error: () => {
          this.actionError = 'Suppression de l\'article impossible';
        }
      });
  }

  onArticleSaved(): void {
    this.showArticleModal = false;
    this.selectedArticle = null;
    this.refreshOrdre();
  }

  onArticleModalClosed(): void {
    this.showArticleModal = false;
    this.selectedArticle = null;
  }

  /**
   * Helper methods pour accéder aux données imbriquées de manière sécurisée
   */
  getFirstDetailLignes(): OrdreDetailLigneRead[] | null {
    return this.ordre?.details?.[0]?.lignesTransport || null;
  }

  getFirstDetailProduits(): ProduitDetailRead[] | null {
    return this.ordre?.details?.[0]?.produitDetails || null;
  }

  getFirstArticles(): ArticleProduitRead[] | null {
    return this.ordre?.details?.[0]?.articleProduits || null;
  }

  getFirstDetailId(): number | null {
    const detailId = this.ordre?.details?.[0]?.id;
    return typeof detailId === 'number' ? detailId : null;
  }

  private hasFirstDetail(): boolean {
    return this.getFirstDetailId() !== null;
  }

  private clearActionError(): void {
    this.actionError = null;
  }

  private refreshOrdre(): void {
    this.facade.loadOrdreById(this.ordreId)
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (ordre) => {
          this.ordre = ordre;
        },
        error: () => {
          this.actionError = 'Impossible de rafraîchir les données';
        }
      });
  }
}
