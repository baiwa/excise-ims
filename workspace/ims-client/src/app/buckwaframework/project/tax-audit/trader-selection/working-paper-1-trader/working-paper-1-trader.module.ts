import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common"
import { WorkingPaper1TraderComponent } from './working-paper-1-trader.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { FormsModule } from '@angular/forms';
import { BreadcrumbModule } from '../../../../common/components';

const routes: Routes = [
    { path: '', component: WorkingPaper1TraderComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule, FormsModule,BreadcrumbModule],
  declarations: [WorkingPaper1TraderComponent],
  exports: [RouterModule]
})
export class WorkingPaper1TraderComponentModule { }