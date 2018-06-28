import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from "@angular/router";

const routes: Routes = [
  { path: "1", loadChildren: "./int01-1/int01-1.module#Int011Module" },
  { path: "2", loadChildren: "./int01-2/int01-2.module#Int012Module" },
  { path: "3", loadChildren: "./int01-3/int01-3.module#Int013Module" },
  { path: "4", loadChildren: "./int01-4/int01-4.module#Int014Module" },
  { path: "5", loadChildren: "./int01-5/int01-5.module#Int015Module" },
  { path: "6", loadChildren: "./int01-6/int01-6.module#Int016Module" },
  { path: "7", loadChildren: "./int01-7/int01-7.module#Int017Module" },
  { path: "8", loadChildren: "./int01-8/int01-8.module#Int018Module" }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule],
  exports: [RouterModule]
})
export class Int01Module {}
