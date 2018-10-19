import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';
declare var jQuery: any;
declare var $: any;
@Component({
  selector: 'app-cop06-1-2',
  templateUrl: './cop06-1-2.component.html',
  styleUrls: ['./cop06-1-2.component.css']
})
export class Cop0612Component implements OnInit {

  breadcrumb: BreadCrumb[];
  constructor(private authService: AuthService) { 
    this.breadcrumb = [
      { label: "รายงานการตรวจปฏิบัติการ", route: "#" },
      { label: "	รายงานการตรวจปฏิบัติการตรวจนับวัตถุดิบคงเหลือ", route: "#" },
    ];
  }


  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-06120');
    this.hidedata();
  }

  showdata() {
    $("#showData").show();
  }
  hidedata() {
    $("#showData").hide();
  }

}
