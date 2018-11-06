import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Int0613Component } from './int06-13.component';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from 'services/auth-guard.service';
import { FormsModule } from '@angular/forms';
import { BreadcrumbModule } from 'components/breadcrumb/breadcrumb.module';


const routes: Routes = [
  { path: "", component: Int0613Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    FormsModule,
    BreadcrumbModule
  ],
  declarations: [Int0613Component]
})
export class Int0613Module { }
