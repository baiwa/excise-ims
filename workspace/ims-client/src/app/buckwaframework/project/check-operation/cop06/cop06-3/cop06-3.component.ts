import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';
declare var $: any;
@Component({
  selector: 'app-cop06-3',
  templateUrl: './cop06-3.component.html',
  styleUrls: ['./cop06-3.component.css']
})
export class Cop063Component implements OnInit {
  breadcrumb: BreadCrumb[];
  constructor(private authService: AuthService) {
    this.breadcrumb = [
      { label: "รายงานการตรวจปฏิบัติการ", route: "#" },
      { label: "รายงานการตรวจปฏิบัติการรับสินค้าสำเร็จรูป", route: "#" },
    ];
   }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-06030');
    this.hidedata();
  }


  showdata() {
    $("#showData").show();
  }
  hidedata() {
    $("#showData").hide();
  }
}
