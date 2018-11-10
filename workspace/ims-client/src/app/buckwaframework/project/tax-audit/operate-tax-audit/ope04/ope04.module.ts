import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { Ope041Component } from "./ope04-1/ope04-1.component";

import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "../../../../common/services";
import { Ope042Component } from "./ope04-2/ope04-2.component";
import { Ope043Component } from "./ope04-3/ope04-3.component";
import { Ope044Component } from "./ope04-4/ope04-4.component";
import { Ope0441Component } from "./ope04-4/ope04-4-1/ope04-4-1.component";
import { Ope045Component } from "./ope04-5/ope04-5.component";
import { Ope046Component } from "./ope04-6/ope04-6.component";
import { Ope047Component } from "./ope04-7/ope04-7.component";
import { Ope048Component } from "./ope04-8/ope04-8.component";
import { Ope049Component } from "./ope04-9/ope04-9.component";
import { Ope0410Component } from "./ope04-10/ope04-10.component";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { BreadcrumbModule } from '../../../../common/components';
import { Ope0461Component } from './ope04-6/ope04-6-1/ope04-6-1.component';
import { Ope0462Component } from './ope04-6/ope04-6-2/ope04-6-2.component';
import { Ope0411Component } from './ope04-1/ope04-1-1/ope04-1-1.component';
import { Ope0412Component } from './ope04-1/ope04-1-2/ope04-1-2.component';
import { Ope0421Component } from './ope04-2/ope04-2-1/ope04-2-1.component';
import { Ope0422Component } from './ope04-2/ope04-2-2/ope04-2-2.component';
import { Ope0431Component } from './ope04-3/ope04-3-1/ope04-3-1.component';
import { Ope0432Component } from './ope04-3/ope04-3-2/ope04-3-2.component';
import { Ope0451Component } from './ope04-5/ope04-5-1/ope04-5-1.component';
import { Ope0452Component } from './ope04-5/ope04-5-2/ope04-5-2.component';




const routes: Routes = [
  { path: "1", component: Ope041Component, canActivate: [AuthGuard] },
  { path: "1-1", component: Ope0411Component, canActivate: [AuthGuard] },
  { path: "1-2", component: Ope0412Component, canActivate: [AuthGuard] },
  { path: "2", component: Ope042Component, canActivate: [AuthGuard] },
  { path: "2-1", component: Ope0421Component, canActivate: [AuthGuard] },
  { path: "2-2", component: Ope0422Component, canActivate: [AuthGuard] },
  { path: "3-1", component: Ope0431Component, canActivate: [AuthGuard] },
  { path: "3-2", component: Ope0432Component, canActivate: [AuthGuard] },
  { path: "3", component: Ope043Component, canActivate: [AuthGuard] },
  { path: "4", component: Ope044Component, canActivate: [AuthGuard] },
  { path: "4/1", component: Ope0441Component, canActivate: [AuthGuard] },
  { path: "5", component: Ope045Component, canActivate: [AuthGuard] },
  { path: "5-1", component: Ope0451Component, canActivate: [AuthGuard] },
  { path: "5-2", component: Ope0452Component, canActivate: [AuthGuard] },
  { path: "6", component: Ope046Component, canActivate: [AuthGuard] },
  { path: "6-1", component: Ope0461Component, canActivate: [AuthGuard] },
  { path: "6-2", component: Ope0462Component, canActivate: [AuthGuard] },
  { path: "7", component: Ope047Component, canActivate: [AuthGuard] },
  { path: "8", component: Ope048Component, canActivate: [AuthGuard] },
  { path: "9", component: Ope049Component, canActivate: [AuthGuard] },
  { path: "10", component: Ope0410Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes), CommonModule,
    FormsModule,
    ReactiveFormsModule,
    BreadcrumbModule
    ],
  declarations: [
    Ope041Component,
    Ope042Component,
    Ope043Component,
    Ope044Component,
    Ope0441Component,
    Ope045Component,
    Ope046Component,
    Ope047Component,
    Ope048Component,
    Ope049Component,
    Ope0410Component,
    Ope0461Component,
    Ope0462Component,
    Ope0411Component,
    Ope0412Component,
    Ope0421Component,
    Ope0422Component,
    Ope0431Component,
    Ope0432Component,
    Ope0451Component,
    Ope0452Component
  ],
  exports: [RouterModule]
})
export class Ope04Module {}
