import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Int0151Component } from "./int01-5-1/int01-5-1.component";

import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "../../../../common/services";
import { BreadcrumbModule } from "app/buckwaframework/common/components";

const routes: Routes = [
  { path: "1", component: Int0151Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule,
    BreadcrumbModule],
  declarations: [Int0151Component],
  exports: [RouterModule]
})
export class Int015Module {}
