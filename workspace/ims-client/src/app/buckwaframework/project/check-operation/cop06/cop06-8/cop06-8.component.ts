import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';

@Component({
  selector: 'app-cop06-8',
  templateUrl: './cop06-8.component.html',
  styleUrls: ['./cop06-8.component.css']
})
export class Cop068Component implements OnInit {
  breadcrumb: BreadCrumb[];
  constructor(private authService: AuthService) {
    this.breadcrumb = [
      { label: "รายงานการตรวจปฏิบัติการ", route: "#" },
     { label: "รายงานการตรวจปฏิบัติการตรวจสอบด้านราคา", route: "#" },
    ];

   }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-06080');
  }

}
