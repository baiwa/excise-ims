import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { Int063Component } from './int06-3.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { Int0631Component } from './int06-3-1/int06-3-1.component';
import { Int0632Component } from './int06-3-2/int06-3-2.component';
import { Int0633Component } from './int06-3-3/int06-3-3.component';
import { Int0634Component } from './int06-3-4/int06-3-4.component';

const routes: Routes = [
    { path: '', component: Int063Component, canActivate: [AuthGuard] },
    { path: '1', component: Int0631Component, canActivate: [AuthGuard] },
    { path: '2', component: Int0632Component, canActivate: [AuthGuard] },
    { path: '3', component: Int0633Component, canActivate: [AuthGuard] },
    { path: '4', component: Int0634Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule
  ],
  declarations: [
    Int063Component,
    Int0631Component,
    Int0632Component,
    Int0633Component,
    Int0634Component
  ],
  exports: [RouterModule]
})
export class Int063Module { }