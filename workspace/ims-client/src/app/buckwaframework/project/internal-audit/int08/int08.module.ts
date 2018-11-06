import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { FormsModule } from "@angular/forms";
import { Routes, RouterModule } from "@angular/router";
import { BreadcrumbModule } from "../../../common/components";
import { Int084Component } from "projects/internal-audit/int08/int08-4/int08-4.component";
import { AuthGuard } from "app/buckwaframework/common/services";
import { Int085Component } from "projects/internal-audit/int08/int08-5/int08-5.component";

const routes: Routes = [
  { path: "1", loadChildren: "./int08-1/int08-1.module#Int081Module" },
  { path: "2", loadChildren: "./int08-2/int08-2.module#Int082Module" },
  { path: "3", loadChildren: "./int08-3/int08-3.module#Int083Module" },
  { path: "4", component: Int084Component, canActivate: [AuthGuard] },
  { path: "5", component: Int085Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes), 
    CommonModule, 
    FormsModule,
  BreadcrumbModule
],
declarations: [Int084Component,Int085Component],
  exports: [RouterModule]
})
export class Int08Module { }
