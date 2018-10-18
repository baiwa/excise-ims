import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "services/auth-guard.service";

import { Cop064Component } from "projects/check-operation/cop06/cop06-4/cop06-4.component";
import { BreadcrumbModule } from "../../../../common/components";

const routes: Routes = [
  { path: "", component: Cop064Component, canActivate: [AuthGuard] }, 
 
 
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule,BreadcrumbModule],
  declarations: [ Cop064Component,],
  exports: [RouterModule]
})
export class Cop064Module {}
