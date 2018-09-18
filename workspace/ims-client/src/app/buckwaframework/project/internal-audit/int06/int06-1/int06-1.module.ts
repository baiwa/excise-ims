import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { Int061Component } from './int06-1.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { Int0611Component } from './int06-1-1/int06-1-1.component';
import { BreadcrumbComponent } from '../../../../common/components/breadcrumb/breadcrumb.component';
import { FormsModule } from '@angular/forms';
import { BreadcrumbModule } from '../../../../common/components';

const routes: Routes = [
  { path: '', component: Int061Component, canActivate: [AuthGuard] },
  { path: '1', component: Int0611Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    FormsModule,
    BreadcrumbModule    
  ],
  declarations: [
    Int061Component,
    Int0611Component,    
  ],
  exports: [RouterModule]
})
export class Int061Module { }