import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from "@angular/router";

const routes: Routes = [
  { path: "1", loadChildren: "./cop04-1/cop04-1.module#Cop041Module" },
  { path: "2", loadChildren: "./cop04-2/cop04-2.module#Cop042Module" }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule],
  exports: [RouterModule]
})
export class Cop04Module {}
