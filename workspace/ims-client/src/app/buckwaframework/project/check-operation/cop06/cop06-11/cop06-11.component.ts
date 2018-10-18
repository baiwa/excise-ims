import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';

@Component({
  selector: 'app-cop06-11',
  templateUrl: './cop06-11.component.html',
  styleUrls: ['./cop06-11.component.css']
})
export class Cop0611Component implements OnInit {

  breadcrumb: BreadCrumb[];
  constructor(private authService: AuthService) { 
    this.breadcrumb = [
      { label: "รายงานการตรวจปฏิบัติการ", route: "#" },
     { label: "รายงานการตรวจปฏิบัติการตรวจนับสินค้าคงเหลือ", route: "#" },
    ];

  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-06011');
  }

}
