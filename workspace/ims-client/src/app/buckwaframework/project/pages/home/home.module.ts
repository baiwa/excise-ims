import { Routes, RouterModule } from "@angular/router";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { NgModule } from "@angular/core";
import { HomePage } from "./home";
import { Buttons } from "./buttons/buttons";

const routes: Routes = [
  { path: "", component: HomePage },
];

@NgModule({
  imports: [
      RouterModule.forChild(routes),
      CommonModule,
      FormsModule
    ],
  declarations: [
      HomePage,
      Buttons
  ],
  exports: [RouterModule]
})

export class HomeModule { }