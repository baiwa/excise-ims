import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common"
import { Epa011Component } from './epa01-1.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { Epa0111Component } from './epa01-1-1/epa01-1-1.component';
import { Epa0112Component } from './epa01-1-2/epa01-1-2.component';

const routes: Routes = [
    { path: '', component: Epa011Component, canActivate: [AuthGuard] },
    { path: '1', component: Epa0111Component, canActivate: [AuthGuard] },
    { path: '2', component: Epa0112Component, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule],
  declarations: [
    Epa011Component,
    Epa0111Component,
    Epa0112Component
  ],
  exports: [RouterModule]
})
export class Epa011Module { }