import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { Int0611Component } from './int06-1-1/int06-1-1.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BreadcrumbModule } from '../../../../common/components';
import { Int0621Component } from './int06-2-1/int06-2-1.component';
import { Int0622Component } from './int06-2-2/int06-2-2.component';

const routes: Routes = [
  { path: '1', component: Int0611Component, canActivate: [AuthGuard] },
  { path: '2-1', component: Int0621Component, canActivate: [AuthGuard] },
  { path: '2-2', component: Int0622Component, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    BreadcrumbModule    
  ],
  declarations: [
    Int0611Component,
    Int0621Component,
    Int0622Component,    
  ],
  exports: [RouterModule]
})
export class Int061Module { }