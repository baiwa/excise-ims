import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from 'services/auth-guard.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BreadcrumbModule } from 'components/breadcrumb/breadcrumb.module';
import { Int0614Component } from './int06-14.component';
import { DirectivesModule } from 'app/buckwaframework/common/directives/directives.module';


const routes: Routes = [
  { path: "", component: Int0614Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    BreadcrumbModule,
    DirectivesModule
  ],
  declarations: [Int0614Component],
  providers: [Int0614Component]
})
export class Int0614Module { }
