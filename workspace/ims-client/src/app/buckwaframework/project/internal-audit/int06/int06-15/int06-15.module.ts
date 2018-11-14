import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from 'services/auth-guard.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BreadcrumbModule } from 'components/breadcrumb/breadcrumb.module';
import { Int0615Component } from './int06-15.component';


const routes: Routes = [
  { path: "", component: Int0615Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    BreadcrumbModule
  ],
  declarations: [Int0615Component],
  providers: [Int0615Component]
})
export class Int0615Module { }
