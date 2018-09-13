import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { Int051Component } from './int05-1.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { BreadcrumbComponent } from 'app/buckwaframework/common/components/breadcrumb/breadcrumb.component';


const routes: Routes = [
    { path: '', component: Int051Component, canActivate: [AuthGuard] },
    { path: '1', loadChildren: './int05-1-1/int05-1-1.module#Int0511Module', canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule,  
    FormsModule  
  ],
  declarations: [
    Int051Component,
    BreadcrumbComponent
    
  ],
  exports: [RouterModule]
})
export class Int051Module { }