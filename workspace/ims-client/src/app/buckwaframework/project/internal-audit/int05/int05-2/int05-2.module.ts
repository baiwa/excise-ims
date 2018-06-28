import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { Int052Component } from './int05-2.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { Int0521Component } from './int05-2-1/int05-2-1.component';
import { Int0522Component } from './int05-2-2/int05-2-2.component';
import { Int0523Component } from './int05-2-3/int05-2-3.component';
import { Int0524Component } from './int05-2-4/int05-2-4.component';

const routes: Routes = [
    { path: '', component: Int052Component, canActivate: [AuthGuard] },
    { path: '1', component: Int0521Component, canActivate: [AuthGuard] },
    { path: '2', component: Int0522Component, canActivate: [AuthGuard] },
    { path: '3', component: Int0523Component, canActivate: [AuthGuard] },
    { path: '4', component: Int0524Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule
  ],
  declarations: [
    Int052Component,
    Int0521Component,
    Int0522Component,
    Int0523Component,
    Int0524Component
  ],
  exports: [RouterModule]
})
export class Int052Module { }