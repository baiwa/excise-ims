import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { Routes, RouterModule } from "@angular/router";
import { Int054Component } from "./int05-4/int05-4.component";
import { Int054AdminComponent } from "./int05-4-admin/int05-4-admin.component";
import { Int0541Component } from "./int05-4/int05-4-1/int05-4-1.component";
import { FormsModule } from "@angular/forms";
import { BreadcrumbModule } from "components/breadcrumb/breadcrumb.module";

const routes: Routes = [
  { path: "1", loadChildren: "./int05-1/int05-1.module#Int051Module" },
  { path: "2", loadChildren: "./int05-2/int05-2.module#Int052Module" },
  { path: "3", loadChildren: "./int05-3/int05-3.module#Int053Module" },
  { path: "4", component: Int054Component },
  { path: "4/admin", component: Int054AdminComponent },
  { path: "4-1", component: Int0541Component }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    FormsModule,
    BreadcrumbModule
  ],
  declarations: [Int054Component, Int054AdminComponent, Int0541Component],
  exports: [RouterModule]
})
export class Int05Module {}
