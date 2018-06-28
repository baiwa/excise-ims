import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../common/services';
import { FormsModule } from '@angular/forms';
import { AddExternalDataComponent } from './add-external-data.component';
import { AddDataComponent } from './add-data/add-data.component';
import { SendLineUserComponent } from './send-line-user/send-line-user.component';

const routes: Routes = [
    { path: '', component: AddExternalDataComponent, canActivate: [AuthGuard] },
    { path: 'add-data', component: AddDataComponent, canActivate: [AuthGuard] },
    { path: 'send-line-user', component: SendLineUserComponent, canActivate: [AuthGuard] },
];

@NgModule({
    imports: [
        RouterModule.forChild(routes),
        CommonModule,
        FormsModule
    ],
    declarations: [
        AddExternalDataComponent,
        AddDataComponent,
        SendLineUserComponent
    ],
    exports: [RouterModule]
})
export class AddExternalDataModule { }