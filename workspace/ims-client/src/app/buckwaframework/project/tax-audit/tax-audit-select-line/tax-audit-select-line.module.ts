import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../common/services';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BreadcrumbModule } from '../../../common/components';
import { TaxAuditSelectLineComponent } from 'projects/tax-audit/tax-audit-select-line/tax-audit-select-line.component';
import { Tsl010100Component } from './tsl000000/tsl010000/tsl010100/tsl010100.component';
import { DirectivesModule } from 'app/buckwaframework/common/directives/directives.module';
import { Tsl010200Component } from './tsl000000/tsl010000/tsl010200/tsl010200.component';
const routes: Routes = [
    { path: '', component: TaxAuditSelectLineComponent, canActivate: [AuthGuard] },
    { path: 'tsl0101-00', component: Tsl010100Component, canActivate: [AuthGuard] },
    { path: 'tsl0102-00', component: Tsl010200Component, canActivate: [AuthGuard] },

];

@NgModule({
    imports: [
        RouterModule.forChild(routes),
        CommonModule,
        FormsModule,
        BreadcrumbModule,
        ReactiveFormsModule,
        DirectivesModule
    ],
    declarations: [
        TaxAuditSelectLineComponent,
        Tsl010100Component,
        Tsl010200Component
    ],
    exports: [RouterModule]
})
export class TaxAuditSelectLine { }