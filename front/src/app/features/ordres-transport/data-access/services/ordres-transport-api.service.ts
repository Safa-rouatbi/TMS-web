import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
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
  ArticleProduitUpdateDTO
} from '../../models/ordres-transport.model';

@Injectable({
  providedIn: 'root'
})
export class OrdresTransportApiService {
  private readonly apiUrl = `${environment.apiUrl}/api/ordres-transport`;

  constructor(private http: HttpClient) {}

  /**
   * === Ordres Transport CRUD ===
   */
  getAll(reference?: string, etat?: string): Observable<OrdreTransportRead[]> {
    let params = new HttpParams();
    if (reference) {
      params = params.set('reference', reference);
    }
    if (etat) {
      params = params.set('etat', etat);
    }
    return this.http.get<OrdreTransportRead[]>(this.apiUrl, { params });
  }

  getById(id: number): Observable<OrdreTransportRead> {
    return this.http.get<OrdreTransportRead>(`${this.apiUrl}/${id}`);
  }

  create(dto: OrdreTransportCreateDTO): Observable<OrdreTransportRead> {
    return this.http.post<OrdreTransportRead>(this.apiUrl, dto);
  }

  update(id: number, dto: OrdreTransportUpdateDTO): Observable<OrdreTransportRead> {
    return this.http.put<OrdreTransportRead>(`${this.apiUrl}/${id}`, dto);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  /**
   * === Détails CRUD ===
   */
  getDetails(ordreId: number): Observable<OrdreDetailRead[]> {
    return this.http.get<OrdreDetailRead[]>(`${this.apiUrl}/${ordreId}/details`);
  }

  getDetailById(ordreId: number, detailId: number): Observable<OrdreDetailRead> {
    return this.http.get<OrdreDetailRead>(`${this.apiUrl}/${ordreId}/details/${detailId}`);
  }

  createDetail(ordreId: number, dto: OrdreDetailCreateDTO): Observable<OrdreDetailRead> {
    return this.http.post<OrdreDetailRead>(`${this.apiUrl}/${ordreId}/details`, dto);
  }

  updateDetail(ordreId: number, detailId: number, dto: OrdreDetailUpdateDTO): Observable<OrdreDetailRead> {
    return this.http.put<OrdreDetailRead>(`${this.apiUrl}/${ordreId}/details/${detailId}`, dto);
  }

  deleteDetail(ordreId: number, detailId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${ordreId}/details/${detailId}`);
  }

  /**
   * === Lignes CRUD ===
   */
  getLignes(ordreId: number, detailId: number): Observable<OrdreDetailLigneRead[]> {
    return this.http.get<OrdreDetailLigneRead[]>(
      `${this.apiUrl}/${ordreId}/details/${detailId}/lignes`
    );
  }

  getLigneById(ordreId: number, detailId: number, ligneId: number): Observable<OrdreDetailLigneRead> {
    return this.http.get<OrdreDetailLigneRead>(
      `${this.apiUrl}/${ordreId}/details/${detailId}/lignes/${ligneId}`
    );
  }

  createLigne(ordreId: number, detailId: number, dto: OrdreDetailLigneCreateDTO): Observable<OrdreDetailLigneRead> {
    return this.http.post<OrdreDetailLigneRead>(
      `${this.apiUrl}/${ordreId}/details/${detailId}/lignes`,
      dto
    );
  }

  updateLigne(ordreId: number, detailId: number, ligneId: number, dto: OrdreDetailLigneUpdateDTO): Observable<OrdreDetailLigneRead> {
    return this.http.put<OrdreDetailLigneRead>(
      `${this.apiUrl}/${ordreId}/details/${detailId}/lignes/${ligneId}`,
      dto
    );
  }

  deleteLigne(ordreId: number, detailId: number, ligneId: number): Observable<void> {
    return this.http.delete<void>(
      `${this.apiUrl}/${ordreId}/details/${detailId}/lignes/${ligneId}`
    );
  }

  /**
   * === Produits Détails CRUD ===
   */
  getProduitsDetails(ordreId: number, detailId: number): Observable<ProduitDetailRead[]> {
    return this.http.get<ProduitDetailRead[]>(
      `${this.apiUrl}/${ordreId}/details/${detailId}/produits-details`
    );
  }

  getProduitDetailById(ordreId: number, detailId: number, produitDetailId: number): Observable<ProduitDetailRead> {
    return this.http.get<ProduitDetailRead>(
      `${this.apiUrl}/${ordreId}/details/${detailId}/produits-details/${produitDetailId}`
    );
  }

  createProduitDetail(ordreId: number, detailId: number, dto: ProduitDetailCreateDTO): Observable<ProduitDetailRead> {
    return this.http.post<ProduitDetailRead>(
      `${this.apiUrl}/${ordreId}/details/${detailId}/produits-details`,
      dto
    );
  }

  updateProduitDetail(ordreId: number, detailId: number, produitDetailId: number, dto: ProduitDetailUpdateDTO): Observable<ProduitDetailRead> {
    return this.http.put<ProduitDetailRead>(
      `${this.apiUrl}/${ordreId}/details/${detailId}/produits-details/${produitDetailId}`,
      dto
    );
  }

  deleteProduitDetail(ordreId: number, detailId: number, produitDetailId: number): Observable<void> {
    return this.http.delete<void>(
      `${this.apiUrl}/${ordreId}/details/${detailId}/produits-details/${produitDetailId}`
    );
  }

  /**
   * === Articles Produits CRUD ===
   */
  getArticles(ordreId: number, detailId: number): Observable<ArticleProduitRead[]> {
    return this.http.get<ArticleProduitRead[]>(
      `${this.apiUrl}/${ordreId}/details/${detailId}/articles-produits`
    );
  }

  getArticleById(ordreId: number, detailId: number, articleId: number): Observable<ArticleProduitRead> {
    return this.http.get<ArticleProduitRead>(
      `${this.apiUrl}/${ordreId}/details/${detailId}/articles-produits/${articleId}`
    );
  }

  createArticle(ordreId: number, detailId: number, dto: ArticleProduitCreateDTO): Observable<ArticleProduitRead> {
    return this.http.post<ArticleProduitRead>(
      `${this.apiUrl}/${ordreId}/details/${detailId}/articles-produits`,
      dto
    );
  }

  updateArticle(ordreId: number, detailId: number, articleId: number, dto: ArticleProduitUpdateDTO): Observable<ArticleProduitRead> {
    return this.http.put<ArticleProduitRead>(
      `${this.apiUrl}/${ordreId}/details/${detailId}/articles-produits/${articleId}`,
      dto
    );
  }

  deleteArticle(ordreId: number, detailId: number, articleId: number): Observable<void> {
    return this.http.delete<void>(
      `${this.apiUrl}/${ordreId}/details/${detailId}/articles-produits/${articleId}`
    );
  }
}
