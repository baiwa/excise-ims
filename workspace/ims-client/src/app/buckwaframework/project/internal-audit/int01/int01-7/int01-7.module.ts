import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Int0171Component } from "./int01-7-1/int01-7-1.component";

import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "../../../../common/services";
import { BreadcrumbModule } from "app/buckwaframework/common/components";

const routes: Routes = [
  { path: "1", component: Int0171Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule,
    BreadcrumbModule],
  declarations: [Int0171Component],
  exports: [RouterModule]
})
export class Int017Module {}
