import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { FormsModule } from '@angular/forms';
import { Int0121Component } from './int01-2-1/int01-2-1.component';
import { Int0122Component } from './int01-2-2/int01-2-2.component';
import { Int0123Component } from './int01-2-3/int01-2-3.component';
import { BreadcrumbModule } from '../../../../common/components';

const routes: Routes = [
    { path: '1', component: Int0121Component, canActivate: [AuthGuard] },
    { path: '2', component: Int0122Component, canActivate: [AuthGuard] },
    { path: '3', component: Int0123Component, canActivate: [AuthGuard] }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes),
        CommonModule,
        FormsModule,
        BreadcrumbModule
    ],
    declarations: [
        Int0121Component,
        Int0122Component,
        Int0123Component
    ],
    exports: [RouterModule]
})
export class Int012Module { }