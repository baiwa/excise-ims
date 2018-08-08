import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { Int081Component } from './int08-1.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { Int0811Component } from './int08-1-1/int08-1-1.component';
import { Int0812Component } from './int08-1-2/int08-1-2.component';
import { Int0813Component } from './int08-1-3/int08-1-3.component';
import { Int0814Component } from './int08-1-4/int08-1-4.component';
import { FormsModule } from '../../../../../../../node_modules/@angular/forms';

const routes: Routes = [
  { path: '', component: Int081Component, canActivate: [AuthGuard] },
  { path: '1', component: Int0811Component, canActivate: [AuthGuard] },
  { path: '2', component: Int0812Component, canActivate: [AuthGuard] },
  { path: '3', component: Int0813Component, canActivate: [AuthGuard] },
  { path: '4', component: Int0814Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    FormsModule
  ],
  declarations: [
    Int081Component,
    Int0811Component,
    Int0812Component,
    Int0813Component,
    Int0814Component
  ],
  exports: [RouterModule]
})
export class Int081Module { }