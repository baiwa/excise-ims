import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '1', loadChildren: './int08-1/int08-1.module#Int081Module' },
  { path: '2', loadChildren: './int08-2/int08-2.module#Int082Module' },
  { path: '3', loadChildren: './int08-3/int08-3.module#Int083Module' }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule
  ],
  exports: [RouterModule]
})
export class Int08Module { }