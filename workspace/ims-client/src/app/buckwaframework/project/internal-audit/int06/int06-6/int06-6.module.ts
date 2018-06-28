import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { Int066Component } from './int06-6.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { Int0661Component } from './int06-6-1/int06-6-1.component';
import { Int0662Component } from './int06-6-2/int06-6-2.component';
import { Int0663Component } from './int06-6-3/int06-6-3.component';
import { Int0664Component } from './int06-6-4/int06-6-4.component';

const routes: Routes = [
  { path: '', component: Int066Component, canActivate: [AuthGuard] },
  { path: '1', component: Int0661Component, canActivate: [AuthGuard] },
  { path: '2', component: Int0662Component, canActivate: [AuthGuard] },
  { path: '3', component: Int0663Component, canActivate: [AuthGuard] },
  { path: '4', component: Int0664Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule
  ],
  declarations: [
    Int066Component,
    Int0661Component,
    Int0662Component,
    Int0663Component,
    Int0664Component
  ],
  exports: [RouterModule]
})
export class Int066Module { }