import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { Int0833Component } from './int08-3-3.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../../common/services';
import { Int08331Component } from './int08-3-3-1/int08-3-3-1.component';
import { Int08332Component } from './int08-3-3-2/int08-3-3-2.component';
import { Int08333Component } from './int08-3-3-3/int08-3-3-3.component';

const routes: Routes = [
    { path: '', component: Int0833Component, canActivate: [AuthGuard] },
    { path: '1', component: Int08331Component, canActivate: [AuthGuard] },
    { path: '2', component: Int08332Component, canActivate: [AuthGuard] },
    { path: '3', component: Int08333Component, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule
  ],
  declarations: [
    Int0833Component,
    Int08331Component,
    Int08332Component,
    Int08333Component
  ],
  exports: [RouterModule]
})
export class Int0833Module { }