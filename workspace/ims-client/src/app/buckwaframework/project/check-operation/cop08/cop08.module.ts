import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common"

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../common/services';
import { Cop08Component } from 'projects/check-operation/cop08/cop08.component';
import { Cop081Component } from 'projects/check-operation/cop08/cop08-1/cop08-1.component';
import { FormsModule } from '@angular/forms';
import { ModalModule, BreadcrumbModule } from '../../../common/components';

const routes: Routes = [
    { path: '', component: Cop08Component, canActivate: [AuthGuard] },
    { path: '1', component: Cop081Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes), 
    CommonModule, 
    FormsModule, 
    ModalModule,
    BreadcrumbModule],
  declarations: [Cop08Component,Cop081Component],
  exports: [RouterModule]
})
export class Cop08Module { }