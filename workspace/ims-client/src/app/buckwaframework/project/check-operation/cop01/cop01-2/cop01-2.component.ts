import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { TextDateTH, digit, formatter } from "../../../../common/helper/datepicker";
import { AjaxService } from "../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../common/services/message-bar.service";
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';
declare var $: any;
@Component({
  selector: 'app-cop01-2',
  templateUrl: './cop01-2.component.html',
  styleUrls: ['./cop01-2.component.css']
})
export class Cop012Component implements OnInit {
  year: any;
  formatter1: any;
  planStart:any;
  breadcrumb: BreadCrumb[];
  
  constructor(private router: Router,
    private route: ActivatedRoute,
    private ajax: AjaxService,
    private messageBarService: MessageBarService,
    private authService: AuthService
    
  ) {
    this.formatter1 = formatter('ดป');
    this.breadcrumb = [
      { label: "ค้นหาแผนการตรวจปฏิบัติการ", route: "#" },
      { label: "จัดทำแผนการตรวจปฏิบัติการ", route: "#" },
      { label: "3", route: "#" },
    ];
   }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-01020');

    $("#calendar").calendar({

      type: "month",
      text: TextDateTH,
      formatter: this.formatter1
    });
  }
  search() {
    this.planStart =  $("#planStart").val();
    console.log(this.planStart);
    this.router.navigate(["/cop01/3"], {
      queryParams: { planStart: this.planStart }
    });
  }
  

}
