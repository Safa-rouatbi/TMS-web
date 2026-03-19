import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { OrdresTransportFacade } from '../../state/ordres-transport.facade';
import { OrdreTransportRead } from '../../models/ordres-transport.model';

@Component({
  selector: 'app-ordres-list-page',
  templateUrl: './ordres-list-page.component.html',
  styleUrls: ['./ordres-list-page.component.scss']
})
export class OrdresListPageComponent implements OnInit, OnDestroy {
  ordres$;
  isLoading$;
  error$;

  private destroy$ = new Subject<void>();

  constructor(
    private facade: OrdresTransportFacade,
    private router: Router
  ) {
    this.ordres$ = this.facade.ordres$;
    this.isLoading$ = this.facade.isLoading$;
    this.error$ = this.facade.error$;
  }

  ngOnInit(): void {
    this.loadOrdres();
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  loadOrdres(): void {
    this.facade.loadOrdres()
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        error: (err) => console.error('Erreur chargement ordres:', err)
      });
  }

  onNew(): void {
    this.router.navigate(['/ordres-transport/new']);
  }

  onEdit(ordreId: number): void {
    this.router.navigate(['/ordres-transport', ordreId, 'edit']);
  }

  onView(ordreId: number): void {
    this.router.navigate(['/ordres-transport', ordreId]);
  }

  onDelete(ordreId: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer cette commande ?')) {
      this.facade.deleteOrdre(ordreId)
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          error: (err) => console.error('Erreur suppression:', err)
        });
    }
  }

  onRetry(): void {
    this.loadOrdres();
  }
}
