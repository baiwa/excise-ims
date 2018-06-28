import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Int075Component } from "./int07-5.component";

import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "../../../../common/services";
import { Int0751Component } from "./int07-5-1/int07-5-1.component";
import { Int0752Component } from "./int07-5-2/int07-5-2.component";
import { Int0753Component } from "./int07-5-3/int07-5-3.component";
import { Int0754Component } from "./int07-5-4/int07-5-4.component";

const routes: Routes = [
  { path: "", component: Int075Component, canActivate: [AuthGuard] },
  { path: "1", component: Int0751Component, canActivate: [AuthGuard] },
  { path: "2", component: Int0752Component, canActivate: [AuthGuard] },
  { path: "3", component: Int0753Component, canActivate: [AuthGuard] },
  { path: "4", component: Int0754Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule],
  declarations: [
    Int075Component,
    Int0751Component,
    Int0752Component,
    Int0753Component,
    Int0754Component
  ],
  exports: [RouterModule]
})
export class Int075Module {}
