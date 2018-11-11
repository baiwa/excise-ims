import { Component, OnInit } from "@angular/core";
import { MessageBarService } from "../../../../common/services/message-bar.service";
import { Router } from "@angular/router";
import { ExciseService } from "../../../../common/services/excise.service";
import { AjaxService } from "../../../../common/services/ajax.service";
import { TextDateTH, digit, formatter } from "../../../../common/helper/datepicker";
import { AuthService } from "app/buckwaframework/common/services";
import { BreadCrumb } from "app/buckwaframework/common/models";


declare var $: any;
@Component({
  selector: 'app-cop01-1',
  templateUrl: './cop01-1.component.html',
  styleUrls: ['./cop01-1.component.css']
})
export class Cop011Component implements OnInit {
  numbers: number[];
  operPlanId: any;
  analysisId: any;
  minisFinanceTarget: any;
  minisFinanceStrat: any;
  minisFinanceExcise: any;
  objective1:any;
  objective2:any;
  objective3:any;
  objective4:any;
  objective5:any;
  strategy:any;           
  keyIndicators:any;
  planner:any;
  primaryResponse:any;
  primaryName:any;
  primaryPosition:any;
  approver:any;
  approverName:any;
  approverPosition:any;
  planStart:any;
  planEnd:any;
  phone:any;
  planDetail:any;
  planName:any;
  formatter1: any;
  formatter2: any;
  month: any;
  selectedStartMonth: any;
  selectedSEndMonth: any;
  selectStartDateObj: any;
  selectEndDateObj: any;
  date: any;
  date1:any;
  date2:any;
  date3:any;
  breadcrumb: BreadCrumb[];

  constructor( private ajax: AjaxService, 
    private messageBarService: MessageBarService,  
    private router: Router, 
    private ex: ExciseService,
    private authService: AuthService ) { 
    this.selectedStartMonth = null;
    this.selectedSEndMonth = null;
    this.selectStartDateObj = null;
    this.selectEndDateObj = null;
    this.date1 = null;
    this.date2 = null;
    this.formatter1 = formatter('ดป');
    this.formatter2 = formatter('ดป');
    this.breadcrumb = [
      { label: "จัดทำแผนการตรวจปฏิบัติการ", route: "#" },
      { label: "2", route: "#" },
      { label: "3", route: "#" },
    ];

  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-01010');
    this.numbers = [];
    this.minisFinanceTarget = "";
    this.onAddField();  
    this.date = new Date(); 
    var month = this.date.getFullYear();
    this.planName = "แผนปฏิบัติการประจำปี : "+month;
    console.log(this.date);
    console.log(this.planName);

   

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
      type: "month",
      text: TextDateTH,
      formatter: this.formatter2,
      onChange: (date) => {
     this.selectedSEndMonth = date.getMonth();
     this.selectEndDateObj = date;
      }
    });
  
  }

  saveOaOperPlan(){
  
    const URL = "oa/cop011/saveOaOperPlan";

    this.ajax.post(URL, {
      operPlanId: this.operPlanId,
      analysisId: this.analysisId,
      minisFinanceTarget: this.minisFinanceTarget,
      minisFinanceStrat: this.minisFinanceStrat,
      minisFinanceExcise: this.minisFinanceExcise,
      objective1: $('#objective1').val(),
      objective2: $('#objective2').val(),
      objective3: $('#objective3').val(),
      objective4: $('#objective4').val(),
      objective5: $('#objective5').val(),
      strategy: this.strategy,
      keyIndicators: this.keyIndicators,
      planner: this.planner,
      primaryResponse: this.primaryResponse,
      primaryName: this.primaryName,
      primaryPosition: this.primaryPosition,
      approver: this.approver,
      approverName: this.approverName,
      approverPosition: this.approverPosition,
      planStart: $('#calendarraw').val(),
      planEnd: $('#calendar1raw').val(),
      phone: this.phone,
      planDetail: this.planDetail,
      planName: this.planName 
    }, res=>{
      var message = res.json();
      this.messageBarService.successModal(message.messageTh, "สำเร็จ");
    }, errRes => {
      var message = errRes.json();
      this.messageBarService.errorModal(message.messageTh);

    });

  }

   onAddField = () => {
     console.log(this.numbers);
    let num = this.numbers.length;
    if (num < 5) {
      this.numbers.push(num + 1);
    } else {
      this.messageBarService.errorModal(
        "ไม่สามารถเพิ่มวัตถุประสงค์ได้เกิน 5 ข้อ",
        "เกิดข้อผิดพลาด"
      );
    }
  };

  onDelField = index => {
    this.numbers.splice(index, 1);
  };

  reset = () => {
    $('#objective1').val("");
    this.numbers.splice(1,4);
    this.approver ="";
    this.approverName ="";
    this.approverPosition ="";
    this.keyIndicators ="";
    this.minisFinanceExcise ="";
    this.minisFinanceStrat ="";
    this.minisFinanceTarget ="";
    this.phone ="";
    this.planDetail ="";
    this.planEnd ="";
    this.planStart ="";
    this.planner ="";
    this.primaryName ="";
    this.primaryPosition ="";
    this.primaryResponse ="";
    this.strategy ="";
  };


}



