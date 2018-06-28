import { Component, OnInit } from "@angular/core";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { ExciseService } from "../../../../../common/services/excise.service";
import { TextDateTH, digit } from "../../../../../common/helper/datepicker";
import { CurrencyPipe } from "@angular/common";

declare var jQuery: any;
declare var $: any;
@Component({
  selector: "app-send-line-user",
  templateUrl: "./send-line-user.component.html"
})
export class SendLineUserComponent implements OnInit {
  sendLineUser: any;
  private listItem: any[];
  before: any;
  last: any;
  month: any;
  from: any;
  analysNumber: any;
  exciseProductType: any;
  flag: any;
  coordinates: any;
  coordinatesArr: any;

  constructor(
    private route: ActivatedRoute,
    private ex: ExciseService,
    private router: Router,
    private ajax: AjaxService
  ) {}

  ngOnInit() {
    const URL = "working/test/getCoordinates";
    this.ajax.post(URL, {}, res => {
      this.coordinatesArr = res.json();
    });

    //call ExciseService
    var { before, last, from, month, currYear } = this.ex.getformValues();
    var { analysNumber } = this.ex.getformNumber();

    //set values
    this.before = before;
    this.last = last;
    this.from = from;
    this.month = month;
    this.analysNumber = analysNumber;
    console.log("analysNumber: ", this.analysNumber);

    //split function
    var from_split = this.from.split("/");

    //default month & year
    var month = from_split[0];
    var year_before = from_split[1];

    var m = parseInt(month) + 1;
    var mm = parseInt(this.month);
    var yy = parseInt(year_before);

    var items: string[] = [];
    for (var i = 1; i <= mm; i++) {
      m = m - 1;
      if (m == 0) {
        m = 12;
        yy = yy - 1;
      }
      items.push(
        '<th style="text-align: center !important">' +
          TextDateTH.monthsShort[m - 1] +
          " " +
          (yy + "").substr(2) +
          "</th>"
      );
    }

    var trHeaderColumn = "";
    for (var i = items.length - 1; i >= 0; i--) {
      trHeaderColumn += items[i];
    }
    document.getElementById("trDrinamic").innerHTML =
      '<tr><th rowspan="2" style="text-align: center !important">ลำดับ</th> ' +
      '<th rowspan="2" style="text-align: center !important">ทะเบียนสรรพสามิต เดิม/ใหม่</th> ' +
      '<th rowspan="2" style="text-align: center !important">ชื่อผู้ประกอบการ</th> ' +
      '<th rowspan="2" style="text-align: center !important">ชื่อโรงอุตสาหกรรม/สถานบริการ</th> ' +
      '<th rowspan="2" style="text-align: center !important">พื้นที่</th> ' +
      '<th colspan="2" style="text-align: center !important">การชำระภาษีในสภาวะปกติ (บาท)</th> ' +
      '<th rowspan="2" style="text-align: center !important">เปลี่ยนแปลง (ร้อยละ)</th> ' +
      '<th rowspan="2" style="text-align: center !important">ชำระภาษี(เดือน)</th> ' +
      '<th colspan="3" style="text-align: center !important">การตรวจสอบภาษีย้อนหลัง 3 ปีงบประมาณ</th> ' +
      '<th rowspan="2" style="text-align: center !important">ภาค</th> ' +
      '<th rowspan="2" style="text-align: center !important">พิกัด</th> ' +
      '<th rowspan="2" style="text-align: center !important">ที่อยู่โรงอุตสาหกรรม/สถานบริการ</th> ' +
      '<th rowspan="2" style="text-align: center !important">ทุนจดทะเบียน</th> ' +
      '<th rowspan="2" style="text-align: center !important">สถานะ/วันที่</th> ' +
      '<th colspan="' +
      this.month / 2 +
      '" style="text-align: center !important">การชำระภาษี ' +
      this.month / 2 +
      " เดือนแรก</th> " +
      '<th colspan="' +
      this.month / 2 +
      '" style="text-align: center !important">การชำระภาษี ' +
      this.month / 2 +
      " เดือนหลัง </th> " +
      "</tr>" +
      '<tr><th style="border-left: 1px solid rgba(34,36,38,.1);">' +
      this.month / 2 +
      " เดือนแรก</th>" +
      '<th style="text-align: center !important">' +
      this.month / 2 +
      " เดือนหลัง </th>" +
      '<th style="text-align: center !important">' +
      (currYear - 3) +
      "</th>" +
      '<th style="text-align: center !important">' +
      (currYear - 2) +
      "</th>" +
      '<th style="text-align: center !important">' +
      (currYear - 1) +
      "</th>" +
      trHeaderColumn +
      "</tr>";

    this.initDatatable();
  }

