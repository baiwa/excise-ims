import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "services/auth-guard.service";
import { Int061151Component } from "./int06-11-5-1/int06-11-5-1.component";
import { Int061152Component } from "./int06-11-5-2/int06-11-5-2.component";
import { Int061153Component } from "./int06-11-5-3/int06-11-5-3.component";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { BreadcrumbModule } from "components/breadcrumb/breadcrumb.module";
import { Int06115Component } from "./int06-11-5.component";

const routes: Routes = [
  { path: "", component: Int06115Component, canActivate: [AuthGuard] },
  { path: "1", component: Int061151Component, canActivate: [AuthGuard] },
  { path: "2", component: Int061152Component, canActivate: [AuthGuard] },
  { path: "3", component: Int061153Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    BreadcrumbModule
  ],
  declarations: [
    Int06115Component,
    Int061151Component,
    Int061152Component,
    Int061153Component
  ],
  exports: [RouterModule]
})
export class Int061105Module {}
