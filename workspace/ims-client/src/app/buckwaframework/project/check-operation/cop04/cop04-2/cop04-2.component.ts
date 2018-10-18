import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';

@Component({
  selector: 'app-cop04-2',
  templateUrl: './cop04-2.component.html',
  styleUrls: ['./cop04-2.component.css']
})
export class Cop042Component implements OnInit {
  breadcrumb: BreadCrumb[];
  constructor(private authService: AuthService) { 
    this.breadcrumb = [
  
     { label: "จัดทำแผนการตรวจปฏิบัติการ", route: "#" },
    ];

  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-04020');
  }

}
