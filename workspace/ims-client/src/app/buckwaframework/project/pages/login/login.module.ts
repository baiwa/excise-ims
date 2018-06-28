import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common"
import { LoginPage } from './login';
import { FormsModule } from '@angular/forms';

import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
    { path: '', component: LoginPage }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule, FormsModule],
  declarations: [LoginPage],
  exports: [RouterModule]
})
export class LoginModule { }