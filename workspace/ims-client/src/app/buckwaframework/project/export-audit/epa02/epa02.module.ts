import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common"

import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
    { path: '1', loadChildren: './epa02-1/epa02-1.module#Epa021Module' }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule],
  exports: [RouterModule]
})
export class Epa02Module { }