import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from "@angular/router";
import { FormsModule } from "@angular/forms";


const routes: Routes = [
  { path: "1", loadChildren: "./int11-1/int11-1.module#Int111Module" },
  { path: "2", loadChildren: "./int11-2/int11-2.module#Int112Module" },
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule, FormsModule],
  exports: [RouterModule]
})

export class Int11Module { }
