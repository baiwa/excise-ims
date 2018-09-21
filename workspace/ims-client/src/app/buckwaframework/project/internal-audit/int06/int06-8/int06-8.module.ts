import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Int068Component } from "./../int06-8/int06-8.component";

import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "../../../../common/services";

const routes: Routes = [
  { path: "", component: Int068Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule],
  declarations: [Int068Component],
  exports: [RouterModule]
})
export class Int068Module {}
