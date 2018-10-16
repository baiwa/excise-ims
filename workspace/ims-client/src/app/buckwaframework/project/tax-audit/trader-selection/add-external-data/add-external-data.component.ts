import { Component, OnInit } from "@angular/core";
import { AjaxService } from "../../../../common/services/ajax.service";
import { Router } from "@angular/router";
import { ExciseService } from "../../../../common/services/excise.service";
import { TextDateTH } from "../../../../common/helper/datepicker";

declare var $: any;
@Component({
  selector: "app-add-external-data",
  templateUrl: "./add-external-data.component.html",
  styleUrls: ["./add-external-data.component.css"]
})
export class AddExternalDataComponent implements OnInit {
  userManagementDt: any;
  before: any;
  last: any;
  month: any;
  from: any;
  analysNumber: any;
  analysNumberArr: any;
  exciseProductType: any;
  flag: any;
  coordinates: any;
  coordinatesArr: any;
  workSheetNumber: any;
  curr: any;
  sector: any;
  sectorArr: any;

  loading: boolean = true;

  constructor(
    private ex: ExciseService,
    private router: Router,
    private ajax: AjaxService
  ) {
    this.flag = "";
    this.analysNumberArr = "";
    this.workSheetNumber = "";
    this.curr = "";
  }

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    $("#exciseBtn").prop("disabled", true);
    //get coordinates in select option
    const URL = "combobox/controller/getCoordinates";
    this.ajax.post(URL, {}, res => {
      this.coordinatesArr = res.json();
    });
    //get Sector in select option
    const URL2 = "combobox/controller/getSector";
    this.ajax.post(URL2, {}, res => {
      this.sectorArr = res.json();
    });

