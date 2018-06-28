import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { FormsModule } from '@angular/forms';
import { Int0141Component } from './int01-4-1/int01-4-1.component';
import { Int0142Component } from './int01-4-2/int01-4-2.component';
import { Int0143Component } from './int01-4-3/int01-4-3.component';
import { Int0144Component } from './int01-4-4/int01-4-4.component';

const routes: Routes = [
    { path: '1', component: Int0141Component, canActivate: [AuthGuard] },
    { path: '2', component: Int0142Component, canActivate: [AuthGuard] },
    { path: '3', component: Int0143Component, canActivate: [AuthGuard] },
    { path: '4', component: Int0144Component, canActivate: [AuthGuard] }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes),
        CommonModule,
        FormsModule
    ],
    declarations: [
        Int0141Component,
        Int0142Component,
        Int0143Component,
        Int0144Component
    ],
    exports: [RouterModule]
})
export class Int014Module { }