import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../common/services';
import { FormsModule } from '@angular/forms';
import { BreadcrumbModule } from '../../../common/components';
import { TaxAuditSelectLineComponent } from 'projects/tax-audit/tax-audit-select-line/tax-audit-select-line.component';
const routes: Routes = [
    { path: '', component: TaxAuditSelectLineComponent, canActivate: [AuthGuard] }

];

@NgModule({
    imports: [
        RouterModule.forChild(routes),
        CommonModule,
        FormsModule,
        BreadcrumbModule
    ],
    declarations: [
        TaxAuditSelectLineComponent
    ],
    exports: [RouterModule]
})
export class TaxAuditSelectLine { }