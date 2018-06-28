import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { Int064Component } from './int06-4.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { Int0641Component } from './int06-4-1/int06-4-1.component';
import { Int0642Component } from './int06-4-2/int06-4-2.component';
import { Int0643Component } from './int06-4-3/int06-4-3.component';
import { Int0644Component } from './int06-4-4/int06-4-4.component';

const routes: Routes = [
    { path: '', component: Int064Component, canActivate: [AuthGuard] },
    { path: '1', component: Int0641Component, canActivate: [AuthGuard] },
    { path: '2', component: Int0642Component, canActivate: [AuthGuard] },
    { path: '3', component: Int0643Component, canActivate: [AuthGuard] },
    { path: '4', component: Int0644Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule
  ],
  declarations: [
    Int064Component,
    Int0641Component,
    Int0642Component,
    Int0643Component,
    Int0644Component
  ],
  exports: [RouterModule]
})
export class Int064Module { }