import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from '../../../common/services';
import { Epa011Component } from './epa01-1/epa01-1.component';
import { Epa012Component } from './epa01-2/epa01-2.component';
import { Epa013Component } from './epa01-3/epa01-3.component';

const routes: Routes = [
  { path: "1", component: Epa011Component, canActivate: [AuthGuard] },
  { path: "2", component: Epa012Component, canActivate: [AuthGuard] },
  { path: "3", component: Epa013Component, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [
    RouterModule.forChild(routes), 
    CommonModule,
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [
    Epa011Component,
    Epa012Component,
    Epa013Component
  ],
  exports: [
    RouterModule
  ]
})
export class Epa01Module {}
