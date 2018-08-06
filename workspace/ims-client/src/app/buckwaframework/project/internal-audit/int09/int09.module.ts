import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from "@angular/router";
import { Int092Component } from "./int09-2/int09-2.component";
import { FormsModule } from "@angular/forms";
import { Int09211Component } from './int09-2/int09-2-1-1/int09-2-1-1.component';
import { Int09212Component } from './int09-2/int09-2-1-2/int09-2-1-2.component';
import { Int09213Component } from './int09-2/int09-2-1-3/int09-2-1-3.component';

const routes: Routes = [
  { path: "1", loadChildren: "./int09-1/int09-1.module#Int091Module" },
  { path: "2", component: Int092Component },
  { path: "2-1-1", component: Int09211Component },
  { path: "2-1-2", component: Int09212Component },
  { path: "2-1-3", component: Int09213Component },

];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule, FormsModule],
  declarations: [Int092Component, Int09211Component, Int09212Component, Int09213Component],
  exports: [RouterModule]
})
export class Int09Module {}
