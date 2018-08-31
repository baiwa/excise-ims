import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { Int082Component } from './int08-2.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { Int0821Component } from './int08-2-1/int08-2-1.component';
import { Int0822Component } from './int08-2-2/int08-2-2.component';
import { FormsModule } from '../../../../../../../node_modules/@angular/forms';
import { Int0823Component } from './int08-2-3/int08-2-3.component';
import { Int0824Component } from './int08-2-4/int08-2-4.component';
import { Int0825Component } from './int08-2-5/int08-2-5.component';
import { Int0826Component } from './int08-2-6/int08-2-6.component';
import { ConditionModule } from '../../../../common/components';

const routes: Routes = [
    { path: '', component: Int082Component, canActivate: [AuthGuard] },
    { path: '1', component: Int0821Component, canActivate: [AuthGuard] },
    { path: '2', component: Int0822Component, canActivate: [AuthGuard] },
    { path: '3', component: Int0823Component, canActivate: [AuthGuard] },
    { path: '4', component: Int0824Component, canActivate: [AuthGuard] },
    { path: '5', component: Int0825Component, canActivate: [AuthGuard] },
    { path: '6', component: Int0826Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    FormsModule,
    ConditionModule
  ],
  declarations: [
    Int082Component,
    Int0821Component,
    Int0822Component,
    Int0823Component,
    Int0824Component,
    Int0825Component,
    Int0826Component
  ],
  exports: [RouterModule]
})
export class Int082Module { }