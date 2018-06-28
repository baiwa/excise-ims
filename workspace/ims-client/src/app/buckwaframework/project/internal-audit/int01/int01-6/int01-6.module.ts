import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Int0161Component } from "./int01-6-1/int01-6-1.component";

import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "../../../../common/services";

const routes: Routes = [
  { path: "1", component: Int0161Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule],
  declarations: [Int0161Component],
  exports: [RouterModule]
})
export class Int016Module {}
