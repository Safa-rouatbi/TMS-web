import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import {
  ButtonModule,
  CardModule,
  FormModule,
  GridModule,
  AlertModule,
  BadgeModule,
  TableModule,
  UtilitiesModule
} from '@coreui/angular';
import { IconModule } from '@coreui/icons-angular';

import { OrdresTransportRoutingModule } from './ordres-transport-routing.module';

import { OrdresListPageComponent } from './pages/ordres-list-page/ordres-list-page.component';
import { OrdresFormPageComponent } from './pages/ordres-form-page/ordres-form-page.component';
import { OrdreDetailPageComponent } from './pages/ordre-detail-page/ordre-detail-page.component';

@NgModule({
  declarations: [
    OrdresListPageComponent,
    OrdresFormPageComponent,
    OrdreDetailPageComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    OrdresTransportRoutingModule,
    // CoreUI Modules
    ButtonModule,
    CardModule,
    FormModule,
    GridModule,
    AlertModule,
    BadgeModule,
    TableModule,
    UtilitiesModule,
    IconModule
  ]
})
export class OrdresTransportModule { }
