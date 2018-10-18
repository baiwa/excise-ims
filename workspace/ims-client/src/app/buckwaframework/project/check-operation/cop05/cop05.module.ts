import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "../../../common/services";
import { Cop051Component } from "./cop05-1/cop05-1.component";
import { Cop0511Component } from "./cop05-1/cop05-1-1/cop05-1-1.component";
import { BreadcrumbModule } from "../../../common/components";

const routes: Routes = [
  { path: "1", component: Cop051Component, canActivate: [AuthGuard] },
  { path: "1/1", component: Cop0511Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule,BreadcrumbModule],
  declarations: [Cop051Component, Cop0511Component],
  exports: [RouterModule]
})
export class Cop05Module {}
