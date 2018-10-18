import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';

@Component({
  selector: 'app-cop06-6',
  templateUrl: './cop06-6.component.html',
  styleUrls: ['./cop06-6.component.css']
})
export class Cop066Component implements OnInit {
  breadcrumb: BreadCrumb[];

  constructor(private authService: AuthService) {
    this.breadcrumb = [
      { label: "รายงานการตรวจปฏิบัติการ", route: "#" },
     { label: "รายงานการตรวจปฏิบัติการตรวจสอบปริมาณการชำระภาษี", route: "#" },
    ];

   }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-06060');
  }

}
