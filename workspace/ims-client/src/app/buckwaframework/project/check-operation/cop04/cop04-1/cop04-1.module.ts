import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { Cop041Component } from './cop04-1.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { Cop0411Component } from './cop04-1-1/cop04-1-1.component';

const routes: Routes = [
    { path: '', component: Cop041Component, canActivate: [AuthGuard] },
    { path: '1', component: Cop0411Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule
  ],
  declarations: [
    Cop041Component,
    Cop0411Component
  ],
  exports: [RouterModule]
})
export class Cop041Module { }