  ngAfterViewInit() {}

  initDatatable(): void {
    var d = new Date();
    const URL = AjaxService.CONTEXT_PATH + "/filter/exise/list";
    console.log(URL);
    console.log(this.analysNumber);
    var json = "";
    json += ' { "lengthChange": false, ';
    json += ' "searching": false, ';
    json += ' "select": true, ';
    json += ' "ordering": true, ';
    // json += ' "pageLength": 10, ';
    json += ' "processing": true, ';
    json += ' "serverSide": true, ';
    json += ' "paging": false, ';
    // json += ' "pagingType": "full_numbers", ';
    json += " ";
    json += ' "ajax": { ';
    json += ' "type": "POST", ';
    json += ' "url": "' + URL + '", ';
    json += ' "data": { ';
    json += ' "paging": false, ';
    json += ' "flag": "' + (this.flag == undefined ? "N" : this.flag) + '", ';
    json +=
      ' "productType": "' +
      (this.coordinates == undefined ? "" : this.coordinates) +
      '", ';
    json += ' "analysNumber": "' + this.analysNumber + '" ';
    json += " } ";
    json += " }, ";
    json += ' "columns": [ ';
    json += ' { "data": "worksheetHeaderId","className":"center" }, ';
    json += ' { "data": "exciseId","className":"center" }, ';
    json += ' { "data": "companyName" }, ';
    json += ' { "data": "companyName" }, ';
    json += ' { "data": "exciseOwnerArea" }, ';
    json += ' { "data": "firstMonth" ,"className":"center" }, ';
    json += ' { "data": "lastMonth","className":"center" }, ';
    json += ' { "data": "percentage","className":"center" }, ';
    json += ' { "data": "totalMonth" ,"className":"center"}, ';
    json += ' { "data": "no1" }, ';
    json += ' { "data": "no2" }, ';
    json += ' { "data": "no3" }, ';
    json += ' { "data": "exciseOwnerArea1" }, ';
    json += ' { "data": "productType" }, ';
    json += ' { "data": "factoryAddress" }, ';
    json += ' { "data": "registeredCapital" }, ';
    json += ' { "data": "status" }, ';

    for (var i = 0; i < this.month; i++) {
      if (i != this.month - 1) {
        json += ' { "data": "amount' + (i + 1) + '" ,"className":"center"}, ';
      } else {
        json += ' { "data": "amount' + (i + 1) + '" ,"className":"center"} ';
      }
    }
    json += "] } ";
    console.log(json);
    let jsonMaping = JSON.parse(json);
    this.sendLineUser = $("#sendLineUser").DataTable(jsonMaping);
    // on init table
    $("#sendLineUser tbody tr").css({
      "background-color": "white",
      cursor: "pointer"
    });

    // on click row
    $("#sendLineUser tbody").on("click", "tr", function() {
      $("#exciseBtn").prop("disabled", false);
      $("#sendLineUser tbody tr").css({
        "background-color": "white",
        cursor: "pointer"
      });
      (<HTMLInputElement>document.getElementById("exciseId")).value = $(this)
        .children()
        .toArray()[1].innerHTML;
      $(this).css("background-color", "rgb(197,217,241)");
    });
  }

  linkToDetail() {
    this.router.navigate(["/add-external-data/add-data"], {
      queryParams: {
        id: (<HTMLInputElement>document.getElementById("exciseId")).value,
        num: this.analysNumber
      }
    });
  }

  changeCoordinates = () => {
    this.coordinates = (<HTMLInputElement>(
      document.getElementById("coordinates")
    )).value;
    this.sendLineUser.destroy();
    this.initDatatable();
  };
}
