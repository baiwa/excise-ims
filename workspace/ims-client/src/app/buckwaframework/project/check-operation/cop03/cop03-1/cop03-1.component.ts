import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';

@Component({
  selector: 'app-cop03-1',
  templateUrl: './cop03-1.component.html',
  styleUrls: ['./cop03-1.component.css']
})
export class Cop031Component implements OnInit {

  breadcrumb: BreadCrumb[];

  constructor( private authService: AuthService) {
    this.breadcrumb = [
      { label: "", route: "#" },
     
    ];

   }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-03100');
  }

}
