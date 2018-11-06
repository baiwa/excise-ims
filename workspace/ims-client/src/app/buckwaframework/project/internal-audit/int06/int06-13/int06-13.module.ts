import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Int0613Component } from './int06-13.component';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from 'services/auth-guard.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BreadcrumbModule } from 'components/breadcrumb/breadcrumb.module';
import { Int0613Service } from './int06-13.service';


const routes: Routes = [
  { path: "", component: Int0613Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    BreadcrumbModule
  ],
  declarations: [Int0613Component],
  providers: [Int0613Service]
})
export class Int0613Module { }
