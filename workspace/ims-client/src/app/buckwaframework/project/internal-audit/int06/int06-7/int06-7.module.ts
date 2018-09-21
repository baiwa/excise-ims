import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Int067Component } from "./../int06-7/int06-7.component";

import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "../../../../common/services";

const routes: Routes = [
  { path: "", component: Int067Component, canActivate: [AuthGuard] }
  // { path: '1', component: Int0651Component, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule],
  declarations: [Int067Component],
  exports: [RouterModule]
})
export class Int067Module {}
