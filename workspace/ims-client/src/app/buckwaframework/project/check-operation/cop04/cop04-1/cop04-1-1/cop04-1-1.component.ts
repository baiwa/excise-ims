import { Component, OnInit } from '@angular/core';
import { BreadCrumb } from 'models/breadcrumb';

@Component({
  selector: 'app-cop04-1-1',
  templateUrl: './cop04-1-1.component.html',
  styleUrls: ['./cop04-1-1.component.css']
})
export class Cop0411Component implements OnInit {

  breadcrumb: BreadCrumb[];
  constructor() { 
    this.breadcrumb = [     
     { label: "บันทึกผลการตรวจปฏิบัติการ", route: "#" },
    ];

  }

  ngOnInit() {
  }

}
