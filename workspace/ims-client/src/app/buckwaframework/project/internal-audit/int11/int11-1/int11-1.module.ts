import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "../../../../common/services";
import { FormsModule } from "@angular/forms";
import { Int111Component } from "./int11-1.component";
import { Int1111Component } from "./int11-1-1/int11-1-1.component";
import { BreadcrumbModule } from "../../../../common/components";

const routes: Routes = [
  { path: "", component: Int111Component, canActivate: [AuthGuard] },
  { path: "1", component: Int1111Component, canActivate: [AuthGuard]  }
];

@NgModule({
  imports: [RouterModule.forChild(routes), CommonModule, FormsModule,BreadcrumbModule],
  declarations: [
    Int111Component, 
    Int1111Component
  ],
  exports: [RouterModule]
})
export class Int111Module {}
