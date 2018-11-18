import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { Routes, RouterModule } from "@angular/router";

const routes: Routes = [
  { path: "1", loadChildren: "./int06-1/int06-1.module#Int061Module" },
  { path: "2", loadChildren: "./int06-2/int06-2.module#Int062Module" },
  { path: "3", loadChildren: "./int06-3/int06-3.module#Int063Module" },
  { path: "4", loadChildren: "./int06-4/int06-4.module#Int064Module" },
  { path: "5", loadChildren: "./int06-5/int06-5.module#Int065Module" },
  { path: "6", loadChildren: "./int06-6/int06-6.module#Int066Module" },
  { path: "7", loadChildren: "./int06-7/int06-7.module#Int067Module" },
  { path: "8", loadChildren: "./int06-8/int06-8.module#Int068Module" },
  { path: "9", loadChildren: "./int06-9/int06-9.module#Int069Module" },
  { path: "10", loadChildren: "./int06-10/int06-10.module#Int0610Module" },
  { path: "11", loadChildren: "./int06-11/int06-11.module#Int0611Module" },
  { path: "12", loadChildren: "./int06-12/int06-12.module#Int0612Module" },
  { path: "13", loadChildren: "./int06-13/int06-13.module#Int0613Module" },
  { path: "14", loadChildren: "./int06-14/int06-14.module#Int0614Module" },
  { path: "15", loadChildren: "./int06-15/int06-15.module#Int0615Module" },
  { path: "16", loadChildren: "./int06-16/int06-16.module#Int0616Module" },
  
];
@NgModule({
  imports: [
    RouterModule.forChild(routes),
    CommonModule,

  ],
  exports: [RouterModule],
  declarations: []
})
export class Int06Module { }
