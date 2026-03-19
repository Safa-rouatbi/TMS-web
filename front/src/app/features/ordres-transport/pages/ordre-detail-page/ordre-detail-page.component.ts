import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil, switchMap } from 'rxjs/operators';
import { OrdresTransportFacade } from '../../state/ordres-transport.facade';
import { OrdreTransportRead, OrdreDetailLigneRead, ProduitDetailRead, ArticleProduitRead } from '../../models/ordres-transport.model';

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

  private ordreId!: number;
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
      this.facade.loadOrdreById(this.ordreId)
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: (ordre) => {
            this.ordre = ordre;
          }
        });
    }
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
}
