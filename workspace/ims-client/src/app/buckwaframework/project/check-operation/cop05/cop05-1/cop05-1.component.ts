import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';

@Component({
  selector: 'app-cop05-1',
  templateUrl: './cop05-1.component.html',
  styleUrls: ['./cop05-1.component.css']
})
export class Cop051Component implements OnInit {
  
  breadcrumb: BreadCrumb[];
  constructor(private authService: AuthService) {
    this.breadcrumb = [
      { label: "รายงานผลการตรวจปฏิบัติการ", route: "#" },
     { label: "รายงานการตรวจปฏิบัติการคำนวณภาษีที่ต้องชำระเพิ่ม", route: "#" },
    ];

   }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-05010');
  }

}
