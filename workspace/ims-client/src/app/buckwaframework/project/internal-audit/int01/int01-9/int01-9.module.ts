import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";



import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "../../../../common/services";
import { Int0191Component } from "projects/internal-audit/int01/int01-9/int01-9-1/int01-9-1.component";
import { Int0192Component } from './int01-9-2/int01-9-2.component';
import { Int0193Component } from './int01-9-3/int01-9-3.component';

const routes: Routes = [
  { path: "1", component: Int0191Component, canActivate: [AuthGuard] },
  { path: "2", component: Int0192Component, canActivate: [AuthGuard] },
  { path: "3", component: Int0193Component, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule],
  declarations: [Int0191Component, Int0192Component, Int0193Component],
  exports: [RouterModule]
})
export class Int019Module {}
