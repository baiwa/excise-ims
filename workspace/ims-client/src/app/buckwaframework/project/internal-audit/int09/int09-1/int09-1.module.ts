import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Int091Component } from "./int09-1.component";
import { Int0911Component } from "./int09-1-1/int09-1-1.component";

import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "../../../../common/services";
import { FormsModule } from "@angular/forms";

const routes: Routes = [
  { path: "", component: Int091Component, canActivate: [AuthGuard] },
  { path: "1", component: Int0911Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule, FormsModule],
  declarations: [Int091Component, Int0911Component],
  exports: [RouterModule]
})
export class Int091Module {}
