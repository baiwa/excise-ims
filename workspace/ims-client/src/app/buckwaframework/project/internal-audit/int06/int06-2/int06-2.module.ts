import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { Int062Component } from './int06-2.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { Int0621Component } from './int06-2-1/int06-2-1.component';

const routes: Routes = [
  { path: '', component: Int062Component, canActivate: [AuthGuard] },
  { path: '1', component: Int0621Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule
  ],
  declarations: [
    Int062Component,
    Int0621Component
  ],
  exports: [RouterModule]
})
export class Int062Module { }