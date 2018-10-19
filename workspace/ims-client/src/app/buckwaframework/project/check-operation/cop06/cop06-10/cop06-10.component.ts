import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';

@Component({
  selector: 'app-cop06-10',
  templateUrl: './cop06-10.component.html',
  styleUrls: ['./cop06-10.component.css']
})
export class Cop0610Component implements OnInit {
  breadcrumb: BreadCrumb[];
  constructor(  private authService: AuthService) { 
    this.breadcrumb = [
      { label: "รายงานการตรวจปฏิบัติการ", route: "#" },
     { label: "รายงานการตรวจปฏิบัติการตรวจสอบสถานะสมาชิก", route: "#" },
    ];
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-06010');
  }

}
