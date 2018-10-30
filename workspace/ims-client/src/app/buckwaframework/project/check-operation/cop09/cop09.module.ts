import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";

import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "app/buckwaframework/common/services";
import { BreadcrumbModule } from "app/buckwaframework/common/components";
import { Cop091Component } from "projects/check-operation/cop09/cop09-1/cop09-1.component";

const routes: Routes = [
  { path: "1", component: Cop091Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule,FormsModule,BreadcrumbModule],
  declarations: [Cop091Component],
  exports: [RouterModule]
})
export class Cop09Module {}
