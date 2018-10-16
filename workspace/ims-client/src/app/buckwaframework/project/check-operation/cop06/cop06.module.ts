import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "../../../common/services";
import { Cop06Component } from "projects/check-operation/cop06/cop06.component";

import { Cop062Component } from './cop06-2/cop06-2.component';
const routes: Routes = [
  { path: "", component: Cop06Component, canActivate: [AuthGuard] },
  { path: "1", loadChildren: "./cop06-1/cop06-1.module#Cop061Module" },
  { path: "2", component: Cop062Component },
  { path: "3", loadChildren: "./cop06-3/cop06-3.module#Cop063Module" },

];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule],
  declarations: [Cop06Component, Cop062Component],
  exports: [RouterModule]
})
export class Cop06Module { }
