import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common"
import { UserManagementPage } from './userManagement';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../common/services';

const routes: Routes = [
    { path: '', component: UserManagementPage, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule],
  declarations: [UserManagementPage],
  exports: [RouterModule]
})
export class UserManagementPageModule { }