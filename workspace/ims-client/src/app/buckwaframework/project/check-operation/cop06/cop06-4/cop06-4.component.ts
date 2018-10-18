import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';

@Component({
  selector: 'app-cop06-4',
  templateUrl: './cop06-4.component.html',
  styleUrls: ['./cop06-4.component.css']
})
export class Cop064Component implements OnInit {
  breadcrumb: BreadCrumb[];
  constructor(private authService: AuthService) { 
    this.breadcrumb = [
      { label: "รายงานการตรวจปฏิบัติการ", route: "#" },
     { label: "รายงานการตรวจปฏิบัติการวัตถุดิบที่ขอลดหย่อนภาษีที่ยื่นต่อกรมสรรพสามิต (ภส.05 - 02)", route: "#" },
    ];
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-06040');
  }

}
