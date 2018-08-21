import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common"
import { SelectFormComponent } from './select-form.component';

import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../common/services';
import { FormsModule } from '@angular/forms';

const routes: Routes = [
    { path: ':category/:coordinate', component: SelectFormComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule,FormsModule],
  declarations: [SelectFormComponent],
  exports: [RouterModule]
})
export class SelectFormComponentModule { }