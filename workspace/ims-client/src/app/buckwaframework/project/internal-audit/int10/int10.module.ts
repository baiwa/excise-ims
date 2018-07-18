import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from "@angular/router";
import { Int101Component } from "./int10-1/int10-1.component";
import { FormsModule } from "@angular/forms";


const routes: Routes = [
  { path: "1", component: Int101Component },

];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule,FormsModule],
  declarations: [Int101Component],
  exports: [RouterModule]
})
export class Int10Module {}
