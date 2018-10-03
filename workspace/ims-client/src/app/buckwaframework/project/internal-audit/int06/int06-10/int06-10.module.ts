import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";


import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';



import { Int0610Component } from 'projects/internal-audit/int06/int06-10/int06-10.component';
import { Int06101Component } from './int06-10-1/int06-10-1.component';
import { Int06102Component } from './int06-10-2/int06-10-2.component';


const routes: Routes = [
    { path: '', component: Int0610Component, canActivate: [AuthGuard] },
    { path: '1', component: Int06101Component, canActivate: [AuthGuard] },
    { path: '2', component: Int06102Component, canActivate: [AuthGuard] },
   
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule
  ],
  declarations: [
    Int0610Component,
    Int06101Component,
    Int06102Component,
  
  ],
  exports: [RouterModule]
})
export class Int0610Module { }