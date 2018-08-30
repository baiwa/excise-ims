import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Location, NgIf } from "@angular/common";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { Alert } from "../../../../../../../../node_modules/@types/selenium-webdriver";
import { log } from "util";

declare var $: any;
declare var $: any;

@Component({
  selector: 'app-int08-2-5',
  templateUrl: './int08-2-5.component.html',
  styleUrls: ['./int08-2-5.component.css']
})
export class Int0825Component implements OnInit {
  
  riskInfPaperName: any;
  budgetYear: any;
  userCheck: any;
  
  riskAssInfHdrList :any;
  columnList: any[];
  percentList: any[];
  
  datatable: any;
  isConditionShow: any;
  ispercent: any;
  constructor(
    private router: Router,
    private ajax: AjaxService,
    private messageBarService: MessageBarService,
    private route: ActivatedRoute
  ) { 
    this.isConditionShow = false;
    this.ispercent = false;
  }

  ngOnInit() {
    this.budgetYear = this.route.snapshot.queryParams["budgetYear"];
    this.columnList = [];
    this.percentList = [];
    this.queryColumnNameListAndSetColumn();
  }
  ngAfterViewInit() {
  }

  queryColumnNameListAndSetColumn() {
    var url = "ia/int082/findByBudgetYear";

    this.ajax.post(url, { budgetYear: this.budgetYear }, res => {
      this.riskAssInfHdrList = res.json();

      var  riskAssInfHdr = res.json();
      console.log( "data : ", riskAssInfHdr);

      for(let i = 0; i <riskAssInfHdr.length; i++){
        const element = riskAssInfHdr[i];
        this.columnList.push(element.riskAssInfHdrName);
        this.percentList.push(0);
      }

      var trHTML = '<tr><th rowspan="2">ลำดับ</th> <th rowspan="2">ระบบสารสนเทศฯ ของกรมสรรพสามิต</th>';
      this.columnList.forEach(element => {
         console.log(element);
         trHTML += '<th rowspan="2">' + element + '</th>';
       });
         trHTML += '<th rowspan="2"  >รวม</th><th colspan="2">ประเมินความเสี่ยง</th></tr><tr><th style="text-align: center !important; border-left: 1px solid rgba(34,36,38,.1) !important">RL</th><th>แปลค่า</th></tr>';
          $("#trColumn").html(trHTML);
         this.initDatatable();
    }, errRes => {
      console.log(errRes);
    });
  }

  initDatatable(): void {
    var url = 'ia/int082/searchFullRiskByBudgetYear';
    var hrmlTr = '';
    this.ajax.post(url, { budgetYear: this.budgetYear, riskAssInfHdrNameList: this.columnList }, res => {

      res.json().forEach(element => {
        console.log(element);
        hrmlTr += "<tr>";
        hrmlTr += "<td>" + element.id + "</td>";
        hrmlTr += "<td>" + element.infName + "</td>";
       
        element.rl.forEach(rl => {
          hrmlTr += "<td>" + rl + "</td>";
        });
        hrmlTr += "<td>" + element.sumRiskCost + "</td>";
        hrmlTr += "<td></td>";
        hrmlTr += "<td></td>";
        hrmlTr += "</tr>";
      });

      $("#tbody").html(hrmlTr);
    }, errRes => {
      console.log(errRes);
    });
  }

  modalConditionRL() {
    this.isConditionShow = true;
  }

  closeConditionRL(e) {
    this.isConditionShow = false;
  }

  getConditionShow() {
    return this.isConditionShow;
  }

  modalpercent() {
    this.ispercent = true;
  }

  closepercent(e) {
    this.ispercent = false;
  }

  getpercent() {
    return this.ispercent;
  }

  cancelFlow() {
    this.messageBarService.comfirm(foo => {
      // let msg = "";
      if (foo) {
        this.router.navigate(["/int08/2/2"], {
          queryParams: { budgetYear: this.budgetYear }
        });
      }
    }, "คุณต้องการยกเลิกการทำงานใช่หรือไม่ ? ");
  }

  savePercent() {
    var sumpercen = 0;
    this.riskAssInfHdrList.forEach(element => {
      sumpercen += element.percent;
    });
    if (sumpercen != 100) {
      this.messageBarService.errorModal("น้ำหนักต้องรวมกันแล้วเป็น 100 %");
      return "";
    }
    var url = 'ia/int082/updateRiskPercent';
    this.ajax.post(url, { riskAssInfHdrList: this.riskAssInfHdrList }, res => {
      this.riskAssInfHdrList = res.json();
      this.messageBarService.successModal("บันทึกน้ำหนักความเสี่ยงสำเร็จ");
      this.ispercent = false;

    });
  }

}

