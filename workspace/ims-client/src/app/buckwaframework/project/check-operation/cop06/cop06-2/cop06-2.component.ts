import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';

@Component({
  selector: 'app-cop06-2',
  templateUrl: './cop06-2.component.html',
  styleUrls: ['./cop06-2.component.css']
})
export class Cop062Component implements OnInit {
  showData: boolean = false;
  breadcrumb: BreadCrumb[];
  constructor(  private authService: AuthService) { 
    this.breadcrumb = [
      { label: "รายงานการตรวจปฏิบัติการ", route: "#" },
      { label: "รายงานการตรวจปฏิบัติการหาความสัมพันธ์ การเบิกใช้วัตถุดิบกับการรับสินค้าสำเร็จรูป", route: "#" },
     
    
    ];
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-06020');
  }

  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }

}
