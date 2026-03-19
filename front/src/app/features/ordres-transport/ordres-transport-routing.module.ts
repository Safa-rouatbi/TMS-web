import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { OrdresListPageComponent } from './pages/ordres-list-page/ordres-list-page.component';
import { OrdresFormPageComponent } from './pages/ordres-form-page/ordres-form-page.component';
import { OrdreDetailPageComponent } from './pages/ordre-detail-page/ordre-detail-page.component';

const routes: Routes = [
  {
    path: '',
    component: OrdresListPageComponent,
    data: {
      title: 'Ordres de Transport'
    }
  },
  {
    path: 'new',
    component: OrdresFormPageComponent,
    data: {
      title: 'Créer un Ordre'
    }
  },
  {
    path: ':id',
    component: OrdreDetailPageComponent,
    data: {
      title: 'Détails Ordre'
    }
  },
  {
    path: ':id/edit',
    component: OrdresFormPageComponent,
    data: {
      title: 'Éditer Ordre'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OrdresTransportRoutingModule { }
