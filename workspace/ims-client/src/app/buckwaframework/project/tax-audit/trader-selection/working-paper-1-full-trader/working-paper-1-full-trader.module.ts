import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common"
import { WorkingPaper1FullTraderComponent } from './working-paper-1-full-trader.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { FormsModule } from '@angular/forms';

const routes: Routes = [
    { path: '', component: WorkingPaper1FullTraderComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule, FormsModule],
  declarations: [WorkingPaper1FullTraderComponent],
  exports: [RouterModule]
})
export class WorkingPaper1FullTraderComponentModule { }