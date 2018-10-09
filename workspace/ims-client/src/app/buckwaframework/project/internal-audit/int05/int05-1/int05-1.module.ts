import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";


import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { BreadcrumbModule } from '../../../../common/components';


const routes: Routes = [
   // { path: '', component: Int051Component, canActivate: [AuthGuard] },
    { path: '1', loadChildren: './int05-1-1/int05-1-1.module#Int0511Module', canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    FormsModule,
    BreadcrumbModule
  ],
  declarations: [
    
  ],
  exports: [RouterModule]
})
export class Int051Module { }