import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from "@angular/router";
import { Int021Component } from "./int02-1/int02-1.component";
import { Int022Component } from "./int02-2/int02-2.component";
import { FormsModule } from "@angular/forms";
import { Int023Component } from "./int02-3/int02-3.component";
import { Int02M2Component } from "./int02-m2/int02-m2.component";
import { Int02M3Component } from "./int02-m3/int02-m3.component";
import { Int02M4Component } from "./int02-m4/int02-m4.component";
import { Int02M41Component } from "./int02-m4/int02-m4-1/int02-m4-1.component";
import { Int02M42Component } from "./int02-m4/int02-m4-2/int02-m4-2.component";
import { Int02M43Component } from "./int02-m4/int02-m4-3/int02-m4-3.component";
import { Int02M51Component } from "./int02-m5/int02-m5-1/int02-m5-1.component";
import { Int02M511Component } from "./int02-m5/int02-m5-1/int02-m5-1-1/int02-m5-1-1.component";
import { Int02M52Component } from "./int02-m5/int02-m5-2/int02-m5-2.component";
import { Int02M521Component } from "./int02-m5/int02-m5-2/int02-m5-2-1/int02-m5-2-1.component";
import { CanDeactivateGuard } from "../../../common/services";
import { ComponentsModule } from "../../../common/components/components.module";
import { Int02M31Component } from "./int02-m3/int02-m3-1/int02-m3-1.component";

const routes: Routes = [
  { path: "1", component: Int021Component },
  {
    path: "2",
    component: Int022Component,
    canDeactivate: [CanDeactivateGuard]
  },
  {
    path: "3",
    component: Int023Component,
    canDeactivate: [CanDeactivateGuard]
  },
  { path: "m2", component: Int02M2Component },
  { path: "m3", component: Int02M3Component },
  { path: "m3/1", component: Int02M31Component },
  { path: "m4", component: Int02M4Component },
  { path: "m5/1", component: Int02M51Component },
  { path: "m5/1/1", component: Int02M511Component },
  { path: "m5/2", component: Int02M52Component },
  { path: "m5/2/1", component: Int02M521Component }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    FormsModule,
    ComponentsModule
  ],
  declarations: [
    // HEAD Component
    Int021Component,
    Int022Component,
    Int023Component,
    // M Main Component
    Int02M2Component,
    Int02M3Component,
    Int02M4Component,
    // M Sub Component
    Int02M31Component,
    Int02M41Component,
    Int02M42Component,
    Int02M43Component,
    Int02M51Component,
    Int02M52Component,
    // M Sub 2 Component
    Int02M511Component,
    Int02M521Component
  ],
  exports: [RouterModule]
})
export class Int02Module {}
