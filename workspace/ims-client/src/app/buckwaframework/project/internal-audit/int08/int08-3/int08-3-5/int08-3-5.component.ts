import { Component, OnInit, AfterViewInit } from "@angular/core";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { AuthService } from "services/auth.service";
declare var $: any;
@Component({
  selector: "int08-3-5",
  templateUrl: "./int08-3-5.component.html",
  styleUrls: ["./int08-3-5.component.css"]
})
export class Int0835Component implements OnInit {

  riskHrdPaperName: any;
  budgetYear: any;
  userCheck: any;
  columnList: any[];
  percentList: any[];
  datatable: any;
  isConditionShow: any;
  ispercent: any;
  riskAssRiskWsHdrList: any;
  constructor(private router: Router,
    private ajax: AjaxService,
    private authService: AuthService,
    private messageBarService: MessageBarService,
    private route: ActivatedRoute) {
    this.isConditionShow = false;
    this.ispercent = false;
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-08350');

    this.columnList = [];
    this.percentList = [];
    this.budgetYear = this.route.snapshot.queryParams["budgetYear"];
    this.queryColumnNameListAndSetColumn();
  }

  queryColumnNameListAndSetColumn() {
    var url = "ia/int083/findByBudgetYear";

    this.ajax.post(url, { budgetYear: this.budgetYear }, res => {
      console.log(riskAssRiskWsHdr);
      var riskAssRiskWsHdr = res.json();
      this.riskAssRiskWsHdrList = res.json();

      for (let i = 0; i < riskAssRiskWsHdr.length; i++) {
        const element = riskAssRiskWsHdr[i];
        this.columnList.push(element.riskHdrName);
        this.percentList.push(0);
      }

      var trHTML = '<tr><th rowspan="2" style="text-align: center !important">ลำดับ</th><th rowspan="2" style="text-align: center !important">หน่วยงาน</th>';
      this.columnList.forEach(element => {
        console.log(element);
        trHTML += '<th rowspan="2" style="text-align: center !important">' + element + '</th>';
      });
      trHTML += '<th rowspan="2"  style="text-align: center !important">รวม</th><th colspan="2" style="text-align: center !important">ประเมินความเสี่ยง</th></tr><tr><th style="text-align: center !important; border-left: 1px solid rgba(34,36,38,.1) !important">RL</th><th style="text-align: center !important">แปลค่า</th></tr>';
      $("#trColumn").html(trHTML);
      this.initDatatable();
    }, errRes => {
      console.log(errRes);
    });
  }


  initDatatable(): void {
    var url = 'ia/int083/searchFullRiskByBudgetYear';

    var hrmlTr = '';
    this.ajax.post(url, { budgetYear: this.budgetYear, riskHrdNameList: this.columnList }, res => {

      res.json().forEach(element => {
        console.log(element);
        hrmlTr += "<tr style='text-align: center !important'>";
        hrmlTr += "<td >" + element.id + "</td>";
        hrmlTr += "<td style='text-align: left !important'>" + element.departmentName + "</td>";
        element.rl.forEach(rl => {
          hrmlTr += "<td style='text-align: right !important'>" + rl + "</td>";
        });
        hrmlTr += "<td style='text-align: right !important'>" + element.sumRiskCost + "</td>";
        hrmlTr += "<td>" + element.rlAll + "</td>";
        hrmlTr += "<td>" + element.valueTranslation + "</td>";
        hrmlTr += "</tr>";
      });

      $("#tbody").html(hrmlTr);
      $('#dataTable_int08_3_12').DataTable({
        scrollX: true
      });
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
        this.router.navigate(["/int08/3/2"], {
          queryParams: { budgetYear: this.budgetYear }
        });
      }
    }, "คุณต้องการยกเลิกการทำงานใช่หรือไม่ ? ");
  }

  savePercent() {
    var sumpercen = 0;
    this.riskAssRiskWsHdrList.forEach(element => {
      sumpercen += element.percent;
    });
    if (sumpercen != 100) {
      this.messageBarService.errorModal("น้ำหนักต้องรวมกันแล้วเป็น 100 %");
      return "";
    }
    var url = 'ia/int083/updateRiskPercent';
    this.ajax.post(url, { riskAssExcAreaHdrList: this.riskAssRiskWsHdrList }, res => {
      this.riskAssRiskWsHdrList = res.json();
      this.messageBarService.successModal("บันทึกน้ำหนักความเสี่ยงสำเร็จ");
      this.ispercent = false;

    });
  }

}


