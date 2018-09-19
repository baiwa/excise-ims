import { Routes, RouterModule } from "@angular/router";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { NgModule } from "@angular/core";

import { HomePage } from "./home";
import { Buttons } from "./buttons/buttons";
import { Segments } from "./segments/segments";
import { Forms } from "./forms/forms";
import { BreadCrump } from "projects/pages/home/breadcrump/breadcrump";
import { BreadcrumbModule } from "components/breadcrumb/breadcrumb.module";

const routes: Routes = [
  { path: "", component: HomePage },
];

@NgModule({
  imports: [
      RouterModule.forChild(routes),
      CommonModule,
      FormsModule,
      BreadcrumbModule
    ],
  declarations: [
      HomePage,
      Buttons,
      Segments,
      Forms,
      BreadCrump
  ],
  exports: [RouterModule]
})

export class HomeModule { }