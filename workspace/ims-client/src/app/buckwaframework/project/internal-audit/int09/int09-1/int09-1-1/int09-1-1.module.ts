import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";


import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "../../../../../common/services";
import { FormsModule } from "@angular/forms";
import { Int0911Component } from "./int09-1-1.component";
import { Int09111Component } from "./int09-1-1-1/int09-1-1-1.component";

const routes: Routes = [
  { path: "", component: Int0911Component, canActivate: [AuthGuard] },
  { path: "1", component: Int09111Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule, FormsModule],
  declarations: [Int0911Component,Int09111Component],
  exports: [RouterModule]
})
export class Int0911Module {}
