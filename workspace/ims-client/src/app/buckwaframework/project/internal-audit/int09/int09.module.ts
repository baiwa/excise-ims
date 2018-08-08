import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from "@angular/router";
import { Int092Component } from "./int09-2/int09-2.component";
import { FormsModule } from "@angular/forms";
import { Int09211Component } from './int09-2/int09-2-1-1/int09-2-1-1.component';
import { Int09212Component } from './int09-2/int09-2-1-2/int09-2-1-2.component';
import { Int09213Component } from './int09-2/int09-2-1-3/int09-2-1-3.component';
import { Int09221Component } from './int09-2/int09-2-2-1/int09-2-2-1.component';
import { Int09222Component } from './int09-2/int09-2-2-2/int09-2-2-2.component';
import { Int09223Component } from './int09-2/int09-2-2-3/int09-2-2-3.component';
import { Int09224Component } from './int09-2/int09-2-2-4/int09-2-2-4.component';
import { Int09225Component } from './int09-2/int09-2-2-5/int09-2-2-5.component';

const routes: Routes = [
  { path: "1", loadChildren: "./int09-1/int09-1.module#Int091Module" },
  { path: "2", component: Int092Component },
  { path: "2-1-1", component: Int09211Component },
  { path: "2-1-2", component: Int09212Component },
  { path: "2-1-3", component: Int09213Component },
  { path: "2-2-1", component: Int09221Component },
  { path: "2-2-2", component: Int09222Component },
  { path: "2-2-3", component: Int09223Component },
  { path: "2-2-4", component: Int09224Component },
  { path: "2-2-5", component: Int09225Component },

];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule, FormsModule],
  declarations: [
    Int092Component, 
    Int09211Component, 
    Int09212Component, 
    Int09213Component, 
    Int09221Component, 
    Int09222Component, 
    Int09223Component, Int09224Component, Int09225Component],
  exports: [RouterModule]
  
})
export class Int09Module {}
