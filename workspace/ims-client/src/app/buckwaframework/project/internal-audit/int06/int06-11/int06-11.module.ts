import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "../../../../common/services";

import { Int0611Component } from "projects/internal-audit/int06/int06-11/int06-11.component";
import { Int06111Component } from "./int06-11-1/int06-11-1.component";
import { Int06112Component } from "./int06-11-2/int06-11-2.component";
import { Int06113Component } from "./int06-11-3/int06-11-3.component";
import { Int06114Component } from "./int06-11-4/int06-11-4.component";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { Int0611301Component } from "./int06-11-3/int06-11-3-01/int06-11-3-01.component";
import { BreadcrumbModule } from "components/breadcrumb/breadcrumb.module";
import { Int06116Component } from "./int06-11-6/int06-11-6.component";
import { Int06117Component } from "./int06-11-7/int06-11-7.component";
import { Int06118Component } from "./int06-11-8/int06-11-8.component";
import { Int06119Component } from "./int06-11-9/int06-11-9.component";
import { DirectivesModule } from "app/buckwaframework/common/directives/directives.module";

const routes: Routes = [
  { path: "", component: Int0611Component, canActivate: [AuthGuard] },
  { path: "1", component: Int06111Component, canActivate: [AuthGuard] },
  { path: "2", component: Int06112Component, canActivate: [AuthGuard] },
  { path: "3", component: Int06113Component, canActivate: [AuthGuard] },
  { path: "3-1", component: Int0611301Component, canActivate: [AuthGuard] },
  { path: "4", component: Int06114Component, canActivate: [AuthGuard] },
  // { path: "5", component: Int06115Component, canActivate: [AuthGuard] },
  { path: "5", loadChildren: "./int06-11-5/int06-11-5.module#Int061105Module" },
  { path: "6", component: Int06116Component, canActivate: [AuthGuard] },
  { path: "7", component: Int06117Component, canActivate: [AuthGuard] },
  { path: "8", component: Int06118Component, canActivate: [AuthGuard] },
  { path: "9", component: Int06119Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    BreadcrumbModule,
    DirectivesModule
  ],
  declarations: [
    Int0611Component,
    Int06111Component,
    Int06112Component,
    Int06113Component,
    Int06114Component,
    Int0611301Component,
    // Int06115Component,
    Int06116Component,
    Int06117Component,
    Int06118Component,
    Int06119Component
    // Int061151Component,
    // Int061152Component,
    // Int061153Component
  ],
  exports: [RouterModule]
})
export class Int0611Module {}
