import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { Int0610Component } from './int06-10.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard, CanDeactivateGuard } from '../../../../common/services';
import { Int06101Component } from './int06-10-1/int06-10-1.component';
import { Int06102Component } from './int06-10-2/int06-10-2.component';
import { BreadcrumbModule } from '../../../../common/components';
import { DirectivesModule } from 'app/buckwaframework/common/directives/directives.module';


const routes: Routes = [
  { path: '', component: Int0610Component, canActivate: [AuthGuard] },
  { path: '1', component: Int06101Component, canActivate: [AuthGuard], canDeactivate: [CanDeactivateGuard] },
  { path: '2', component: Int06102Component, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    FormsModule,
    BreadcrumbModule,
    ReactiveFormsModule,
    DirectivesModule
  ],
  declarations: [
    Int0610Component,
    Int06101Component,
    Int06102Component,

  ],
  exports: [RouterModule]
})
export class Int0610Module { }