import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "services/auth-guard.service";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { BreadcrumbModule } from "components/breadcrumb/breadcrumb.module";
import { Int06117Component } from "./int06-11-7.component";

const routes: Routes = [
  { path: "", component: Int06117Component, canActivate: [AuthGuard] }
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
    Int06117Component
  ],
  exports: [RouterModule]
})
export class Int061107Module {}
