import { Component, OnInit, AfterViewInit } from '@angular/core';
import { Int0616Service } from './int06-16.service';
import { BreadCrumb } from 'models/breadcrumb';
declare var $: any;
@Component({
  selector: 'app-int06-16',
  templateUrl: './int06-16.component.html',
  styleUrls: ['./int06-16.component.css'],
  providers: [Int0616Service]
})
export class Int0616Component implements OnInit, AfterViewInit {
  breadcrumb: BreadCrumb[] = [
    { label: "ตรวจสอบภายใน", route: "#" },
    { label: "ตรวจสอบเบิกจ่าย", route: "#" },
    { label: "รายงานการตรวจสอบ", route: "#" }
  ];
  constructor(
    private myService: Int0616Service
  ) { }

  ngOnInit() {
  }
  ngAfterViewInit() {
    this.callDatatable1();
    this.callDatatable2();
  }
  callDatatable1 = () => {
    this.myService.callDatatable1();
  }
  callDatatable2 = () => {
    this.myService.callDatatable2();
  }
}
