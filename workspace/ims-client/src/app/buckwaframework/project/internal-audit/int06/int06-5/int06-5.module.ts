import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { Int065Component } from './int06-5.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { Int0651Component } from './int06-5-1/int06-5-1.component';
import { Int0652Component } from './int06-5-2/int06-5-2.component';
import { Int0653Component } from './int06-5-3/int06-5-3.component';
import { Int0654Component } from './int06-5-4/int06-5-4.component';

const routes: Routes = [
    { path: '', component: Int065Component, canActivate: [AuthGuard] },
    { path: '1', component: Int0651Component, canActivate: [AuthGuard] },
    { path: '2', component: Int0652Component, canActivate: [AuthGuard] },
    { path: '3', component: Int0653Component, canActivate: [AuthGuard] },
    { path: '4', component: Int0654Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule
  ],
  declarations: [
    Int065Component,
    Int0651Component,
    Int0652Component,
    Int0653Component,
    Int0654Component
  ],
  exports: [RouterModule]
})
export class Int065Module { }