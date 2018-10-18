import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';

@Component({
  selector: 'app-cop06-7',
  templateUrl: './cop06-7.component.html',
  styleUrls: ['./cop06-7.component.css']
})
export class Cop067Component implements OnInit {
  breadcrumb: BreadCrumb[];
  constructor( private authService: AuthService) { 
    this.breadcrumb = [
      { label: "รายงานการตรวจปฏิบัติการ", route: "#" },
     { label: "รายงานการตรวจปฏิบัติการตรวจสอบด้านปริมาณ", route: "#" },
    ];

	
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-06070');
  }

}
