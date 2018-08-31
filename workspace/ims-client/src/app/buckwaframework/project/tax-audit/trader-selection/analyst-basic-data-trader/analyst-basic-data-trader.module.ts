import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common"
import { AnalystBasicDataTraderComponent } from './analyst-basic-data-trader.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { FormsModule } from '@angular/forms';
import { ModalModule } from '../../../../common/components';

const routes: Routes = [
    { path: '', component: AnalystBasicDataTraderComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule, FormsModule, ModalModule],
  declarations: [AnalystBasicDataTraderComponent],
  exports: [RouterModule]
})
export class AnalystBasicDataTraderComponentModule { }