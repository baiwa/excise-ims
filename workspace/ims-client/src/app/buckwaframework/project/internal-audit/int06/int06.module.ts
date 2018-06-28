import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from "@angular/router";

const routes: Routes = [
  { path: "1", loadChildren: "./int06-1/int06-1.module#Int061Module" },
  { path: "2", loadChildren: "./int06-2/int06-2.module#Int062Module" },
  { path: "3", loadChildren: "./int06-3/int06-3.module#Int063Module" },
  { path: "4", loadChildren: "./int06-4/int06-4.module#Int064Module" },
  { path: "5", loadChildren: "./int06-5/int06-5.module#Int065Module" },
  { path: "6", loadChildren: "./int06-6/int06-6.module#Int066Module" }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule],
  exports: [RouterModule]
})
export class Int06Module {}
