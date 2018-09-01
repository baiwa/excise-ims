import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";


import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "../../../../../common/services";
import { FormsModule } from "@angular/forms";
import { Int0911Component } from "./int09-1-1.component";
import { Int09111Component } from "./int09-1-1-1/int09-1-1-1.component";
import { Int09112Component } from "./int09-1-1-2/int09-1-1-2.component";
import { Int09113Component } from "./int09-1-1-3/int09-1-1-3.component";
import { Int09114Component } from "./int09-1-1-4/int09-1-1-4.component";
import { Int09115Component } from "./int09-1-1-5/int09-1-1-5.component";

const routes: Routes = [
  { path: "", component: Int0911Component, canActivate: [AuthGuard] },
  { path: "1", component: Int09111Component, canActivate: [AuthGuard] },
  { path: "2", component: Int09112Component, canActivate: [AuthGuard] },
  { path: "3", component: Int09113Component, canActivate: [AuthGuard] },
  { path: "4", component: Int09114Component, canActivate: [AuthGuard] },
  { path: "5", component: Int09115Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule, FormsModule],
  declarations: [
    Int0911Component,
    Int09111Component,
    Int09112Component,
    Int09113Component,
    Int09114Component,
    Int09115Component],
  exports: [RouterModule]
})
export class Int0911Module {}
