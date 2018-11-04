import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";

import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "app/buckwaframework/common/services";
import { BreadcrumbModule } from "app/buckwaframework/common/components";
import { Cop10Component } from "projects/check-operation/cop10/cop10.component";

const routes: Routes = [
  { path: '', component: Cop10Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule,FormsModule,BreadcrumbModule],
  declarations: [Cop10Component],
  exports: [RouterModule]
})
export class Cop10Module {}
