import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common"
import { SummaryAmountProductTraderComponent } from './summary-amount-product-trader.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';

const routes: Routes = [
    { path: '', component: SummaryAmountProductTraderComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule],
  declarations: [SummaryAmountProductTraderComponent],
  exports: [RouterModule]
})
export class SummaryAmountProductTraderComponentModule { }