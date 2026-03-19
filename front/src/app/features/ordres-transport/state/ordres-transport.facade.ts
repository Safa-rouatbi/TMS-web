import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, finalize, tap } from 'rxjs/operators';
import { OrdresTransportStore } from './ordres-transport.store';
import { OrdresTransportApiService } from '../data-access/services/ordres-transport-api.service';
import {
  OrdreTransportRead,
  OrdreTransportCreateDTO,
  OrdreTransportUpdateDTO,
  OrdreDetailRead,
  OrdreDetailCreateDTO,
  OrdreDetailUpdateDTO,
  OrdreDetailLigneRead,
  OrdreDetailLigneCreateDTO,
  OrdreDetailLigneUpdateDTO,
  ProduitDetailRead,
  ProduitDetailCreateDTO,
  ProduitDetailUpdateDTO,
  ArticleProduitRead,
  ArticleProduitCreateDTO,
  ArticleProduitUpdateDTO,
  OrdresTransportState
} from '../models/ordres-transport.model';

@Injectable({
  providedIn: 'root'
})
export class OrdresTransportFacade {
  // Exposer l'état et les sélecteurs
  public state$: Observable<OrdresTransportState>;
  public ordres$: Observable<OrdreTransportRead[]>;
  public selectedOrdre$: Observable<OrdreTransportRead | null>;
  public isLoading$: Observable<boolean>;
  public error$: Observable<string | null>;

  constructor(
    private store: OrdresTransportStore,
    private api: OrdresTransportApiService
  ) {
    this.state$ = this.store.state$;
    this.ordres$ = this.store.ordres$;
    this.selectedOrdre$ = this.store.selectedOrdre$;
    this.isLoading$ = this.store.isLoading$;
    this.error$ = this.store.error$;
  }

  /**
   * === Ordres Transport ===
   */
  loadOrdres(reference?: string, etat?: string): Observable<OrdreTransportRead[]> {
    this.store.setLoading(true);
    this.store.setError(null);

    return this.api.getAll(reference, etat).pipe(
      tap(ordres => this.store.setOrdres(ordres)),
      catchError(error => {
        const errorMsg = error?.error?.message || 'Erreur lors du chargement';
        this.store.setError(errorMsg);
        return throwError(() => error);
      }),
      finalize(() => this.store.setLoading(false))
    );
  }

  loadOrdreById(id: number): Observable<OrdreTransportRead> {
    this.store.setLoading(true);
    this.store.setError(null);

    return this.api.getById(id).pipe(
      tap(ordre => this.store.setSelectedOrdre(ordre)),
      catchError(error => {
        const errorMsg = error?.error?.message || 'Erreur lors du chargement';
        this.store.setError(errorMsg);
        return throwError(() => error);
      }),
      finalize(() => this.store.setLoading(false))
    );
  }

  createOrdre(dto: OrdreTransportCreateDTO): Observable<OrdreTransportRead> {
    this.store.setLoading(true);
    this.store.setError(null);

    return this.api.create(dto).pipe(
      tap(ordre => this.store.addOrdre(ordre)),
      catchError(error => {
        const errorMsg = error?.error?.message || 'Erreur lors de la création';
        this.store.setError(errorMsg);
        return throwError(() => error);
      }),
      finalize(() => this.store.setLoading(false))
    );
  }

  updateOrdre(id: number, dto: OrdreTransportUpdateDTO): Observable<OrdreTransportRead> {
    this.store.setLoading(true);
    this.store.setError(null);

    return this.api.update(id, dto).pipe(
      tap(ordre => this.store.updateOrdre(ordre)),
      catchError(error => {
        const errorMsg = error?.error?.message || 'Erreur lors de la mise à jour';
        this.store.setError(errorMsg);
        return throwError(() => error);
      }),
      finalize(() => this.store.setLoading(false))
    );
  }

