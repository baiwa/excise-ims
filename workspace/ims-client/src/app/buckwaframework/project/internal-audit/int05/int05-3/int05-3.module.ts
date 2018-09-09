import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { Int053Component } from './int05-3.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { Int0531Component } from './int05-3-1/int05-3-1.component';
import { Int0532Component } from './int05-3-2/int05-3-2.component';
import { Int05321Component } from './int05-3-2/int05-3-2-1/int05-3-2-1.component';
import { FormsModule } from '@angular/forms';

const routes: Routes = [
  { path: '', component: Int053Component, canActivate: [AuthGuard] },
  { path: '1', component: Int0531Component, canActivate: [AuthGuard] },
  { path: '2', component: Int0532Component, canActivate: [AuthGuard] },
  { path: '2/1', component: Int05321Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    FormsModule,
    CommonModule
  ],
  declarations: [
    Int053Component,
    Int0531Component,
    Int0532Component,
    Int05321Component
  ],
  exports: [RouterModule]
})
export class Int053Module { }