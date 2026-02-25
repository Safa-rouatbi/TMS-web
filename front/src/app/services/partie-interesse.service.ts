import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { PartieInteresseCreateDTO, PartieInteresseReadDTO, PartieInteresseUpdateDTO } from '../models/partie-interesse.model';

@Injectable({
  providedIn: 'root'
})
export class PartieInteresseService {
  private readonly apiUrl = `${environment.apiUrl}/api/parties`;

  constructor(private http: HttpClient) { }


  create(partie: PartieInteresseCreateDTO): Observable<PartieInteresseReadDTO> {
    return this.http.post<PartieInteresseReadDTO>(this.apiUrl, partie);
  }


  getAll(type?: string): Observable<PartieInteresseReadDTO[]> {
    let params = new HttpParams();
    if (type) {
      params = params.set('type', type);
    }
    return this.http.get<PartieInteresseReadDTO[]>(this.apiUrl, { params });
  }


  getById(id: number): Observable<PartieInteresseReadDTO> {
    return this.http.get<PartieInteresseReadDTO>(`${this.apiUrl}/${id}`);
  }


  update(id: number, partie: PartieInteresseUpdateDTO): Observable<PartieInteresseReadDTO> {
    return this.http.put<PartieInteresseReadDTO>(`${this.apiUrl}/${id}`, partie);
  }


  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
