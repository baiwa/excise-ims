import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { FormsModule } from '@angular/forms';
import { Int0131Component } from './int01-3-1/int01-3-1.component';
import { Int0132Component } from './int01-3-2/int01-3-2.component';
import { Int0133Component } from './int01-3-3/int01-3-3.component';
import { Int0134Component } from './int01-3-4/int01-3-4.component';
import { Int0135Component } from './int01-3-5/int01-3-5.component';
import { Int0136Component } from './int01-3-6/int01-3-6.component';
import { Int0137Component } from './int01-3-7/int01-3-7.component';

const routes: Routes = [
    { path: '1', component: Int0131Component, canActivate: [AuthGuard] },
    { path: '2', component: Int0132Component, canActivate: [AuthGuard] },
    { path: '3', component: Int0133Component, canActivate: [AuthGuard] },
    { path: '4', component: Int0134Component, canActivate: [AuthGuard] },
    { path: '5', component: Int0135Component, canActivate: [AuthGuard] },
    { path: '6', component: Int0136Component, canActivate: [AuthGuard] },
    { path: '7', component: Int0137Component, canActivate: [AuthGuard] },
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
        Int0133Component,
        Int0134Component,
        Int0135Component,
        Int0136Component,
        Int0137Component
    ],
    exports: [RouterModule]
})
export class Int013Module { }