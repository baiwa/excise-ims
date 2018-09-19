import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Int0111Component } from './int01-1-1/int01-1-1.component';
import { Int0112Component } from './int01-1-2/int01-1-2.component';
import { Int0113Component } from './int01-1-3/int01-1-3.component';
import { BreadcrumbModule, ModalModule } from 'components/index';
import { Int011Service } from './int01-1.services';
// Modules
// import { DataTablesModule } from 'angular-datatables';

const routes: Routes = [
    { path: '1', component: Int0111Component, canActivate: [AuthGuard] },
    { path: '2', component: Int0112Component, canActivate: [AuthGuard] },
    { path: '3', component: Int0113Component, canActivate: [AuthGuard] }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes),
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        BreadcrumbModule,
        ModalModule
        // DataTablesModule
    ],
    declarations: [
        Int0111Component,
        Int0112Component,
        Int0113Component
    ],
    providers: [Int011Service],
    exports: [RouterModule]
})
export class Int011Module { }