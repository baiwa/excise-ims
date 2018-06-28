import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common"
import { ResultAnalysisPage } from './result-analysis.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../common/services';

const routes: Routes = [
    { path: ':category/:coordinate', component: ResultAnalysisPage, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule],
  declarations: [ResultAnalysisPage],
  exports: [RouterModule]
})
export class ResultAnalysisPageModule { }