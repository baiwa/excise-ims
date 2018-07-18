import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from "@angular/router";
import { Int101Component } from "./int10-1/int10-1.component";


const routes: Routes = [
  { path: "1", component: Int101Component },

];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule],
  declarations: [Int101Component],
  exports: [RouterModule]
})
export class Int10Module {}
