import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';

@Component({
  selector: 'app-cop06-13',
  templateUrl: './cop06-13.component.html',
  styleUrls: ['./cop06-13.component.css']
})
export class Cop0613Component implements OnInit {
  breadcrumb: BreadCrumb[];
  constructor(private authService: AuthService) {
    this.breadcrumb = [
      { label: "รายงานการตรวจปฏิบัติการ", route: "#" },
     { label: "รายงานการตรวจปฏิบัติการคำนวณภาษีที่ต้องชำระเพิ่ม", route: "#" },
    ];

	
   }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-06013');
  }

}
