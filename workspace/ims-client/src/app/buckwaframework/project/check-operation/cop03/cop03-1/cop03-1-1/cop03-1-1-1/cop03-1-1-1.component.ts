import { Component, OnInit } from '@angular/core';
import { BreadCrumb } from 'models/breadcrumb';

@Component({
  selector: 'app-cop03-1-1-1',
  templateUrl: './cop03-1-1-1.component.html',
  styleUrls: ['./cop03-1-1-1.component.css']
})
export class Cop03111Component implements OnInit {
  breadcrumb: BreadCrumb[];

  constructor() {
    this.breadcrumb = [
      { label: "ค้นหาหนังสือขออนมุัติเดินทางไปราชการ", route: "#" },
      { label: "ผลการค้นหาหนังสือขออนุมัติเดินทางไปราชการ", route: "#" },
      
    ];
   }

  ngOnInit() {
  }

}