    if (this.ex.getformNumber().analysNumber !== undefined) {
      //call ExciseService
      var { before, last, from, month, currYear } = this.ex.getformValues();
      var { analysNumber } = this.ex.getformNumber();

      //set values
      this.before = before;
      this.last = last;
      this.from = from;
      this.month = month;
      this.analysNumber = analysNumber;
      this.curr = currYear;
      this.createTH(currYear);

      this.initDatatable();
    } else {
      const analysUrl = "combobox/controller/getAnalysNumber";
      this.ajax
        .post(analysUrl, {}, res => {
          this.analysNumberArr = res.json();
        })
        .then(() => {
          this.directAccess(true);
        })
        .then(() => {
          $(".ui.dropdown").dropdown();
        });
    }
  }

  initDatatable(): void {
    const URL = AjaxService.CONTEXT_PATH + "filter/exise/list";
    var json = "";
    json += ' { "lengthChange": true, ';
    json += ' "scrollX": true, ';
    json += ' "searching": false, ';
    json += ' "select": true, ';
    json += ' "ordering": true, ';
    json += ' "pageLength": 10, ';
    json += ' "processing": true, ';
    json += ' "serverSide": true, ';
    json += ' "paging": true, ';
    json += ' "pagingType": "full_numbers", ';
    json += ' "fixedColumns" : { "leftColumns" : 2}, ';
    json += " ";
    json += ' "ajax": { ';
    json += ' "type": "POST", ';
    json += ' "url": "' + URL + '", ';
    json += ' "data": { ';
    json += ' "flag": "' + (this.flag == "" ? "N" : this.flag) + '", ';
    json +=
      ' "productType": "' +
      (this.coordinates == undefined ? "" : this.coordinates) +
      '", ';
    json +=
      ' "sector": "' + (this.sector == undefined ? "" : this.sector) + '", ';
    json += ' "analysNumber": "' + this.analysNumber + '" ';
    json += " } ";
    json += " }, ";
    json += ' "columns": [ ';

    json += ' { "data": "exciseId","className":"center" }, ';
    json += ' { "data": "exciseIdOld","className":"center" }, ';
    json += ' { "data": "companyName" }, ';
    json += ' { "data": "companyName" }, ';
    json += ' { "data": "exciseOwnerArea1" }, ';
    json += ' { "data": "exciseOwnerArea" }, ';
    json += ' { "data": "firstMonth" ,"className":"center" }, ';
    json += ' { "data": "lastMonth","className":"center" }, ';
    json += ' { "data": "percentage","className":"center" }, ';
    json += ' { "data": "totalMonth" ,"className":"center"}, ';
    json += ' { "data": "no1" }, ';
    json += ' { "data": "no2" }, ';
    json += ' { "data": "no3" }, ';
    
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
    let jsonMaping = JSON.parse(json);
    this.userManagementDt = $("#userManagementDt").DataTable(jsonMaping);
    this.loading = false;

    // on init table
    $("#userManagementDt tbody tr").css({
      "background-color": "white",
      cursor: "pointer"
    });

    // on click row
    $("#userManagementDt tbody").on("click", "tr", function () {
      $("#exciseBtn").prop("disabled", false);
      $("#userManagementDt tbody tr").css({
        "background-color": "white",
        cursor: "pointer"
      });
      $("#exciseId").val(
        $(this)
          .children()
          .toArray()[0].innerHTML
      );
      $(this).css("background-color", "rgb(197,217,241)");
    });
  }

  linkToDetail() {
    this.router.navigate(["/add-external-data/add-data"], {
      queryParams: {
        id: $("#exciseId").val(),
        num: this.analysNumber
      }
    });
  }

  FlagN = () => {
    this.flag = "N";
    if (this.userManagementDt != null) {
      this.userManagementDt.destroy();
    }
    this.initDatatable();
  };

  FlagNotN = () => {
    this.flag = "NOT N";
    if (this.userManagementDt != null) {
      this.userManagementDt.destroy();
    }
    this.initDatatable();
  };

  selectAnalysNumbers = () => {
    this.userManagementDt.destroy().draw();
    $("#tbodyDrinamic").empty();
    this.directAccess();
  };

  changeCoordinates = () => {
    this.coordinates = $("#coordinates").val();
    this.userManagementDt.destroy().draw();
    this.initDatatable();
  };

  changeSector = () => {
    this.sector = (<HTMLInputElement>document.getElementById("sector")).value;
    if (this.userManagementDt != null) {
      this.userManagementDt.destroy();
    }
    this.initDatatable();
  };

  directAccess = (withOut?: any) => {
    const headerUrl = AjaxService.CONTEXT_PATH + "filter/exise/getStartEndDate";
    let analysNumber: any;
    if (withOut) {
      analysNumber = this.analysNumberArr[0];
      this.analysNumber = analysNumber;
    } else {
      analysNumber = this.analysNumber;
    }
    this.getWorkSheetNumber();
    $.post(headerUrl, { analysNumber: analysNumber }, res => {
      this.before = res[0];
      this.last = res[1];
      this.from = res[2];
      this.month = res[3];

      var currDate = new Date();
      var currYear = currDate.getFullYear() + 543;

      this.curr = currYear;
      this.createTH(currYear);

      this.initDatatable();
      return res;
    });
  };

  getWorkSheetNumber = () => {
    const workSheetUrl = "filter/exise/getWorkSheetNumber/";
    this.ajax.get(`${workSheetUrl}${this.analysNumber}`, res => {
      this.workSheetNumber = res.json();
    });
  };

  sendLineUserValues = () => {
    this.ex.setToSendlineUser(
      this.before,
      this.last,
      this.analysNumber,
      this.workSheetNumber,
      this.from,
      this.month,
      this.curr
    );
  };

  createTH = currYear => {
    console.log("createTH", currYear);
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
    var str =

      '<th rowspan="2" style="text-align: center !important">ทะเบียนสรรพสามิต เดิม/ใหม่</th> ' +
      '<th rowspan="2" style="text-align: center !important" >เลขทะเบียนสรรพสามิตกเก่า</th> ' +
      '<th rowspan="2" style="text-align: center !important">ชื่อผู้ประกอบการ</th> ' +
      '<th rowspan="2" style="text-align: center !important">ชื่อโรงอุตสาหกรรม/สถานบริการ</th> ' +
      '<th rowspan="2" style="text-align: center !important">ภาค</th> ' +
      '<th rowspan="2" style="text-align: center !important">พื้นที่</th> ' +
      '<th colspan="2" style="text-align: center !important">การชำระภาษีในสภาวะปกติ (บาท)</th> ' +
      '<th rowspan="2" style="text-align: center !important">เปลี่ยนแปลง (ร้อยละ)</th> ' +
      '<th rowspan="2" style="text-align: center !important">ชำระภาษี(เดือน)</th> ' +
      '<th colspan="3" style="text-align: center !important">การตรวจสอบภาษีย้อนหลัง 3 ปีงบประมาณ</th> ' +
      
      '<th rowspan="2" style="text-align: center !important">พิกัด</th> ' +
      '<th rowspan="2" style="text-align: center !important">ที่อยู่โรงอุตสาหกรรม/สถานบริการ</th> ' +
      '<th rowspan="2" style="text-align: center !important">สถานะล่าสุด</th> ' +
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
    document.getElementById("trDrinamic").innerHTML = str;
  };
}
