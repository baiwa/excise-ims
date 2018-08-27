import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";

import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "../../../common/services";




import { Cop011Component } from "./cop01-1/cop01-1.component";
import { Cop012Component } from "./cop01-2/cop01-2.component";
import { Cop013Component } from "./cop01-3/cop01-3.component";

const routes: Routes = [
  { path: "1", component: Cop011Component, canActivate: [AuthGuard] },
  { path: "2", component: Cop012Component, canActivate: [AuthGuard] },
  { path: "3", component: Cop013Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule,FormsModule],
  declarations: [Cop011Component, Cop012Component, Cop013Component],
  exports: [RouterModule],
  
})
export class Cop01Module {}
