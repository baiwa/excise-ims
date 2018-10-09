import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { Int0511Component } from './int05-1-1.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../../common/services';
import { Int05111Component } from './int05-1-1-1/int05-1-1-1.component';
import { Int05112Component } from './int05-1-1-2/int05-1-1-2.component';
import { BreadcrumbModule } from '../../../../../common/components';

const routes: Routes = [
    { path: '',component: Int0511Component,canActivate: [AuthGuard] },
    { path: '1', component: Int05111Component, canActivate: [AuthGuard] },
    { path: '2', component: Int05112Component, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    FormsModule,
    BreadcrumbModule    
  ],
  declarations: [
    Int0511Component,
    Int05111Component,
    Int05112Component,
  ],
  exports: [RouterModule]
})
export class Int0511Module { }