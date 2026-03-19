import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { of, Subject } from 'rxjs';
import { takeUntil, switchMap } from 'rxjs/operators';
import { OrdresTransportFacade } from '../../state/ordres-transport.facade';
import {
  OrdreTransportCreateDTO,
  OrdreTransportRead,
  OrdreTransportUpdateDTO
} from '../../models/ordres-transport.model';

@Component({
  selector: 'app-ordres-form-page',
  templateUrl: './ordres-form-page.component.html',
  styleUrls: ['./ordres-form-page.component.scss']
})
export class OrdresFormPageComponent implements OnInit, OnDestroy {
  form!: FormGroup;
  isLoading$;
  error$;
  step = 1;
  readonly maxStep = 3;

  ordreId: number | null = null;
  private destroy$ = new Subject<void>();

  constructor(
    private fb: FormBuilder,
    private facade: OrdresTransportFacade,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.isLoading$ = this.facade.isLoading$;
    this.error$ = this.facade.error$;
  }

  ngOnInit(): void {
    this.initForm();
    this.route.params
      .pipe(
        switchMap(params => {
          this.ordreId = params['id'] ? parseInt(params['id'], 10) : null;
          if (this.ordreId) {
            return this.facade.loadOrdreById(this.ordreId);
          }
          return of(null);
        }),
        takeUntil(this.destroy$)
      )
      .subscribe({
        next: (ordre: OrdreTransportRead | null) => {
          if (ordre) {
            this.form.patchValue({
              reference: ordre.reference,
              refexterne: ordre.refexterne,
              refCommande: ordre.refCommande,
              date: this.toDateInput(ordre.date),
              observations: ordre.observations,
              natureLivraison: ordre.natureLivraison,
              partieIntId: ordre.partieIntId,
              etat: ordre.etat,
              transporteur: ordre.transporteur,
              modepaiementId: ordre.modepaiementId,
              marcheId: ordre.marcheId,
              reglementId: ordre.reglementId,
              idDepot: ordre.idDepot,
              idCamion: ordre.idCamion,
              idRemorque: ordre.idRemorque,
              idChauffeur: ordre.idChauffeur,
              camion: ordre.camion,
              chauffeur: ordre.chauffeur,
              remorque: ordre.remorque,
              boutiqueId: ordre.boutiqueId,
              devise: ordre.devise,
              tauxConversion: ordre.tauxConversion,
              referenceBlManuel: ordre.referenceBlManuel,
              numCommandeClient: ordre.numCommandeClient,
              numTelPassager: ordre.numTelPassager,
              emailPassager: ordre.emailPassager,
              adressePassager: ordre.adressePassager,
              userName: ordre.userName,
              tourneeId: ordre.tourneeId
            });
          }
        },
        error: (err) => console.error('Erreur chargement ordre:', err)
      });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  private initForm(): void {
    this.form = this.fb.group({
      reference: [''],
      refexterne: [''],
      refCommande: [''],
      date: ['', Validators.required],
      observations: [''],
      natureLivraison: [''],
      partieIntId: [null],
      etat: [''],
      transporteur: [''],
      modepaiementId: [null],
      marcheId: [null],
      reglementId: [null],
      idDepot: [null],
      idCamion: [null],
      idRemorque: [null],
      idChauffeur: [null],
      camion: [''],
      chauffeur: [''],
      remorque: [''],
      boutiqueId: [null],
      devise: [null],
      tauxConversion: [null],
      referenceBlManuel: [''],
      numCommandeClient: [''],
      numTelPassager: [''],
      emailPassager: [''],
      adressePassager: [''],
      userName: [''],
      tourneeId: [null]
    });
  }

  nextStep(): void {
    if (this.step < this.maxStep) {
      this.step += 1;
    }
  }

  previousStep(): void {
    if (this.step > 1) {
      this.step -= 1;
    }
  }

  canGoToStep(target: number): boolean {
    return target <= this.step;
  }

  onSave(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const payload = this.form.value;

    if (this.ordreId) {
      const dto: OrdreTransportUpdateDTO = payload;
      this.facade.updateOrdre(this.ordreId, dto)
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: () => {
            this.router.navigate(['/ordres-transport']);
          },
          error: (err) => console.error('Erreur mise à jour:', err)
        });
    } else {
      const dto: OrdreTransportCreateDTO = payload;
      this.facade.createOrdre(dto)
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: () => {
            this.router.navigate(['/ordres-transport']);
          },
          error: (err) => console.error('Erreur création:', err)
        });
    }
  }

  onCancel(): void {
    this.router.navigate(['/ordres-transport']);
  }

  private toDateInput(value?: string): string | null {
    if (!value) {
      return null;
    }
    const parsed = new Date(value);
    if (Number.isNaN(parsed.getTime())) {
      return null;
    }
    return parsed.toISOString().slice(0, 10);
  }
}
