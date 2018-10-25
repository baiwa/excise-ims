import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";

import { Routes, RouterModule } from "@angular/router";

import { BreadcrumbModule } from "../../../common/components";
import { Cop071Component } from "projects/check-operation/cop07/cop07-1/cop07-1.component";
import { AuthGuard } from "app/buckwaframework/common/services";

const routes: Routes = [
  { path: "1", component: Cop071Component, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule,FormsModule,BreadcrumbModule],
  declarations: [Cop071Component],
  exports: [RouterModule]
})
export class Cop07Module {}
