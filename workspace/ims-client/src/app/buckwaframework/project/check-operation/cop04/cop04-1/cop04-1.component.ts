import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';

@Component({
  selector: 'app-cop04-1',
  templateUrl: './cop04-1.component.html',
  styleUrls: ['./cop04-1.component.css']
})
export class Cop041Component implements OnInit {
  breadcrumb: BreadCrumb[];
  constructor(private authService: AuthService) { 
    this.breadcrumb = [    
     { label: "บันทึกผลการตรวจปฏิบัติการ", route: "#" },
    ];

  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-04010');
  }

}
