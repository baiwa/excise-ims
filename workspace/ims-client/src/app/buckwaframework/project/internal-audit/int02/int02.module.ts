import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from "@angular/router";
import { Int021Component } from "./int02-1/int02-1.component";
import { Int022Component } from "./int02-2/int02-2.component";
import { FormsModule } from "@angular/forms";

const routes: Routes = [
  { path: "1", component: Int021Component },
  { path: "2", component: Int022Component }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule, FormsModule],
  declarations: [Int021Component, Int022Component],
  exports: [RouterModule]
})
export class Int02Module {}
