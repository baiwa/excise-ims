import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Int068Component } from "./int06-8.component";

import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "../../../../common/services";
import { FormsModule } from "@angular/forms";
import { BreadcrumbModule } from "../../../../common/components";

const routes: Routes = [
  { path: "", component: Int068Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    FormsModule,
    BreadcrumbModule
  ],
  declarations: [Int068Component],
  exports: [RouterModule]
})
export class Int068Module {}
