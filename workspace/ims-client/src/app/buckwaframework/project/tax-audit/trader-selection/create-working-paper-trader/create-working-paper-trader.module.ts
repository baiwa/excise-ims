import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common"
import { CreateWorkingPaperTraderComponent } from './create-working-paper-trader.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { FormsModule } from '@angular/forms';

const routes: Routes = [
    { path: '', component: CreateWorkingPaperTraderComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule, FormsModule],
  declarations: [CreateWorkingPaperTraderComponent],
  exports: [RouterModule]
})
export class CreateWorkingPaperTraderComponentModule { }