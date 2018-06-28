import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common"
import { UserManagementDetailPage } from './userManagement-detail';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../common/services';

const routes: Routes = [
    { path: '', component: UserManagementDetailPage, canActivate: [AuthGuard] },
    { path: ':id', component: UserManagementDetailPage, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule],
  declarations: [UserManagementDetailPage],
  exports: [RouterModule]
})
export class UserManagementDetailPageModule { }