  deleteOrdre(id: number): Observable<void> {
    this.store.setLoading(true);
    this.store.setError(null);

    return this.api.delete(id).pipe(
      tap(() => this.store.removeOrdre(id)),
      catchError(error => {
        const errorMsg = error?.error?.message || 'Erreur lors de la suppression';
        this.store.setError(errorMsg);
        return throwError(() => error);
      }),
      finalize(() => this.store.setLoading(false))
    );
  }


  getDetails(ordreId: number): Observable<OrdreDetailRead[]> {
    return this.api.getDetails(ordreId);
  }

  getDetailById(ordreId: number, detailId: number): Observable<OrdreDetailRead> {
    return this.api.getDetailById(ordreId, detailId);
  }

  createDetail(ordreId: number, dto: OrdreDetailCreateDTO): Observable<OrdreDetailRead> {
    return this.api.createDetail(ordreId, dto);
  }

  updateDetail(ordreId: number, detailId: number, dto: OrdreDetailUpdateDTO): Observable<OrdreDetailRead> {
    return this.api.updateDetail(ordreId, detailId, dto);
  }

  deleteDetail(ordreId: number, detailId: number): Observable<void> {
    return this.api.deleteDetail(ordreId, detailId);
  }

  
  getLignes(ordreId: number, detailId: number): Observable<OrdreDetailLigneRead[]> {
    return this.api.getLignes(ordreId, detailId);
  }

  createLigne(ordreId: number, detailId: number, dto: OrdreDetailLigneCreateDTO): Observable<OrdreDetailLigneRead> {
    return this.api.createLigne(ordreId, detailId, dto);
  }

  updateLigne(ordreId: number, detailId: number, ligneId: number, dto: OrdreDetailLigneUpdateDTO): Observable<OrdreDetailLigneRead> {
    return this.api.updateLigne(ordreId, detailId, ligneId, dto);
  }

  deleteLigne(ordreId: number, detailId: number, ligneId: number): Observable<void> {
    return this.api.deleteLigne(ordreId, detailId, ligneId);
  }


  getProduitsDetails(ordreId: number, detailId: number): Observable<ProduitDetailRead[]> {
    return this.api.getProduitsDetails(ordreId, detailId);
  }

  createProduitDetail(ordreId: number, detailId: number, dto: ProduitDetailCreateDTO): Observable<ProduitDetailRead> {
    return this.api.createProduitDetail(ordreId, detailId, dto);
  }

  updateProduitDetail(ordreId: number, detailId: number, produitDetailId: number, dto: ProduitDetailUpdateDTO): Observable<ProduitDetailRead> {
    return this.api.updateProduitDetail(ordreId, detailId, produitDetailId, dto);
  }

  deleteProduitDetail(ordreId: number, detailId: number, produitDetailId: number): Observable<void> {
    return this.api.deleteProduitDetail(ordreId, detailId, produitDetailId);
  }

 
  getArticles(ordreId: number, detailId: number): Observable<ArticleProduitRead[]> {
    return this.api.getArticles(ordreId, detailId);
  }

  createArticle(ordreId: number, detailId: number, dto: ArticleProduitCreateDTO): Observable<ArticleProduitRead> {
    return this.api.createArticle(ordreId, detailId, dto);
  }

  updateArticle(ordreId: number, detailId: number, articleId: number, dto: ArticleProduitUpdateDTO): Observable<ArticleProduitRead> {
    return this.api.updateArticle(ordreId, detailId, articleId, dto);
  }

  deleteArticle(ordreId: number, detailId: number, articleId: number): Observable<void> {
    return this.api.deleteArticle(ordreId, detailId, articleId);
  }

  
  selectOrdre(ordre: OrdreTransportRead | null): void {
    this.store.setSelectedOrdre(ordre);
  }

  setFilter(filter: string): void {
    this.store.setFilter(filter);
  }

  clearError(): void {
    this.store.setError(null);
  }

  reset(): void {
    this.store.reset();
  }
}
