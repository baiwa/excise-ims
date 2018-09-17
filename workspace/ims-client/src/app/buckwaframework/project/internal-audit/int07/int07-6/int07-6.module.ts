import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Int076Component } from './int07-6.component';
import { Int0761Component } from './int07-6-1/int07-6-1.component';

import { AuthGuard } from '../../../../common/services';
import { Routes, RouterModule } from "@angular/router";
import { FormsModule } from "@angular/forms";
import { BreadcrumbModule } from '../../../../common/components';

const routes: Routes = [
  { path: '', component: Int076Component, canActivate: [AuthGuard] },
  { path: '1', component: Int0761Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    FormsModule,
    CommonModule,
    BreadcrumbModule
  ],
  declarations: [
    Int076Component, 
    Int0761Component
  ],
    exports: [RouterModule]
})
export class Int076Module { }
