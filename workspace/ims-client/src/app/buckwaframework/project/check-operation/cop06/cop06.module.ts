import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "../../../common/services";
import { Cop06Component } from "projects/check-operation/cop06/cop06.component";

import { Cop062Component } from './cop06-2/cop06-2.component';
import { Cop064Component } from './cop06-4/cop06-4.component';
import { Cop065Component } from './cop06-5/cop06-5.component';
import { Cop066Component } from './cop06-6/cop06-6.component';
import { Cop067Component } from './cop06-7/cop06-7.component';
import { Cop068Component } from './cop06-8/cop06-8.component';
import { Cop069Component } from './cop06-9/cop06-9.component';
import { Cop0610Component } from './cop06-10/cop06-10.component';
const routes: Routes = [
  { path: "", component: Cop06Component, canActivate: [AuthGuard] },
  { path: "1", loadChildren: "./cop06-1/cop06-1.module#Cop061Module" },
  { path: "2", component: Cop062Component },
  { path: "3", loadChildren: "./cop06-3/cop06-3.module#Cop063Module" },
  { path: "4", loadChildren: "./cop06-4/cop06-4.module#Cop064Module" },
  { path: "5", component: Cop065Component },
  { path: "5", component: Cop066Component },


];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule],
  declarations: [Cop06Component, Cop062Component, Cop065Component, Cop066Component, Cop067Component, Cop068Component, Cop069Component, Cop0610Component, ],
  exports: [RouterModule]
})
export class Cop06Module { }
