import { Component, OnInit } from '@angular/core';
import { BreadCrumb } from 'models/breadcrumb';


@Component({
  selector: 'app-int08-7',
  templateUrl: './int08-7.component.html',
  styleUrls: ['./int08-7.component.css']
})
export class Int087Component implements OnInit {
  breadcrumb: BreadCrumb[];
  constructor() {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบรายได้", route: "#" },
      { label: "รายงานผลการตรวจสอบรายได้", route: "#" },
    ];
   }

  ngOnInit() {
  }

}
