import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common"
import { CreateSaveMessageTraderComponent } from './create-save-message-trader.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';

const routes: Routes = [
    { path: '', component: CreateSaveMessageTraderComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule],
  declarations: [CreateSaveMessageTraderComponent],
  exports: [RouterModule]
})
export class CreateSaveMessageTraderComponentModule { }