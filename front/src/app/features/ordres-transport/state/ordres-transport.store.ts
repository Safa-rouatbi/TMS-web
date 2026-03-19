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

  private readonly stateSubject = new BehaviorSubject<OrdresTransportState>(this.initialState);
  public readonly state$: Observable<OrdresTransportState> = this.stateSubject.asObservable();

  // Sélecteurs pour les besoins courants
  get ordres$(): Observable<OrdreTransportRead[]> {
    return new Observable(observer => {
      this.state$.subscribe(state => observer.next(state.ordres));
    });
  }

  get selectedOrdre$(): Observable<OrdreTransportRead | null> {
    return new Observable(observer => {
      this.state$.subscribe(state => observer.next(state.selectedOrdre));
    });
  }

  get isLoading$(): Observable<boolean> {
    return new Observable(observer => {
      this.state$.subscribe(state => observer.next(state.isLoading));
    });
  }

  get error$(): Observable<string | null> {
    return new Observable(observer => {
      this.state$.subscribe(state => observer.next(state.error));
    });
  }

  get currentState(): OrdresTransportState {
    return this.stateSubject.getValue();
  }

  /**
   * Mise à jour du state
   */
  setState(partialState: Partial<OrdresTransportState>): void {
    const currentState = this.currentState;
    this.stateSubject.next({
      ...currentState,
      ...partialState
    });
  }

  setOrdres(ordres: OrdreTransportRead[]): void {
    this.setState({ ordres });
  }

  setSelectedOrdre(ordreRead: OrdreTransportRead | null): void {
    this.setState({ selectedOrdre: ordreRead });
  }

  setLoading(isLoading: boolean): void {
    this.setState({ isLoading });
  }

  setError(error: string | null): void {
    this.setState({ error });
  }

  setFilter(filter: string): void {
    this.setState({ filter });
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
    this.stateSubject.next(this.initialState);
  }
}
