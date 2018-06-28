import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { Ope051Component } from './ope05-1/ope05-1.component';
import { Ope0511Component } from './ope05-1/ope05-1-1/ope05-1-1.component';
import { Ope052Component } from './ope05-2/ope05-2.component';
import { Ope053Component } from './ope05-3/ope05-3.component';
import { Ope054Component } from './ope05-4/ope05-4.component';
import { Ope055Component } from './ope05-5/ope05-5.component';
import { Ope056Component } from './ope05-6/ope05-6.component';
import { Ope057Component } from './ope05-7/ope05-7.component';

const routes: Routes = [
    { path: '1', component: Ope051Component, canActivate: [AuthGuard] },
    { path: '1/1', component: Ope0511Component, canActivate: [AuthGuard] },
    { path: '2', component: Ope052Component, canActivate: [AuthGuard] },
    { path: '3', component: Ope053Component, canActivate: [AuthGuard] },
    { path: '4', component: Ope054Component, canActivate: [AuthGuard] },
    { path: '5', component: Ope055Component, canActivate: [AuthGuard] },
    { path: '6', component: Ope056Component, canActivate: [AuthGuard] },
    { path: '7', component: Ope057Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
      RouterModule.forChild(routes),
      CommonModule
    ],
  declarations: [
    Ope051Component,
    Ope0511Component,
    Ope052Component,
    Ope053Component,
    Ope054Component,
    Ope055Component,
    Ope056Component,
    Ope057Component
  ],
  exports: [RouterModule]
})
export class Ope05Module { }