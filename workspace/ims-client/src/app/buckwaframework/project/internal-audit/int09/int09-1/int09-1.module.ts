import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Int091Component } from "./int09-1.component";

import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "../../../../common/services";
import { FormsModule } from "@angular/forms";

const routes: Routes = [
  { path: "", component: Int091Component, canActivate: [AuthGuard] },
  { path: "1", loadChildren: "./int09-1-1/int09-1-1.module#Int0911Module"}
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule, FormsModule],
  declarations: [Int091Component],
  exports: [RouterModule]
})
export class Int091Module {}
