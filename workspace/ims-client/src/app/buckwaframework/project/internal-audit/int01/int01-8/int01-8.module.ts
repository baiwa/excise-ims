import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { Int0181Component } from "./int01-8-1/int01-8-1.component";

import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "../../../../common/services";
import { BreadcrumbModule } from '../../../../common/components';

const routes: Routes = [
  { path: "1", component: Int0181Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes),
    FormsModule,
    CommonModule,
    BreadcrumbModule
  ],
  declarations: [Int0181Component],
  exports: [RouterModule]
})
export class Int018Module { }
