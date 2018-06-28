import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { FormsModule } from '@angular/forms';
import { Int0131Component } from './int01-3-1/int01-3-1.component';
import { Int0132Component } from './int01-3-2/int01-3-2.component';
import { Int0133Component } from './int01-3-3/int01-3-3.component';

const routes: Routes = [
    { path: '1', component: Int0131Component, canActivate: [AuthGuard] },
    { path: '2', component: Int0132Component, canActivate: [AuthGuard] },
    { path: '3', component: Int0133Component, canActivate: [AuthGuard] }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes),
        CommonModule,
        FormsModule
    ],
    declarations: [
        Int0131Component,
        Int0132Component,
        Int0133Component
    ],
    exports: [RouterModule]
})
export class Int013Module { }