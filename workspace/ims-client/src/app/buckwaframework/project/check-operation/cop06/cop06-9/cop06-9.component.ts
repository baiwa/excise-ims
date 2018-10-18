import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';

@Component({
  selector: 'app-cop06-9',
  templateUrl: './cop06-9.component.html',
  styleUrls: ['./cop06-9.component.css']
})
export class Cop069Component implements OnInit {
  breadcrumb: BreadCrumb[];
  constructor( private authService: AuthService) { 
    this.breadcrumb = [
      { label: "รายงานการตรวจปฏิบัติการ", route: "#" },
     { label: "รายงานการตรวจปฏิบัติการรตรวจสอบด้านมูลค่า", route: "#" },
    ];

  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-06090');
  }

}
