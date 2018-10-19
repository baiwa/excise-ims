import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';
declare var $: any;
@Component({
  selector: 'app-cop02-1-1',
  templateUrl: './cop02-1-1.component.html',
  styleUrls: ['./cop02-1-1.component.css']
})
export class Cop0211Component implements OnInit {
  breadcrumb: BreadCrumb[];
  constructor(  private authService: AuthService) {

    this.breadcrumb = [
      { label: "รวบรวมข้อมูลเพื่อเตรียมออกตรวจปฏิบัติการ", route: "#" },
      { label: "ข้อมูลวิเคราะห์รายได้ ตามแผนการตรวจสอบภาษี", route: "#" },
      { label: "เพิ่มข้อมูลภายนอก", route: "#" },
    ];
   }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-02110');
    $('.ui.radio.checkbox')
  .checkbox()
;
  }
  

}
