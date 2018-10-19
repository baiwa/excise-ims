import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from "@angular/router";
import { Cop061Component } from "projects/check-operation/cop06/cop06-1/cop06-1.component";
import { AuthGuard } from "services/auth-guard.service";
import { Cop0612Component } from "projects/check-operation/cop06/cop06-1/cop06-1-2/cop06-1-2.component";
import { Cop0611Component } from "projects/check-operation/cop06/cop06-1/cop06-1-1/cop06-1-1.component";
import { BreadcrumbModule } from "../../../../common/components";

const routes: Routes = [
  { path: "", component: Cop061Component, canActivate: [AuthGuard] },
  { path: "1", component: Cop0611Component, canActivate: [AuthGuard] },
  { path: "2", component: Cop0612Component, canActivate: [AuthGuard] },
 
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule,BreadcrumbModule
	],
  declarations: [ Cop061Component,Cop0611Component,Cop0612Component],
  exports: [RouterModule]
})
export class Cop061Module {}
