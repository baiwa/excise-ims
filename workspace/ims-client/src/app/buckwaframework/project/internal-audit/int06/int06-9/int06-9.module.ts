import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";


import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';

import { Int069Component } from './int06-9.component';
import { Int0691Component } from 'projects/internal-audit/int06/int06-9/int06-9-1/int06-9-1.component';
import { Int0692Component } from './int06-9-2/int06-9-2.component';

const routes: Routes = [
    { path: '', component: Int069Component, canActivate: [AuthGuard] },
    { path: '1', component: Int0691Component, canActivate: [AuthGuard] },
    { path: '2', component: Int0692Component, canActivate: [AuthGuard] },
    
   
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule
  ],
  declarations: [
    Int069Component,
    Int0691Component,
    Int0692Component
  ],
  exports: [RouterModule]
})
export class Int069Module { }