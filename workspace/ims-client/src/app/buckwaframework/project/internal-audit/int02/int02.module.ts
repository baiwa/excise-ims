import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from "@angular/router";
import { Int021Component } from "./int02-1/int02-1.component";
import { Int022Component } from "./int02-2/int02-2.component";
import { FormsModule } from "@angular/forms";
import { Int023Component } from "./int02-3/int02-3.component";
import { Int02M2Component } from "./int02-m2/int02-m2.component";
import { Int02M3Component } from './int02-m3/int02-m3.component';
import { Int02M4Component } from './int02-m4/int02-m4.component';

const routes: Routes = [
  { path: "1", component: Int021Component },
  { path: "2", component: Int022Component },
  { path: "3", component: Int023Component },
  { path: "m2", component: Int02M2Component },
  { path: "m3", component: Int02M3Component },
  { path: "m4", component: Int02M4Component }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule, FormsModule],
  declarations: [
    Int021Component,
    Int022Component,
    Int023Component,
    Int02M2Component,
    Int02M3Component,
    Int02M4Component
  ],
  exports: [RouterModule]
})
export class Int02Module {}
