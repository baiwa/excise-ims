import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from "@angular/router";
import { Int054Component } from "./int05-4/int05-4.component";

const routes: Routes = [
  { path: "1", loadChildren: "./int05-1/int05-1.module#Int051Module" },
  { path: "2", loadChildren: "./int05-2/int05-2.module#Int052Module" },
  { path: "3", loadChildren: "./int05-3/int05-3.module#Int053Module" },
  { path: "4", component: Int054Component }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule],
  declarations: [Int054Component],
  exports: [RouterModule]
})
export class Int05Module {}
