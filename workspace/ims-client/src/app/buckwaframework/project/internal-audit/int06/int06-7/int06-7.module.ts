import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Int067Component } from "./int06-7.component";

import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "../../../../common/services";
import { FormsModule } from "@angular/forms";
import { Int0671Component } from "./int06-7-1/int06-7-1.component";
import { BreadcrumbModule } from "../../../../common/components";

const routes: Routes = [
  { path: "", component: Int067Component, canActivate: [AuthGuard] },
  { path: "1", component: Int0671Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    FormsModule,
    BreadcrumbModule
  ],
  declarations: [Int067Component, Int0671Component],
  exports: [RouterModule]
})
export class Int067Module {}
