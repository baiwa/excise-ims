import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';

@Component({
  selector: 'app-cop06-12',
  templateUrl: './cop06-12.component.html',
  styleUrls: ['./cop06-12.component.css']
})
export class Cop0612Component implements OnInit {

  breadcrumb: BreadCrumb[];
  constructor(private authService: AuthService) {
    this.breadcrumb = [
      { label: "รายงานการตรวจปฏิบัติการ", route: "#" },
     { label: "รายงานการตรวจปฏิบัติการตรวจสอบมูลค่าขอยกเว้นภาษี", route: "#" },
    ];
   }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-06012');
  }

}
