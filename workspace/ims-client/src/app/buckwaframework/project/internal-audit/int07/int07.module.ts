import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from "@angular/router";
import { Int071Component } from "./int07-1/int07-1.component";
import { Int072Component } from "./int07-2/int07-2.component";
import { Int073Component } from "./int07-3/int07-3.component";
import { Int074Component } from "./int07-4/int07-4.component";
import { Int077Component } from "./int07-7/int07-7.component";
import { AuthGuard } from "../../../common/services";
import { FormsModule } from "@angular/forms";
const routes: Routes = [
  { path: "1", component: Int071Component, canActivate: [AuthGuard] },
  { path: "2", component: Int072Component, canActivate: [AuthGuard] },
  { path: "3", component: Int073Component, canActivate: [AuthGuard] },
  { path: "4", component: Int074Component, canActivate: [AuthGuard] },
  { path: "5", loadChildren: "./int07-5/int07-5.module#Int075Module" },
  { path: "6", loadChildren: "./int07-6/int07-6.module#Int076Module" },
  { path: "7", component: Int077Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    FormsModule],
  declarations: [
    Int071Component,
    Int072Component,
    Int073Component,
    Int074Component,
    Int077Component
  ],
  exports: [RouterModule]
})
export class Int07Module {}
