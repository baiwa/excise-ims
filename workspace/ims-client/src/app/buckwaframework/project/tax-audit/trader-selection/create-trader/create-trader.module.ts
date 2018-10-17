import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common"
import { CreateTraderComponent } from './create-trader.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { BreadcrumbModule } from '../../../../common/components';

const routes: Routes = [
    { path: '', component: CreateTraderComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule,BreadcrumbModule],
  declarations: [CreateTraderComponent],
  exports: [RouterModule]
})
export class CreateTraderComponentModule { }