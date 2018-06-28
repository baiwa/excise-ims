import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from "@angular/router";

const routes: Routes = [
  { path: "1", loadChildren: "./epa01-1/epa01-1.module#Epa011Module" }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule],
  exports: [RouterModule]
})
export class Epa01Module {}
