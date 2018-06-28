import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { Cop042Component } from './cop04-2.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { Cop0421Component } from './cop04-2-1/cop04-2-1.component';

const routes: Routes = [
    { path: '', component: Cop042Component, canActivate: [AuthGuard] },
    { path: '1', component: Cop0421Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule
  ],
  declarations: [
    Cop042Component,
    Cop0421Component
  ],
  exports: [RouterModule]
})
export class Cop042Module { }