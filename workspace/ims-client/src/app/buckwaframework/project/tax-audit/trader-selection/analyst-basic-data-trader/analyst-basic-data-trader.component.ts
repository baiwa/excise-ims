import { Component, OnInit, OnDestroy } from "@angular/core";
import { ExciseService } from "../../../../common/services/excise.service";
import { AjaxService } from "../../../../common/services/ajax.service";
import { Router, ActivatedRoute } from "@angular/router";

import { TextDateTH } from "../../../../common/helper/datepicker";
declare var $: any;
@Component({
  selector: "app-analyst-basic-data-trader",
  templateUrl: "./analyst-basic-data-trader.component.html",
  styleUrls: ["./analyst-basic-data-trader.component.css"]
})
export class AnalystBasicDataTraderComponent implements OnInit, OnDestroy {
  listMenu: any[] = [];
  valueForFontList: any[] = [];
  condition: any[] = [];
  valueForBackEndList: any[] = [];
  showmenu: boolean = true;
  userManagementDt: any;
  month: any;
  from: any;
  before: any;
  last: any;
  private currYear: any;
  private prevYear: any;
  exciseProductType: any;
  onLoading: boolean;
  numbers: number[];
  font: number[];
  back: number[];
  firstNumber: any;
  lastNumber: any;
  loading: boolean;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private ex: ExciseService
  ) {}

  ngOnDestroy() {
    $(".ui.modal.condition").remove();
  }

  ngOnInit() {
    this.listMenu = [
      "น้ำมัน",
      "เครื่องดื่ม",
      "ยาสูบ",
      "ไพ่",
      "แก้วและเครื่องแก้ว",
      "รถยนต์",
      "พรมและสิ่งทอปูพื้น",
      "แบตเตอรี่",
      "ไนท์คลับและดิสโกเธค",
      "สถานอาบน้ำหรืออบตัวและนวด",
      "สนามแข่งม้า",
      "สนามกอล์ฟ"
    ];
    this.loading = false;
    this.numbers = [1];
    console.log(this.numbers);
    this.back = [];
    this.font = [];
    for (let i = 0; i < 3; i++) {
      this.back.push(0);
      this.font.push(0);
    }

    this.exciseProductType = "";
    // subscribe to router event
    this.from = this.route.snapshot.queryParams["from"];
    this.month = this.route.snapshot.queryParams["month"];
    //split function
    var from_split = this.from.split("/");
    var currDate = new Date();
    var currYear = currDate.getFullYear() + 543;
    this.currYear = currYear;
    //default month & year
    var month = from_split[0];
    var year_before = from_split[1];

    var m = parseInt(month) + 1;
    var mm = parseInt(this.month);
    var yy = parseInt(year_before);
    this.prevYear = yy;
    var trHeaderColumn = "";

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
      '<th rowspan="2" style="text-align: center !important">ค่าเฉลี่ยภาษี</th> ' +
      '<th rowspan="2" style="text-align: center !important">ค่าร้อยละสูงสุด</th> ' +
      '<th rowspan="2" style="text-align: center !important">ค่าร้อยละต่ำสุด</th> ' +
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

    //show values
    var sum_month = TextDateTH.months[m - 1];
    this.before = sum_month + " " + yy;
    var sum_month2 = TextDateTH.months[parseInt(month) - 1];
    this.last = sum_month2 + " " + parseInt(year_before);
    this.initDatatable();
    $("#exciseBtn").prop("disabled", true);

    //on click condition modal
    $("#conditonModal").click(function() {
      $(".ui.modal.condition").modal("show");
    });

    this.listMenu = this.checkProductType(this.listMenu);
    console.log(this.listMenu);
  }

  checkProductType = listMenu => {
    const URL = AjaxService.CONTEXT_PATH + "/working/test/checkDupProductType";
    $.post(
      URL,
      {
        startBackDate: this.from,
        month: this.month,
        exciseProductType: this.exciseProductType
      },
      function(returnedData) {
        console.log("returnedData : ", returnedData.length);
        for (var i = 0; i < returnedData.length; i++) {
          var dat = returnedData[i];
          var index = listMenu.indexOf(dat);
          console.log("indexOf", index);
          listMenu[index] = "*" + dat;
        }
      }
    );
    return listMenu;
  };

  onSend = () => {
    this.loading = true;
    //call ExciseService
    this.ex.setformValues(
      this.before,
      this.last,
      this.from,
      this.month,
      this.currYear,
      this.prevYear
    );

    var router = this.router;
    var param1 = this.before;
    var param2 = this.last;
    var param3 = this.month;
    var from = this.from;
    var d = new Date();
    var conditionStr = "";
    console.log(this.valueForBackEndList);
    for (var i = 0; i < this.valueForBackEndList.length; i++) {
      conditionStr += this.valueForBackEndList[i];

      if (i != this.valueForBackEndList.length - 1) {
        conditionStr += ",";
      }
    }

    d.setFullYear(parseInt(this.from[1]));
    d.setMonth(parseInt(this.from[0]));
    const URL = AjaxService.CONTEXT_PATH + "/working/test/createWorkSheet";
    $.post(
      URL,
      {
        startBackDate: this.from,
        month: this.month,
        exciseProductType: this.exciseProductType,
        conditionStr: conditionStr
      },
      function(returnedData) {
        console.log("analysNumber : " + returnedData);
        console.log("this.before : " + param1);
        console.log("this.last : " + param3);
        console.log("this.month : " + param3);
        router.navigate(["/create-working-paper-trader"]);
        this.loading = false;
      }
    ).fail(function() {
      console.log("error");
    });
  };

  numberWithCommas = x => {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  };

  onKeyUp(e, target) {
    e.preventDefault();
    var num = e.target.value;
    for (var i = 0; i < num.length / 3; i++) {
      num = num.replace(",", "");
    }
    // console.log("num: ", num);
    // console.log("num[,]: ", this.numberWithCommas(num));
    (<HTMLInputElement>(
      document.getElementById(target)
    )).value = this.numberWithCommas(num);
  }

  onAddField = () => {
    let num = this.numbers.length;
    if (num < 3) {
      this.numbers.push(num + 1);
    }
  };

  onDelField = index => {
    this.numbers.splice(index, 1);
    console.log(this.numbers);
    this.back.splice(index, 1);
    this.font.splice(index, 1);
  };

  onSendModal = e => {
    e.preventDefault();
    this.firstNumber = (<HTMLInputElement>(
      document.getElementById("firstNumber")
    )).value;
    this.lastNumber = (<HTMLInputElement>(
      document.getElementById("lastNumber")
    )).value;
    console.log("firstNumber: ", this.firstNumber);
    console.log("lastNumber: ", this.lastNumber);
    console.log("font: ", this.font);
    console.log("back: ", this.back);
    this.valueForFontList = new Array();
    this.valueForBackEndList = new Array();
    this.valueForFontList.push("มากกว่า " + this.firstNumber);
    this.valueForBackEndList.push(
      ">:" + this.replaceAllValue(this.firstNumber)
    );
    for (var i = 0; i < this.font.length; i++) {
      if (this.font[i] != 0 || this.back[i] != 0) {
        this.valueForFontList.push(
          "ตั้งแต่ " + this.font[i] + " ถึง " + this.back[i]
        );
        this.valueForBackEndList.push(
          this.replaceAllValue(this.font[i]) +
            ":" +
            this.replaceAllValue(this.back[i])
        );
      }
    }
    this.valueForFontList.push("น้อยกว่า " + this.lastNumber);
    this.valueForBackEndList.push("<:" + this.replaceAllValue(this.lastNumber));
    $(".ui.modal.condition").modal("hide");
  };

  selectExciseProductType(productionType): void {
    this.exciseProductType = productionType;
    this.userManagementDt.destroy();
    this.initDatatable();
  }

  replaceAllValue(data) {
    while (data.indexOf(",") > 0) {
      data = data.replace(",", "");
    }
    return data;
  }

  changeCondition = data => {
    console.log(data);
    data = this.replaceAllValue(data);
    if (data == 0) {
      this.condition = this.valueForBackEndList;
    } else {
      var conditionValue = new Array();
      conditionValue.push(data);
      this.condition = conditionValue;
    }

    this.userManagementDt.destroy();
    this.initDatatable();
  };

  initDatatable(): void {
    this.onLoading = true;
    var d = new Date();
    const URL = AjaxService.CONTEXT_PATH + "/working/test/list";
    var json = "";
    json += " [ ";
    json += ' { "data": "exciseRegisttionNumberId","className":"center" }, ';
    json += ' { "data": "exciseId","className":"center" }, ';
    json += ' { "data": "exciseOperatorName" }, ';
    json += ' { "data": "exciseFacName" }, ';
    json += ' { "data": "exciseArea" }, ';
    json += ' { "data": "exciseFacAddress" ,"className":"center" }, ';
    json += ' { "data": "exciseRegisCapital","className":"center" }, ';
    json += ' { "data": "change","className":"center" }, ';
    json += ' { "data": "payingtax" ,"className":"center"}, ';
    json += ' { "data": "no1" }, ';
    json += ' { "data": "no2" }, ';
    json += ' { "data": "no3" }, ';
    json += ' { "data": "coordinates" }, ';
    json += ' { "data": "sector" }, ';
    json += ' { "data": "industrialAddress" }, ';
    json += ' { "data": "registeredCapital" }, ';
    json += ' { "data": "status" }, ';
    json += ' { "data": "avgTotal","className":"right" }, ';
    json += ' { "data": "monthMaxPercen","className":"right" }, ';
    json += ' { "data": "monthMinPercen","className":"right" }, ';
    for (var i = 0; i < this.month / 2; i++) {
      json +=
        ' { "data": "exciseFirstTaxReceiveAmount' +
        (i + 1) +
        '" ,"className":"right amount"}, ';
    }
    for (var i = 0; i < this.month / 2; i++) {
      if (i != this.month / 2 - 1) {
        json +=
          ' { "data": "exciseLatestTaxReceiveAmount' +
          (i + 1) +
          '" ,"className":"right amount"}, ';
      } else {
        json +=
          ' { "data": "exciseLatestTaxReceiveAmount' +
          (i + 1) +
          '" ,"className":"right amount"} ';
      }
    }
    json += "]";
    console.log(json);
    let jsonMapping = JSON.parse(json);
    console.log(jsonMapping);
    this.userManagementDt = $("#userManagementDt").DataTable({
      lengthChange: false,
      searching: false,
      select: true,
      ordering: true,
      pageLength: 10,
      processing: true,
      serverSide: true,
      paging: true,
      pagingType: "full_numbers",
      ajax: {
        type: "POST",
        url: URL,
        data: {
          exciseProductType: this.exciseProductType.replace("*", ""),
          startBackDate: this.from,
          condition:
            this.condition != undefined ? this.condition.toString() : "",
          month: this.month
        }
      },
      columns: jsonMapping,
      fnDrawCallback: function(oSettings) {
        if ($(".amount").length > 0) {
          $(".amount").each(function() {
            if (
              this.innerHTML == "" ||
              this.innerHTML == null ||
              this.innerHTML == "0" ||
              this.innerHTML == 0
            ) {
              this.className = "center amount null";
              this.innerHTML = "-";
            }
          });
        }
      }
    });

    //check loaded datatable...?
    setTimeout(() => {
      this.onLoading = false;
    }, 500);
  }
}
