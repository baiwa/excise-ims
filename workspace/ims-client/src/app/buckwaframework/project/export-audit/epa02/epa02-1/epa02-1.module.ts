import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { Epa021Component } from "./epa02-1.component";

import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "../../../../common/services";
import { Epa0211Component } from "./epa02-1-1/epa02-1-1.component";
import { Epa0212Component } from "./epa02-1-2/epa02-1-2.component";
import { Epa0213Component } from "./epa02-1-3/epa02-1-3.component";

const routes: Routes = [
  { path: "", component: Epa021Component, canActivate: [AuthGuard] },
  { path: "1", component: Epa0211Component, canActivate: [AuthGuard] },
  { path: "2", component: Epa0212Component, canActivate: [AuthGuard] },
  { path: "3", component: Epa0213Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule],
  declarations: [
    Epa021Component,
    Epa0211Component,
    Epa0212Component,
    Epa0213Component
  ],
  exports: [RouterModule]
})
export class Epa021Module {}
