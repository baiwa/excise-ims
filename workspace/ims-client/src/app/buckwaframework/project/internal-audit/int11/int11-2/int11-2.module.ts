import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";


import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "../../../../common/services";
import { FormsModule } from "@angular/forms";
import { Int112Component } from "./int11-2.component";
import { Int1121Component } from "./int11-2-1/int11-2-1.component";

const routes: Routes = [
  { path: "", component: Int112Component, canActivate: [AuthGuard] },
  { path: "1", component: Int1121Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule, FormsModule],
  declarations: [
    Int112Component,
    Int1121Component
  ],
  exports: [RouterModule]
})
export class Int112Module {}
