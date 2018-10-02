import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";


import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { Int0771Component } from './int07-7-1/int07-7-1.component';
import { Int0772Component } from './int07-7-2/int07-7-2.component';
import { Int077Component } from './int07-7.component';


const routes: Routes = [
  { path: '', component: Int077Component, canActivate: [AuthGuard] },
  { path: '1', component: Int0771Component, canActivate: [AuthGuard] },
  { path: '2', component: Int0772Component, canActivate: [AuthGuard] },
 
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule
  ],
  declarations: [   
    Int077Component,
    Int0771Component,
    Int0772Component
   
  ],
  exports: [RouterModule]
})
export class Int077Module { }