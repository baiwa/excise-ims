import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { AjaxService } from '../../../../common/services';
import { MessageBarService } from 'app/buckwaframework/common/services';
import { BreadCrumb } from '../../../../common/models';

@Component({
  selector: 'app-int07-6',
  templateUrl: './int07-6.component.html',
  styleUrls: ['./int07-6.component.css']
})
export class Int076Component implements OnInit {
  breadcrumb: BreadCrumb[];

  constructor(
    private router: Router,
    private ajax: AjaxService,
    private message: MessageBarService) { 
      this.breadcrumb = [
        { label: "ตรวจสอบภายใน", route: "#" },
        { label: "ตรวจสอบบัญชี", route: "#" },
        { label: "ตรวจสอบการนำส่งเงินบัญชีเจ้าหนี้ อปท.", route: "#" }
      ];
    }

  ngOnInit() {
  }

  checkData() {
    console.log("ไปหน้าตรวจสอบข้อมูล");
    this.router.navigate(["/int07/6/1"], {
      
    });
  }

}
