import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../common/services';
import { Cop031Component } from './cop03-1/cop03-1.component';
import { Cop0311Component } from './cop03-1/cop03-1-1/cop03-1-1.component';
import { Cop03111Component } from './cop03-1/cop03-1-1/cop03-1-1-1/cop03-1-1-1.component';

const routes: Routes = [
    { path: '1', component: Cop031Component, canActivate: [AuthGuard] },
    { path: '1/1', component: Cop0311Component, canActivate: [AuthGuard] },
    { path: '1/1/1', component: Cop03111Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule
  ],
  declarations: [
    Cop031Component,
    Cop0311Component,
    Cop03111Component
  ],
  exports: [RouterModule]
})
export class Cop03Module { }