import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { Int0171Component } from './int01-7-1/int01-7-1.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';

const routes: Routes = [
    { path: '', component: Int0171Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule
  ],
  declarations: [
    Int0171Component
  ],
  exports: [RouterModule]
})
export class Int017Module { }