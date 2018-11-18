import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthGuard } from 'services/auth-guard.service';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BreadcrumbModule } from 'components/breadcrumb/breadcrumb.module';
import { DirectivesModule } from 'app/buckwaframework/common/directives/directives.module';
import { Int0616Component } from './int06-16.component';


const routes: Routes = [
  { path: "", component: Int0616Component, canActivate: [AuthGuard] }
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
  declarations: [Int0616Component],
  providers: [Int0616Component]
})
export class Int0616Module { }
