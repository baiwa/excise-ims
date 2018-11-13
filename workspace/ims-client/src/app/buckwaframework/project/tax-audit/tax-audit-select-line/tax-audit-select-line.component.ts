import { Component, OnInit } from "@angular/core";
import { AjaxService } from "../../../common/services/ajax.service";
import { Router } from "@angular/router";

import { TextDateTH, digit, formatter } from "../../../common/helper/datepicker";

import { BreadCrumb } from 'models/index';
import { AuthService } from "services/auth.service";
import { MessageBarService } from "services/message-bar.service";

declare var jQuery: any;
declare var $: any;
@Component({
  selector: 'app-tax-audit-select-line',
  templateUrl: './tax-audit-select-line.component.html',
  styleUrls: ['./tax-audit-select-line.component.css']
})
export class TaxAuditSelectLineComponent implements OnInit {

  monthCount: number;
  breadcrumb: BreadCrumb[] = [
    { label: 'ตรวจสอบภาษี', route: '#' },
    { label: 'การคัดเลือกราย', route: '#' },
    { label: 'สร้างกระดาษทำการคัดเลือกราย', route: '#' }
  ];
  selectedStartMonth: any;
  selectedSEndMonth: any;
  selectStartDateObj: any;
  selectEndDateObj: any;
  month: any;
  formatter1: any;
  formatter2: any;
  percenDiffMonth: any;
  constructor(
    private ajax: AjaxService,
    private router: Router,
    private authService: AuthService,
    private messageBarService: MessageBarService,
  ) {

  }


  ngOnInit(): void {
    this.authService.reRenderVersionProgram('TAX-04000');
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");

    this.formatter1 = formatter('ดป');
    this.formatter2 = formatter('ดป');
    $("#calendar").calendar({
      endCalendar: $("#calendar1"),
      maxDate: new Date(),
      type: "month",
      text: TextDateTH,
      formatter: this.formatter1,
      onChange: (date) => {
        this.selectedStartMonth = date.getMonth();
        this.selectStartDateObj = date;
      }
    });
    $("#calendar1").calendar({
      startCalendar: $("#calendar"),
      maxDate: new Date(),
      type: "month",
      text: TextDateTH,
      formatter: this.formatter2,
      onChange: (date) => {
        this.selectedSEndMonth = date.getMonth();
        this.selectEndDateObj = date;
      }
    });
  }



  onSubmit = (event: any) => {

    console.log(this.selectStartDateObj);
    console.log(this.selectEndDateObj);
    console.log(this.monthCount);
    console.log(this.percenDiffMonth);

    if (this.selectStartDateObj == null || this.selectStartDateObj == undefined) {
      this.messageBarService.errorModal("กรุณากรอก \"ระยะเวลาที่ตรวจสอบจาก\" เพื่อสร้างกระดาษทำการคัดเลือกราย");
      return;
    }
    if (this.selectEndDateObj == null || this.selectEndDateObj == undefined) {
      this.messageBarService.errorModal("กรุณากรอก \"ระยะเวลาที่ตรวจสอบถึง\" เพื่อสร้างกระดาษทำการคัดเลือกราย");
      return;
    }

    if (this.monthCount == null || this.monthCount == undefined) {
      this.messageBarService.errorModal("กรุณากรอก \"จำนวนเดือนไม่ชำระภาษีติดต่อกัน\"");
      return;
    }

    if (this.percenDiffMonth == null || this.percenDiffMonth == undefined) {
      this.messageBarService.errorModal("กรุณากรอก \"ร้อยละความแตกต่างของแต่ละเดือน\"");
      return;
    }

    const URL = "taxAudit/selectList/saveToTaPlanSearchRick";
    this.ajax.post(URL, {
      startDate: this.selectStartDateObj.toJSON().split("T")[0],
      endDate: this.selectEndDateObj.toJSON().split("T")[0],
      monthNotPayRisk: this.monthCount,
      percentDiff: this.percenDiffMonth
    }, res => {

      console.log(res.json());


    });
  };










}
