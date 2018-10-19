import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';

@Component({
  selector: 'app-cop06-5',
  templateUrl: './cop06-5.component.html',
  styleUrls: ['./cop06-5.component.css']
})
export class Cop065Component implements OnInit {
  breadcrumb: BreadCrumb[];

  constructor(private authService: AuthService) {
    this.breadcrumb = [
      { label: "รายงานการตรวจปฏิบัติการ", route: "#" },
     { label: "รายงานการตรวจปฏิบัติการจ่ายสินค้าสำเร็จรูปต่างประเทศ", route: "#" },
    ];
   }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-06050');
  }

}
