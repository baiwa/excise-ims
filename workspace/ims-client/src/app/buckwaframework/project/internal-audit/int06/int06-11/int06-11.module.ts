import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";


import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';



import { Int0611Component } from 'projects/internal-audit/int06/int06-11/int06-11.component';
import { Int06111Component } from './int06-11-1/int06-11-1.component';
import { Int06112Component } from './int06-11-2/int06-11-2.component';
import { Int06113Component } from './int06-11-3/int06-11-3.component';
import { Int06114Component } from './int06-11-4/int06-11-4.component';


const routes: Routes = [
    { path: '', component: Int0611Component, canActivate: [AuthGuard] },
    { path: '1', component: Int06111Component, canActivate: [AuthGuard] },
    { path: '2', component: Int06112Component, canActivate: [AuthGuard] },
    { path: '3', component: Int06113Component, canActivate: [AuthGuard] },
    { path: '4', component: Int06114Component, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule
  ],
  declarations: [
    Int0611Component,
    Int06111Component,
    Int06112Component,
    Int06113Component,
    Int06114Component,
   
  ],
  exports: [RouterModule]
})
export class Int0611Module { }