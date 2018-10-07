import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { Int0611Component } from './int06-1-1/int06-1-1.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BreadcrumbModule } from '../../../../common/components';
import { Int0621Component } from './int06-2-1/int06-2-1.component';
import { Int0622Component } from './int06-2-2/int06-2-2.component';
import { Int0612Component } from './int06-1-2/int06-1-2.component';
import { Int0613Component } from './int06-1-3/int06-1-3.component';
import { Int061Service } from 'projects/internal-audit/int06/int06-1/int06-1.service';

const routes: Routes = [
  { path: '1', component: Int0611Component, canActivate: [AuthGuard] },
  { path: '1-2', component: Int0612Component, canActivate: [AuthGuard] },
  { path: '1-3', component: Int0613Component, canActivate: [AuthGuard] },
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
    Int0612Component,
    Int0613Component,    
  ],
  providers:[
    Int061Service
  ],
  exports: [RouterModule]
})
export class Int061Module { }