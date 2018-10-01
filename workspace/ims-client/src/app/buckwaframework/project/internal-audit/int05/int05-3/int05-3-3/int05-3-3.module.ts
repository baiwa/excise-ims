import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../../common/services';
import { Int0533Component } from './int05-3-3.component';
import { Int05331Component } from './int05-3-3-1/int05-3-3-1.component';
import { Int05332Component } from './int05-3-3-2/int05-3-3-2.component';

const routes: Routes = [
  { path: '', component: Int0533Component, canActivate: [AuthGuard] },
  { path: '1', component: Int05331Component, canActivate: [AuthGuard] },
  { path: '2', component: Int05332Component, canActivate: [AuthGuard] }

];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    FormsModule,
    CommonModule
  ],
  declarations: [

    Int0533Component,
    Int05331Component,
    Int05332Component
  ],
  exports: [RouterModule]
})
export class Int0533Module { }