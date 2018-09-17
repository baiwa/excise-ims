import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from "@angular/router";
import { FormsModule } from "@angular/forms";

const routes: Routes = [
  { path: "1", loadChildren: "./int09-1/int09-1.module#Int091Module" },
 

];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule, FormsModule],
  declarations: [],
  exports: [RouterModule]
  
})
export class Int09Module {}
