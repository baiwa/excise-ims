import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";


import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';



import { Int0612Component } from 'projects/internal-audit/int06/int06-12/int06-12.component';
import { Int06121Component } from './int06-12-1/int06-12-1.component';
import { Int06122Component } from './int06-12-2/int06-12-2.component';



const routes: Routes = [
    { path: '', component: Int0612Component, canActivate: [AuthGuard] },
    { path: '1', component: Int06121Component, canActivate: [AuthGuard] },
    { path: '2', component: Int06122Component, canActivate: [AuthGuard] },

];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule
  ],
  declarations: [
    Int0612Component,
    Int06121Component,
    Int06122Component
   
   
  ],
  exports: [RouterModule]
})
export class Int0612Module { }