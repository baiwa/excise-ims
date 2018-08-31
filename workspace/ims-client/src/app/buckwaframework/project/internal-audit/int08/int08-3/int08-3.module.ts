import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { Int083Component } from './int08-3.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { Int0831Component } from './int08-3-1/int08-3-1.component';
import { Int0832Component } from './int08-3-2/int08-3-2.component';
import { Int0833Component } from './int08-3-3/int08-3-3.component';
import { Int0834Component } from './int08-3-4/int08-3-4.component';
import { Int0835Component } from './int08-3-5/int08-3-5.component';
import { Int0836Component } from './int08-3-6/int08-3-6.component';
import { FormsModule } from '../../../../../../../node_modules/@angular/forms';
import { ConditionModule } from '../../../../common/components';
const routes: Routes = [
  { path: '', component: Int083Component, canActivate: [AuthGuard] },
  { path: '1', component: Int0831Component, canActivate: [AuthGuard] },
  { path: '2', component: Int0832Component, canActivate: [AuthGuard] },
  { path: '3', component: Int0833Component, canActivate: [AuthGuard] },
  { path: '4', component: Int0834Component, canActivate: [AuthGuard] },
  { path: '5', component: Int0835Component, canActivate: [AuthGuard] },
  { path: '6', component: Int0836Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    FormsModule,
    ConditionModule
  ],
  declarations: [
    Int083Component,
    Int0831Component,
    Int0832Component,
    Int0833Component,
    Int0834Component,
    Int0835Component,
    Int0836Component
  ],
  exports: [RouterModule]
})
export class Int083Module { }