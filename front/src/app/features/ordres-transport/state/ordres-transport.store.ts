import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import {
  OrdresTransportState,
  OrdreTransportRead
} from '../models/ordres-transport.model';

@Injectable({
  providedIn: 'root'
})
export class OrdresTransportStore {
  private readonly initialState: OrdresTransportState = {
    ordres: [],
    selectedOrdre: null,
    isLoading: false,
    error: null,
    filter: ''
  };

  private state: OrdresTransportState = { ...this.initialState };

  private readonly stateSubject = new BehaviorSubject<OrdresTransportState>(this.state);
  private readonly ordresSubject = new BehaviorSubject<OrdreTransportRead[]>(this.state.ordres);
  private readonly selectedOrdreSubject = new BehaviorSubject<OrdreTransportRead | null>(this.state.selectedOrdre);
  private readonly isLoadingSubject = new BehaviorSubject<boolean>(this.state.isLoading);
  private readonly errorSubject = new BehaviorSubject<string | null>(this.state.error);
  private readonly filterSubject = new BehaviorSubject<string>(this.state.filter);

  private emitAll(): void {
    this.stateSubject.next(this.state);
    this.ordresSubject.next(this.state.ordres);
    this.selectedOrdreSubject.next(this.state.selectedOrdre);
    this.isLoadingSubject.next(this.state.isLoading);
    this.errorSubject.next(this.state.error);
    this.filterSubject.next(this.state.filter);
  }

  public readonly state$: Observable<OrdresTransportState> = this.stateSubject.asObservable();
  public readonly ordres$: Observable<OrdreTransportRead[]> = this.ordresSubject.asObservable();
  public readonly selectedOrdre$: Observable<OrdreTransportRead | null> = this.selectedOrdreSubject.asObservable();
  public readonly isLoading$: Observable<boolean> = this.isLoadingSubject.asObservable();
  public readonly error$: Observable<string | null> = this.errorSubject.asObservable();
  public readonly filter$: Observable<string> = this.filterSubject.asObservable();

  get currentState(): OrdresTransportState {
    return this.state;
  }

  /**
   * Mise à jour du state
   */
  setState(partialState: Partial<OrdresTransportState>): void {
    this.state = {
      ...this.state,
      ...partialState
    };
    this.emitAll();
  }

  setOrdres(ordres: OrdreTransportRead[]): void {
    this.state = {
      ...this.state,
      ordres
    };
    this.emitAll();
  }

  setSelectedOrdre(ordreRead: OrdreTransportRead | null): void {
    this.state = {
      ...this.state,
      selectedOrdre: ordreRead
    };
    this.emitAll();
  }

  setLoading(isLoading: boolean): void {
    this.state = {
      ...this.state,
      isLoading
    };
    this.emitAll();
  }

  setError(error: string | null): void {
    this.state = {
      ...this.state,
      error
    };
    this.emitAll();
  }

  setFilter(filter: string): void {
    this.state = {
      ...this.state,
      filter
    };
    this.emitAll();
  }

  addOrdre(ordre: OrdreTransportRead): void {
    const currentOrdres = this.currentState.ordres;
    this.setOrdres([...currentOrdres, ordre]);
  }

  updateOrdre(ordre: OrdreTransportRead): void {
    const updated = this.currentState.ordres.map(o => o.id === ordre.id ? ordre : o);
    this.setOrdres(updated);
  }

  removeOrdre(ordreId: number): void {
    const filtered = this.currentState.ordres.filter(o => o.id !== ordreId);
    this.setOrdres(filtered);
  }

  reset(): void {
    this.state = {
      ...this.initialState,
      ordres: [...this.initialState.ordres]
    };
    this.emitAll();
  }
}
