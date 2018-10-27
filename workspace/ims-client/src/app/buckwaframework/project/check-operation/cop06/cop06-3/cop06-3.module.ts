import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "services/auth-guard.service";
import { BreadcrumbModule } from "../../../../common/components";
import { Cop063Component } from "projects/check-operation/cop06/cop06-3/cop06-3.component";
import { Cop0631Component } from "projects/check-operation/cop06/cop06-3/cop06-3-1/cop06-3-1.component";

const routes: Routes = [
  { path: "", component: Cop063Component, canActivate: [AuthGuard] },
  { path: "1", component: Cop0631Component, canActivate: [AuthGuard] }
 
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule,BreadcrumbModule
	],
  declarations: [ Cop063Component,Cop0631Component],
  exports: [RouterModule]
})
export class Cop063Module {}
