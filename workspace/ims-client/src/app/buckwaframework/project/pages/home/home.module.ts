import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common"
import { HomePage } from './home';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../common/services';

const routes: Routes = [
    { path: '', component: HomePage, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule],
  declarations: [HomePage],
  exports: [RouterModule]
})
export class HomeModule { }