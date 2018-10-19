import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';

@Component({
  selector: 'app-cop05-1-1',
  templateUrl: './cop05-1-1.component.html',
  styleUrls: ['./cop05-1-1.component.css']
})
export class Cop0511Component implements OnInit {
  breadcrumb: BreadCrumb[];
  constructor( private authService: AuthService) {
    this.breadcrumb = [
      { label: "จัดทำแผนการตรวจปฏิบัติการ", route: "#" },
     { label: "จัดทำแผนการตรวจปฏิบัติการ", route: "#" },
    ];
   }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-05110');
  }

}
