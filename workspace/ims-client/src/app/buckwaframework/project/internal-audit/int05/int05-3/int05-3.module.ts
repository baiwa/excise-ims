import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { Int053Component } from './int05-3.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { Int0531Component } from './int05-3-1/int05-3-1.component';

const routes: Routes = [
  { path: '', component: Int053Component, canActivate: [AuthGuard] },
  { path: '1', component: Int0531Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule
  ],
  declarations: [
    Int053Component,
    Int0531Component
  ],
  exports: [RouterModule]
})
export class Int053Module { }