import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from "@angular/router";
import { Int092Component } from "./int09-2/int09-2.component";

const routes: Routes = [
  { path: "1", loadChildren: "./int09-1/int09-1.module#Int091Module" },
  { path: "2", component: Int092Component }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule],
  declarations: [Int092Component],
  exports: [RouterModule]
})
export class Int09Module {}
