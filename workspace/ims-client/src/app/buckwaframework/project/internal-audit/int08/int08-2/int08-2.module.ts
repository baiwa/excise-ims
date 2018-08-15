import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { Int082Component } from './int08-2.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { Int0821Component } from './int08-2-1/int08-2-1.component';
import { Int0822Component } from './int08-2-2/int08-2-2.component';
import { FormsModule } from '../../../../../../../node_modules/@angular/forms';
import { Int0823Component } from './int08-2-3/int08-2-3.component';

const routes: Routes = [
    { path: '', component: Int082Component, canActivate: [AuthGuard] },
    { path: '1', component: Int0821Component, canActivate: [AuthGuard] },
    { path: '2', component: Int0822Component, canActivate: [AuthGuard] },
    { path: '3', component: Int0823Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    FormsModule
  ],
  declarations: [
    Int082Component,
    Int0821Component,
    Int0822Component,
    Int0823Component
  ],
  exports: [RouterModule]
})
export class Int082Module { }